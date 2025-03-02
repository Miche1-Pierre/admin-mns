package com.mns.admin.dto;

public class AuthRequest {
    private String email;
    private String motDePasse;
    private String token;

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }
}
