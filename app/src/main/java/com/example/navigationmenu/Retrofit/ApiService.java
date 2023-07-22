package com.example.navigationmenu.Retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiService {
    @POST("login")
    Call<LoginRquest.Response> login(@Body LoginRquest body);

    @POST("update_user")
    Call<UserUpdateRequest.Response> changeInfo(@Body UserUpdateRequest body);

    @POST("change_password")
    Call<ChangePassworsRequest.Response> changePassword(@Body ChangePassworsRequest body);
}
