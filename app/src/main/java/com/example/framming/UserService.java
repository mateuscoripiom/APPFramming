package com.example.framming;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @POST("posters/{user_id}")
    Call<ResponseBody> saveUser(@Path("user_id") String userID, @Body UserRequest userRequest);

    @GET("posters/{user_id}/{filme_id}")
    Call<PosterResponse> getAllPosters(@Path("user_id") String userID, @Path("filme_id") String filmeID);

}
