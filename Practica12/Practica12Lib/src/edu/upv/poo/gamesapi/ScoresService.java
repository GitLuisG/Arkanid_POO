package edu.upv.poo.gamesapi;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Definición del servicio de la Rest API Primosoft Games.
 * @author luisroberto
 */
public interface ScoresService {
    
    @GET("getscores.php")
    Call<List<Score>> getScores(@Query("game")String game);
    
    @GET("getgames.php")
    Call<List<String>> getGames();
    
    @FormUrlEncoded
    @POST("addscore.php")
    Call<AddScoreRes> addScore(
            @Field("score") int score, @Field("player") String player, 
            @Field("game") String game);
    
}
