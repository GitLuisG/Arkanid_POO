package edu.upv.poo;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import upv.edu.poo.maps.MapGenerate;

/**
 * Representa la raqueta que se usa para golpear la pelota.
 * @author luisroberto
 */
public final class Racquet implements Paintable {

    // Posición de la raqueta en Y.330
    public static final int y = 700;
    
    // Tamaño de la raqueta.
        public static final int WIDTH = 60;
    public static final int HEIGTH = 10;
    
    public MapGenerate map;
    
    
    //  cantidad de balas y contador de balas
    int disparadas=0,contador=0;
    
    // disparo de salida y cantidad de balas
    int balas = 20;
    //Balas
    public Ellipse2D[] disparos = new Ellipse2D[balas];
    public int[] xDisparos= new int[balas];
    public int[] yDisparos= new int[balas];
    // Posición en X.
    private int x = 0;
    
    // Aceleración y dirección
    private int xa = 0;
    
    private final GamePanel gamePanel;

    public Racquet(GamePanel gamePanel) {
        inicializarDisparos();
        this.gamePanel = gamePanel;
    }

    public void move() {
        if (x + xa > 0 && x + xa < gamePanel.getWidth() - WIDTH) {
            x += xa;
        }
    }

    @Override
    public void paint(Graphics2D g) {
        g.setColor(Color.green);
        g.fillRect(x, y, WIDTH, HEIGTH);
        
        for(int i = 0; i < disparadas;i++){
            disparos[i] = new Ellipse2D.Double(xDisparos[i], yDisparos[i], 10, 20);
            g.fill(disparos[i]);
        }
    }
    
    public void inicializarDisparos(){
        for(int i = 0; i < balas;i++){
            yDisparos[i] = y;
        }
    }

    public void keyReleassed(KeyEvent e) {
        xa = 0;
    }
    
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            xa -= 12;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            xa += 12;
        }
        if(e.getKeyCode() == KeyEvent.VK_P){
            
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                if(contador < balas && disparadas <balas){
            xDisparos[disparadas] = x;
            disparadas+=1;
            contador+=1;
            }
        }
        
        if (e.getKeyCode() == KeyEvent.VK_R) {
                contador=0;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGTH);
    }

    public int getTopY() {
        return y - HEIGTH;
    }
    private int value;
   
}
