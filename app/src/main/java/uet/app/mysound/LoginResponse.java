package uet.app.mysound;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    private String user_id;
    private String email;
    private String username;

    @SerializedName("token")
    private String token; // Tên trường JSON tương ứng

    @SerializedName("audio-token")
    private String audio_token; // Tên trường JSON tương ứng

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public String getAudioToken() {
        return audio_token;
    }
}
