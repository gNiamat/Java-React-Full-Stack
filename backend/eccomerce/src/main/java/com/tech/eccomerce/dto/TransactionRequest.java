package com.tech.eccomerce.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class TransactionRequest {

    @NotNull
    private Long walletId;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal amount;

    public TransactionRequest() {
    }

    public TransactionRequest(Long walletId, BigDecimal amount) {
        this.walletId = walletId;
        this.amount = amount;
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
