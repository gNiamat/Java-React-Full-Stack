package com.tech.eccomerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.eccomerce.dto.TransactionRequest;
import com.tech.eccomerce.dto.TransferRequest;
import com.tech.eccomerce.dto.UserRegistrationRequest;
import com.tech.eccomerce.dto.WalletResponse;
import com.tech.eccomerce.services.WalletService;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private static final Logger log = LoggerFactory.getLogger(WalletController.class);

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/register")
    public ResponseEntity<WalletResponse> registerUser(@Validated @RequestBody UserRegistrationRequest request) {
        log.info("Register wallet request received for email: {}", request.getEmail());
        WalletResponse response = walletService.registerUser(request);
        log.info("Wallet registration completed for walletId: {} and userId: {}", response.getWalletId(), response.getUserId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<WalletResponse> getBalance(@PathVariable Long walletId) {
        log.info("Balance inquiry request received for walletId: {}", walletId);
        WalletResponse response = walletService.getBalance(walletId);
        log.info("Balance retrieved for walletId: {} with amount: {}", walletId, response.getBalance());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/deposit")
    public ResponseEntity<WalletResponse> deposit(@Validated @RequestBody TransactionRequest request) {
        log.info("Deposit request received for walletId: {} amount: {}", request.getWalletId(), request.getAmount());
        WalletResponse response = walletService.deposit(request);
        log.info("Deposit completed for walletId: {} new balance: {}", request.getWalletId(), response.getBalance());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<WalletResponse> withdraw(@Validated @RequestBody TransactionRequest request) {
        log.info("Withdrawal request received for walletId: {} amount: {}", request.getWalletId(), request.getAmount());
        WalletResponse response = walletService.withdraw(request);
        log.info("Withdrawal completed for walletId: {} new balance: {}", request.getWalletId(), response.getBalance());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/transfer")
    public ResponseEntity<WalletResponse> transfer(@Validated @RequestBody TransferRequest request) {
        log.info("Transfer request received from walletId: {} to walletId: {} amount: {}",
                request.getSourceWalletId(), request.getDestinationWalletId(), request.getAmount());
        WalletResponse response = walletService.transfer(request);
        log.info("Transfer completed from walletId: {} to walletId: {} remaining balance: {}",
                request.getSourceWalletId(), request.getDestinationWalletId(), response.getBalance());
        return ResponseEntity.ok(response);
    }
}
