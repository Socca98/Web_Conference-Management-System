package com.cms.dto.token;

public class TokenInformation {
    private String username;
    private Long timeout;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public TokenInformation username(String username) {
        this.username = username;
        return this;
    }

    public TokenInformation timeout(Long timeout) {
        this.timeout = timeout;
        return this;
    }
}
