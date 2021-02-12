/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sonidos;
import java.applet.AudioClip;
/**
 *
 * @author HARDCORE
 */
public final class Reproductor {
    public AudioClip sonidos;
    public Reproductor(int x){
        switch(x){
            case 1:Opening();
            break;
            case 2:FondoGame();
            break;
        }
    
    }
    public void Opening(){
        sonidos = java.applet.Applet.newAudioClip(getClass().getResource("/Musica/InicioArkanoid.wav"));
    }
    public void FondoGame(){
        sonidos = java.applet.Applet.newAudioClip(getClass().getResource("/Musica/Intro.wav"));
    }
   
    
}
