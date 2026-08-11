package com.tech.eccomerce.controller;

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

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/register")
    public ResponseEntity<WalletResponse> registerUser(@Validated @RequestBody UserRegistrationRequest request) {
        return ResponseEntity.ok(walletService.registerUser(request));
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<WalletResponse> getBalance(@PathVariable Long walletId) {
        return ResponseEntity.ok(walletService.getBalance(walletId));
    }

    @PostMapping("/deposit")
    public ResponseEntity<WalletResponse> deposit(@Validated @RequestBody TransactionRequest request) {
        return ResponseEntity.ok(walletService.deposit(request));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<WalletResponse> withdraw(@Validated @RequestBody TransactionRequest request) {
        return ResponseEntity.ok(walletService.withdraw(request));
    }

    @PostMapping("/transfer")
    public ResponseEntity<WalletResponse> transfer(@Validated @RequestBody TransferRequest request) {
        return ResponseEntity.ok(walletService.transfer(request));
    }
}
