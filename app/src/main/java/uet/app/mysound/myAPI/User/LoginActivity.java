package uet.app.mysound.myAPI.User;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uet.app.mysound.R;
import uet.app.mysound.myAPI.ApiClient;
import uet.app.mysound.myAPI.AppData;
import uet.app.mysound.ui.MainActivity;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText edUsername, edPassword;
    TextView noAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        edUsername = findViewById(R.id.et_email);
        edPassword = findViewById(R.id.et_password);
        noAccount = findViewById(R.id.tvCreateAccount);

        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edUsername.getText().toString()) || TextUtils.isEmpty(edPassword.getText().toString())) {
                    String message = "All inputs required ..";
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                } else {
                    LoginRequest loginRequest = new LoginRequest();
                    loginRequest.setEmail(edUsername.getText().toString());
                    loginRequest.setPassword(edPassword.getText().toString());
                    String request = "Đang đăng nhập ...";
                    Toast.makeText(LoginActivity.this, request, Toast.LENGTH_LONG).show();
                    loginUser(loginRequest);
                }
            }
        });

    }

    public void loginUser(LoginRequest loginRequest) {
        Call<LoginResponse> loginResponseCall = ApiClient.getService().loginUser(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    // Dữ liệu JSON đã được phân tích thành công.
                    String success = "Request success to the server...";
                    Toast.makeText(LoginActivity.this, success, Toast.LENGTH_LONG).show();
                    LoginResponse loginResponse = response.body();
                    assert loginResponse != null;
                    if (loginResponse != null) {
                        String token = loginResponse.getToken();
                        String audioToken = loginResponse.getAudioToken();
                        // Ở đây bạn đã gán dữ liệu từ response vào các thuộc tính của lớp LoginResponse
                    }
                    AppData.getInstance().setLoginResponse(loginResponse);
                    Toast.makeText(LoginActivity.this, loginResponse.toString(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    // load lại
                    intent.putExtra("data", loginResponse);
                    startActivity(intent);
                    finish();
                } else {
                    // Mã lỗi HTTP không phải là 2xx (ví dụ: 404, 500).
                    int httpErrorCode = response.code();
                    String errorMessage = "An error occurred. HTTP Error Code: " + httpErrorCode;
                    Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_LONG).show();

                    try {
                        // In ra nội dung lỗi từ response body (nếu có).
                        String errorBody = response.errorBody().string();
                        Log.e("Error Response", errorBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                String failed = "Request failed to the server...";
                Toast.makeText(LoginActivity.this, failed, Toast.LENGTH_LONG).show();

                // Kiểm tra xem có lỗi JSON không hợp lệ không
                if (t instanceof com.google.gson.stream.MalformedJsonException) {
                    // Xử lý lỗi JSON không hợp lệ ở đây, ví dụ:
                    Toast.makeText(LoginActivity.this, "Malformed JSON", Toast.LENGTH_LONG).show();
                } else {
                    // Xử lý lỗi khác
                    String message = t.getLocalizedMessage();
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}