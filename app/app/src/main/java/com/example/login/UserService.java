package com.example.login;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("api/me/")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);


    @POST("users/")
    Call<RegisterResponse> registerUsers(@Body RegisterRequest registerRequest);

}
