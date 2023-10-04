package com.example.framming;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @POST("posters/{user_id}")
    Call<ResponseBody> saveUser(@Path("user_id") String userID, @Body UserRequest userRequest);

}
