package edu.upv.poo;


import java.awt.Dimension;
import java.net.MalformedURLException;
import javax.swing.*;
import Sonidos.Reproductor;
import java.util.Scanner;

/**
 * Frame contenedor del juego.
 * @author luisroberto
 */
public class Practica12 extends JFrame 
{

    public static final String TITLE = "Arkanoid";
    
    /**
     * Entry point of the application.
     * @param args the command line arguments
     */
    
    public String Player;
       
    public void main(String[] args) throws MalformedURLException {
        //JFrame.setDefaultLookAndFeelDecorated(true);
         JFrame frame = new Practica12();
    }
    public Practica12() throws MalformedURLException {
        super(TITLE);
         Player = JOptionPane.showInputDialog("Nombre del Jugador?");
         if(Player != null){
             //Ingresa el nombre del jugador
             GamePanel.Getnickname(Player);
            initFrame();
         }
         else {
                setDefaultCloseOperation(this.EXIT_ON_CLOSE);
         }
        //
    }

    private final GamePanel game = new GamePanel();
    
    private final Reproductor Inicio = new Reproductor(1);
    Scanner scanner = new Scanner(System.in);
    
    private void initFrame() {
        this.setMinimumSize(new Dimension(800, 400));
        //setSize(800, 400);
        this.setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        Inicio.sonidos.play();
        add(game);
        setVisible(true);
        game.run();
        
        
        
    }
   

}
