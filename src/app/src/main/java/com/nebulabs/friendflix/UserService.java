package com.nebulabs.friendflix;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @GET("users")
    Call<List<User>> all();

    @GET("users/{uid}")
    Call<User> get(@Path("uid") String uid);

    @POST("users/new")
    Call<User> create(@Body User user);
}