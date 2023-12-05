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
    @POST("payment/{user_id}")
    Call<ResponseBody> savePayment(@Path("user_id") String userID, @Body PaymentRequest paymentRequest);

    @POST("updateUsers/{user_id}")
    Call<ResponseBody> saveEditU(@Path("user_id") String userID, @Body EditarRequest editarRequest);
    @POST("deleteUsers/{user_id}")
    Call<ResponseBody> deleteUser(@Path("user_id") String userID);

    @POST("ticket/{user_id}")
    Call<ResponseBody> saveTicket(@Path("user_id") String userID, @Body TicketRequest ticketRequest);
    @GET("ticket/user/{user_id}")
    Call<ArrayList<TicketResponse>> getAllUsersTicker(@Path("user_id") String userID);

    @GET("users/{user_id}")
    Call<UserResponse> getAllDataUser(@Path("user_id") String userID);

    @GET("payment/{user_cpf}")
    Call<ArrayList<PaymentResponse>> getUserPaymets(@Path("user_cpf") String userCPF);

    @POST("list/{user_id}")
    Call<CriarListaResponse> saveNewLista(@Path("user_id") String userId, @Body ListaRequest listaRequest);
    @GET("list/{user_id}")
    Call<ArrayList<ListaResponse>> getAllLists(@Path("user_id") String userId);

    @GET("list/{user_id}/{lista_id}")
    Call<ArrayList<Root>> getList(@Path("user_id") String userID, @Path("lista_id") String listaID);

    @POST("list/{user_id}/{lista_id}")
    Call<ResponseBody> saveFilmeLista(@Path("user_id") String userId, @Path("lista_id") String listaID, @Body FListaRequest fListaRequest);

    @POST("feedback/{user_id}/{feedback_id}")
    Call<ResponseBody> saveFeedbackEdit(@Path("user_id") String userId, @Path("feedback_id") String feedbackId, @Body FeedbackERequest feedbackERequest);
    @GET("nationalMovies")
    Call<ArrayList<ItemNac>> getAllNational();
    @POST("deleteFeedback/{user_id}/{feedback_id}")
    Call<ResponseBody> deleteFeedback(@Path("user_id") String userId, @Path("feedback_id") String feedbackId);


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
    @GET("feedback/{user_id}/{feedback_id}")
    Call<FeedbackResponse> getMovieFeedback(@Path("user_id") String userID, @Path("feedback_id") String feedbackID);
    @GET("session/0/{filme_id}")
    Call<ArrayList<ItemSession>> getSessionMovie(@Path("filme_id") String filmeID);
    @GET("session/0/0/{sessao_id}")
    Call<ArrayList<ItemSessionTipIng>> getSessionID(@Path("sessao_id") String sessaoID);
    @GET("cinema/{token_cinema}")
    Call<CinemaResponse> getCinemaData(@Path("token_cinema") String tokenCinema);

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
