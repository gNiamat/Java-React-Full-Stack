package com.tech.eccomerce.dto;

public class AuthResponse {

    private final String tokenType = "Bearer";
    private final String accessToken;
    private final long expiresIn;
    private final Long userId;
    private final Long walletId;

    public AuthResponse(String accessToken, long expiresIn, Long userId, Long walletId) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.userId = userId;
        this.walletId = walletId;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getWalletId() {
        return walletId;
    }
}