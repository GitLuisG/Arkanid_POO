package edu.upv.poo.gamesapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Clase clientp ara interactuar con la Rest API de Primosoft Games.
 * @author luisroberto
 */
public class ScoresClient {
    
    public final static String BASE_URL = 
            "http://www.primosoft.com.mx/games/api/";
    
    /**
     * Inicializa una nueva instancia de ScoresClient.
     */
    public ScoresClient() {
        retrofit = newRetrofit();
        svc = retrofit.create(ScoresService.class);
    }
    
    /**
     * Obtiene un listado de todos los juegos.
     * @return Un List de los juegos; en caso de error, null.
     */
    public List<String> getGames() {
        try {
            return svc.getGames().execute().body();
        }
        catch (Exception ex) {
            System.err.printf(
                    "ERROR (%s): %s\n", ex.getClass().getCanonicalName(), 
                    ex.getMessage());
        }
        return null;
    }
    
    /**
     * Obtiene un listado de todos los juegos (ejecución asíncrona).
     * @param clbck Objeto de tipo Callback que se ejecutará cuando se termine 
     * la ejecución de la operación.
     */
    public void getGamesAsync(Callback<List<String>> clbck) {
        svc.getGames().enqueue(clbck);
    }
    
    /**
     * Obtiene los scores de un juego en específico.
     * @param name Nombre del juego del que se quiere obtener los scores.
     * @return Un List de los scores del juego especificado; en caso de error, 
     * null.
     */
    public List<Score> getScores(String name) {
        try {
            return svc.getScores(name).execute().body();
        }
        catch (Exception ex) {
            System.err.printf(
                    "ERROR (%s): %s\n", ex.getClass().getCanonicalName(), 
                    ex.getMessage());
        }
        return null;
    }
    
    /**
     * Obtiene los scores de un juego en específico (ejecución asíncrona).
     * @param game Nombre del juego del que se quiere obtener los scores.
     * @param clbck Objeto de tipo Callback que se ejecutará cuando se termine 
     * la ejecución de la operación.
     */
    public void getScoresAsync(String game, Callback<List<Score>> clbck) {
        svc.getScores(game).enqueue(clbck);
    }
    
    /**
     * Agrega un nuevo score.
     * @param score Score (puntaje obtenido).
     * @param player Nombre o iniciales del jugador.
     * @param game Nombre del juego.
     * @return Un objeto de tipo AddScoreRes que contiene la respuesta de la 
     * ejecución.
     */
    public AddScoreRes addScore(int score, String player, String game) {
        try {
            return svc.addScore(score, player, game).execute().body();
        }
        catch (Exception ex) {
            System.err.printf(
                    "ERROR (%s): %s\n", ex.getClass().getCanonicalName(), 
                    ex.getMessage());
        }
        return null;
    }
    
    /**
     * Agrega un nuevo score  (ejecución asíncrona).
     * @param score Score (puntaje obtenido).
     * @param player Nombre o iniciales del jugador.
     * @param game Nombre del juego.
     * @param clbck Objeto de tipo Callback que se ejecutará cuando se termine
     * la ejecución de la operación.
     */
    public void addScoreAsync(
            int score, String player, String game, Callback<AddScoreRes> clbck) {
        svc.addScore(score, player, game).enqueue(clbck);
    }
    
    private Retrofit retrofit;
    private ScoresService svc;
    
    private Retrofit newRetrofit() {
        Gson gson = newGson();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
    
    private Gson newGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
    }
    
}
