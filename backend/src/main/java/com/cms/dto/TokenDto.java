package com.cms.dto;

public class TokenDto {
    public String token;
    public String refreshToken;
    public Long timeout;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public TokenDto token(String token) {
        this.token = token;
        return this;
    }

    public TokenDto refreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public TokenDto timeout(Long timeout) {
        this.timeout = timeout;
        return this;
    }
}
