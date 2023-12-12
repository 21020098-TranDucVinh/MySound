package uet.app.mysound.myAPI;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uet.app.mysound.R;

public class RegisterActivity extends AppCompatActivity {

    Button btnSignUp;
    EditText edUsername, edEmail, edPassword, edCpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnSignUp = findViewById(R.id.btnSignUp);
        edUsername = findViewById(R.id.edUsername);
        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        edCpassword = findViewById(R.id.edCPassword);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(edEmail.getText().toString()) || TextUtils.isEmpty(edUsername.getText().toString()) || TextUtils.isEmpty(edPassword.getText().toString()) || TextUtils.isEmpty(edCpassword.getText().toString())){

                    String message = "All inputs required ..";
                    Toast.makeText(RegisterActivity.this,message,Toast.LENGTH_LONG).show();
                }else {
                    RegisterRequest registerRequest = new RegisterRequest();
                    registerRequest.setEmail(edEmail.getText().toString());
                    registerRequest.setPassword(edPassword.getText().toString());
                    registerRequest.setName(edUsername.getText().toString());
                    registerUser(registerRequest);
                }
            }
        });

    }

    public void registerUser(RegisterRequest registerRequest){


        Call<RegisterResponse> registerResponseCall = ApiClient.getService().registerUsers(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                if(response.isSuccessful()){

                    // Dữ liệu JSON đã được phân tích thành công.
                    String success = "Request success to the server...";
                    Toast.makeText(RegisterActivity.this, success, Toast.LENGTH_LONG).show();
                    RegisterResponse registerResponse = response.body();
                    assert registerResponse != null;
                    String token = registerResponse.getToken();
                    String audioToken = registerResponse.getAudioToken();
                    // Ở đây bạn đã gán dữ liệu từ response vào các thuộc tính của lớp LoginResponse
                    AppData.getInstance().setRegisterResponse(registerResponse);
                    Toast.makeText(RegisterActivity.this, registerResponse.toString(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivityMyApi.class);
                    startActivity(intent);
                    finish();

                }else{
                    String message = "An error occurred please try again later ...";
                    Toast.makeText(RegisterActivity.this,message,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

                String message = t.getLocalizedMessage();
                Toast.makeText(RegisterActivity.this,message,Toast.LENGTH_LONG).show();

            }
        });
    }
}