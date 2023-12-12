package uet.app.mysound.myAPI;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import uet.app.mysound.R;

public class MainActivityMyApi extends AppCompatActivity {
    LoginResponse loginResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_myapi);

        loginResponse = AppData.getInstance().getLoginResponse();

        if (loginResponse != null) {
            // Sử dụng loginResponse ở đây
            String token = loginResponse.getToken();
            String audioToken = loginResponse.getAudioToken();
            TextView tokenTextView = findViewById(R.id.tokenTextView);
            TextView audioTokenTextView = findViewById(R.id.audioTokenTextView);

            tokenTextView.setText("Token: " + token);
            audioTokenTextView.setText("Audio Token: " + audioToken);
        }

    }
}