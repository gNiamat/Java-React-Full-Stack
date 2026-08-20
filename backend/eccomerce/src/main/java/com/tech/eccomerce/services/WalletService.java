package com.tech.eccomerce.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tech.eccomerce.dto.TransactionRequest;
import com.tech.eccomerce.dto.TransferRequest;
import com.tech.eccomerce.dto.UserRegistrationRequest;
import com.tech.eccomerce.dto.WalletResponse;
import com.tech.eccomerce.entity.Transaction;
import com.tech.eccomerce.entity.User;
import com.tech.eccomerce.entity.Wallet;
import com.tech.eccomerce.exception.InsufficientFundsException;
import com.tech.eccomerce.exception.ResourceNotFoundException;
import com.tech.eccomerce.repository.TransactionRepository;
import com.tech.eccomerce.repository.UserRepository;
import com.tech.eccomerce.repository.WalletRepository;

@Service
public class WalletService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public WalletService(UserRepository userRepository, WalletRepository walletRepository,
            TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public WalletResponse registerUser(UserRegistrationRequest request) {
        userRepository.findByEmail(request.getEmail()).ifPresent(user -> {
            throw new IllegalArgumentException("Email already registered: " + request.getEmail());
        });

        User user = new User(request.getName(), request.getEmail());
        Wallet wallet = new Wallet(user);
        user.setWallet(wallet);

        userRepository.save(user);
        walletRepository.save(wallet);

        return new WalletResponse(wallet.getId(), user.getId(), wallet.getBalance());
    }

    @Transactional(readOnly = true)
    public WalletResponse getBalance(Long walletId) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found: " + walletId));
        return new WalletResponse(wallet.getId(), wallet.getUser().getId(), wallet.getBalance());
    }

    @Transactional
    public WalletResponse deposit(TransactionRequest request) {
        Wallet wallet = walletRepository.findById(request.getWalletId())
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found: " + request.getWalletId()));

        wallet.setBalance(wallet.getBalance().add(request.getAmount()));
        walletRepository.save(wallet);

        Transaction transaction = new Transaction("DEPOSIT", request.getAmount(), null, wallet, "COMPLETED",
                "Deposit to wallet");
        transactionRepository.save(transaction);

        return new WalletResponse(wallet.getId(), wallet.getUser().getId(), wallet.getBalance());
    }

    @Transactional
    public WalletResponse withdraw(TransactionRequest request) {
        Wallet wallet = walletRepository.findById(request.getWalletId())
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found: " + request.getWalletId()));

        if (wallet.getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientFundsException("Insufficient balance for withdrawal");
        }

        wallet.setBalance(wallet.getBalance().subtract(request.getAmount()));
        walletRepository.save(wallet);

        Transaction transaction = new Transaction("WITHDRAWAL", request.getAmount(), wallet, null, "COMPLETED",
                "Withdrawal from wallet");
        transactionRepository.save(transaction);

        return new WalletResponse(wallet.getId(), wallet.getUser().getId(), wallet.getBalance());
    }

    @Transactional
    public WalletResponse transfer(TransferRequest request) {
        if (request.getSourceWalletId().equals(request.getDestinationWalletId())) {
            throw new IllegalArgumentException("Source and destination wallets must differ");
        }

        Wallet sourceWallet = walletRepository.findById(request.getSourceWalletId())
                .orElseThrow(() -> new ResourceNotFoundException("Source wallet not found: " + request.getSourceWalletId()));
        Wallet destinationWallet = walletRepository.findById(request.getDestinationWalletId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Destination wallet not found: " + request.getDestinationWalletId()));

        if (sourceWallet.getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientFundsException("Insufficient balance for transfer");
        }

        sourceWallet.setBalance(sourceWallet.getBalance().subtract(request.getAmount()));
        destinationWallet.setBalance(destinationWallet.getBalance().add(request.getAmount()));

        walletRepository.save(sourceWallet);
        walletRepository.save(destinationWallet);

        Transaction transaction = new Transaction("TRANSFER", request.getAmount(), sourceWallet, destinationWallet,
                "COMPLETED", "Transfer between wallets");
        transactionRepository.save(transaction);

        return new WalletResponse(sourceWallet.getId(), sourceWallet.getUser().getId(), sourceWallet.getBalance());
    }
}
