package com.example.login;

public class AppData {
    private static AppData instance;
    private LoginResponse loginResponse;

    private AppData() {}

    public static AppData getInstance() {
        if (instance == null) {
            instance = new AppData();
        }
        return instance;
    }

    public LoginResponse getLoginResponse() {
        return loginResponse;
    }

    public void setLoginResponse(LoginResponse response) {
        loginResponse = response;
    }
}
