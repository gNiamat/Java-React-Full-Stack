package com.tech.eccomerce.dto;

import java.io.Serializable;
import java.time.Instant;

public class UserRegistrationEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;
    private Long walletId;
    private String name;
    private String email;
    private Instant registeredAt;

    public UserRegistrationEvent() {
    }

    public UserRegistrationEvent(Long userId, Long walletId, String name, String email, Instant registeredAt) {
        this.userId = userId;
        this.walletId = walletId;
        this.name = name;
        this.email = email;
        this.registeredAt = registeredAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Instant registeredAt) {
        this.registeredAt = registeredAt;
    }
}
