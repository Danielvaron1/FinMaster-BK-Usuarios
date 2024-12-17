package com.unir.Usuarios.model.request;

public class LoginResponse {
    private String token;

    private long expiresIn;

    public LoginResponse(String jwtToken, long expirationTime) {
        this.token = jwtToken;
        this.expiresIn = expirationTime;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getToken() {
        return token;
    }
}
