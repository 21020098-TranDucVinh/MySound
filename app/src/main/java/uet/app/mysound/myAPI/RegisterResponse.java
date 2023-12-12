package uet.app.mysound.myAPI;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    private int id;
    private String  username;
    private String  email;
    @SerializedName("token")
    private String token; // Tên trường JSON tương ứng

    @SerializedName("audio-token")
    private String audio_token; // Tên trường JSON tương ứng
    public String getToken() {
        return token;
    }

    public String getAudioToken() {
        return audio_token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
