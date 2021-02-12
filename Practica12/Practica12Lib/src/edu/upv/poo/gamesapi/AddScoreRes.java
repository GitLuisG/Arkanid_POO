package edu.upv.poo.gamesapi;

import com.google.gson.annotations.SerializedName;

/**
 * Representa la respuesta de agregar un nuevo score.
 * @author luisroberto
 */
public class AddScoreRes {
    
    @SerializedName("_error")
    private String error;
    private Score score;

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return the score
     */
    public Score getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(Score score) {
        this.score = score;
    }
    
    
    
}
