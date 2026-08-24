package com.tech.eccomerce.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

public class TransactionEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long transactionId;
    private String type; // DEPOSIT, WITHDRAWAL, TRANSFER
    private BigDecimal amount;
    private Long sourceWalletId;
    private Long destinationWalletId;
    private Long sourceUserId;
    private Long destinationUserId;
    private String status; // PENDING, COMPLETED, FAILED
    private String description;
    private Instant timestamp;

    public TransactionEvent() {
    }

    public TransactionEvent(Long transactionId, String type, BigDecimal amount, Long sourceWalletId,
            Long destinationWalletId, Long sourceUserId, Long destinationUserId, String status,
            String description, Instant timestamp) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.sourceWalletId = sourceWalletId;
        this.destinationWalletId = destinationWalletId;
        this.sourceUserId = sourceUserId;
        this.destinationUserId = destinationUserId;
        this.status = status;
        this.description = description;
        this.timestamp = timestamp;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getSourceWalletId() {
        return sourceWalletId;
    }

    public void setSourceWalletId(Long sourceWalletId) {
        this.sourceWalletId = sourceWalletId;
    }

    public Long getDestinationWalletId() {
        return destinationWalletId;
    }

    public void setDestinationWalletId(Long destinationWalletId) {
        this.destinationWalletId = destinationWalletId;
    }

    public Long getSourceUserId() {
        return sourceUserId;
    }

    public void setSourceUserId(Long sourceUserId) {
        this.sourceUserId = sourceUserId;
    }

    public Long getDestinationUserId() {
        return destinationUserId;
    }

    public void setDestinationUserId(Long destinationUserId) {
        this.destinationUserId = destinationUserId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
