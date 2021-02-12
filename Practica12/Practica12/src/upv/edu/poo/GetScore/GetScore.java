/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upv.edu.poo.GetScore;

import edu.upv.poo.gamesapi.Score;
import edu.upv.poo.gamesapi.ScoresClient;
import java.text.SimpleDateFormat;
import java.util.List;
/**
 *
 * @author HARDCORE
 */
public class GetScore {
    private ScoresClient clnt = new ScoresClient();      
    private int temp=0;
    String x = "Arkanoid";
    String[] scor= new String[1000];
    
 
    public void getscore(){
             List<Score> scoresG = clnt.getScores(x);
            System.out.println("Cant scores: " + scoresG.size());
            SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            temp=0;
            for (Score i : scoresG) {
                try{
                    //if(temp <= scoresG2.size()-1){
                        scor[temp]=i.getPlayer() + " - " + i.getScore() + " - " + f.format(i.getDate());
                        System.out.println(i.getPlayer() + " - " + i.getScore() + " - " + f.format(i.getDate()));
                    //}
                    
                }catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("ERROR al hacer llamada: " + ex.getMessage());
                 }
                temp++;
            }
        }
        
}
    