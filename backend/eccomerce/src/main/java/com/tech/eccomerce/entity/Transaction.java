package com.tech.eccomerce.entity;

import java.math.BigDecimal;
import java.time.Instant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_wallet_id")
    private Wallet sourceWallet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_wallet_id")
    private Wallet destinationWallet;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    @Column(length = 500)
    private String description;

    public Transaction() {
    }

    public Transaction(String type, BigDecimal amount, Wallet sourceWallet, Wallet destinationWallet, String status, String description) {
        this.type = type;
        this.amount = amount;
        this.sourceWallet = sourceWallet;
        this.destinationWallet = destinationWallet;
        this.status = status;
        this.description = description;
        this.createdAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Wallet getSourceWallet() {
        return sourceWallet;
    }

    public void setSourceWallet(Wallet sourceWallet) {
        this.sourceWallet = sourceWallet;
    }

    public Wallet getDestinationWallet() {
        return destinationWallet;
    }

    public void setDestinationWallet(Wallet destinationWallet) {
        this.destinationWallet = destinationWallet;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
