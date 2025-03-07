package com.example.qiming.mvp.model.entity;

public class AuthorizationUser {

    private String username;

    private String password;

    private String code;

    private String uuid = "";

    @Override
    public String toString() {
        return "{username=" + username  + ", password= ******}";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
