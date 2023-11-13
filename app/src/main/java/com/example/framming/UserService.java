package com.example.framming;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {
    @POST("posters/{user_id}")
    Call<ResponseBody> saveUser(@Path("user_id") String userID, @Body UserRequest userRequest);

    @GET("posters/{user_id}/{filme_id}")
    Call<PosterResponse> getAllPosters(@Path("user_id") String userID, @Path("filme_id") String filmeID);

    @GET("users/{user_id}")
    Call<UserResponse> getAllDataUser(@Path("user_id") String userID);

    @GET("nationalMovies")
    Call<ArrayList<ItemNac>> getAllNational();

    @POST("users/register")
    Call<CadastroReponse> salvarUsuario(@Body UsuarioRequest usuarioRequest);

    @POST("users/login")
    Call<LoginResult> logarUsuario(@Body LoginRequest loginRequest);

    @POST("upload?")
    Call<ResponseBody> salvarImgP(@Query("expiration") String expirationLink, @Query("key") String keyLink, @Query("image") String imageLink);
}
