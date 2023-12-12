package uet.app.mysound.myAPI;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {

    @POST("api/me/")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @GET("/")
    Call<LoginResponse> testAPI(@Body LoginRequest loginRequest);

    @POST("/api/register")
    Call<RegisterResponse> registerUsers(@Body RegisterRequest registerRequest);

}
