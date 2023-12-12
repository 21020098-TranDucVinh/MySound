package uet.app.mysound.myAPI;

public class AppData {
    private static AppData instance;
    private LoginResponse loginResponse;

    private RegisterResponse registerResponse;
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

    public RegisterResponse getRegisterResponse() {
        return registerResponse;
    }

    public void setLoginResponse(LoginResponse response) {
        loginResponse = response;
    }

    public void setRegisterResponse(RegisterResponse registerResponse) {
        this.registerResponse = registerResponse;
    }
}
