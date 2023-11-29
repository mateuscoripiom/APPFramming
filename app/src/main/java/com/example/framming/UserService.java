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

    @POST("feedback/{user_id}")
    Call<ResponseBody> saveFeedback(@Path("user_id") String userID, @Body FeedbackRequest feedbackRequest);
    @GET("feedback/{user_id}")
    Call<ArrayList<ItemFeedback>> getAllFeedback(@Path("user_id") String userID);
    @GET("feedback/{filme_id}")
    Call<ArrayList<ItemCritica>> getAllFeedbackMovie(@Path("filme_id") String filmeID);
    @GET("feedbackMovie/{user_id}/{filme_id}")
    Call<ArrayList<FeedbackResponse>> getFeedbackMovie(@Path("user_id") String userID, @Path("filme_id") String filmeID);

    @POST("upload?")
    Call<ResponseBody> salvarImgP(@Query("expiration") String expirationLink, @Query("key") String keyLink, @Query("image") String imageLink);
    @GET("movies/{id}")
    Call<ArrayList<FilmesResponse>> getAllDataFilme(@Path("id") String filmeID);
    @POST("watch-later/{user_id}")
    Call<ResponseBody> saveQueroVer(@Path("user_id") String queroVer, @Body QueroVerRequest queroVerRequest);
    @GET("watch-later/{user_id}")
    Call<ArrayList<ItemQueroVer>> getQueroVer(@Path("user_id") String userID);
    @GET("watch-later/{user_id}/{filme_id}")
    Call<QueroVerResponse> getMovieQV(@Path("user_id") String userID, @Path("filme_id") String filmeID);
    @POST("favoriteMovies/{user_id}")
    Call<ResponseBody> salvarFilmeFav(@Path("user_id") String userId, @Body FavoritoRequest favoritoRequest);
    @GET("favoriteMovies/{user_id}")
    Call<ArrayList<FilmesResponse>> getFavoritos(@Path("user_id") String userId);
    @GET("movies/{id}")
    Call<ArrayList<ItemFilme>> getAllDataFilmeAPI(@Path("id") String filmeID);


}
