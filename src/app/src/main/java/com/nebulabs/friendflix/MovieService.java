package com.nebulabs.friendflix;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MovieService {
    @GET("movies")
    Call<List<Movie>> all();

    @GET("movies/{mid}")
    Call<User> get(@Path("mid") String mid);

    @POST("movies/new")
    Call<User> create(@Body Movie movie);
}
