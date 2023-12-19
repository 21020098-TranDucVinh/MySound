package uet.app.mysound.myAPI;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import uet.app.mysound.myAPI.User.LoginRequest;
import uet.app.mysound.myAPI.User.LoginResponse;
import uet.app.mysound.myAPI.User.RegisterRequest;
import uet.app.mysound.myAPI.User.RegisterResponse;

public interface UserService {

    @POST("api/me/")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @GET("/")
    Call<LoginResponse> testAPI(@Body LoginRequest loginRequest);

    @POST("/api/register")
    Call<RegisterResponse> registerUsers(@Body RegisterRequest registerRequest);

}
