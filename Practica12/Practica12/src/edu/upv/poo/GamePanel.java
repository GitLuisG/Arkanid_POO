package edu.upv.poo;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.Graphics;
/**
 * Panel principal donde se dibujaran los componentes del juego.
 * @author luisroberto
 */
public class GamePanel extends JPanel {
    public static String nickname;
    public GamePanel(){
    initControls();
    }
    public static void Getnickname(String Player) {
        nickname = Player;
    }

    public Ball getBall() { return Ball; }

    public Racquet getRacquet() { return racquet; }
    
    
    /**
     * Corre el juego.
     */
    public void run(){
        
        P:while (true) {
            
            move();
            repaint();
            try {
                Thread.sleep(10);
            }
            catch (InterruptedException ex) { }            
        }
    }
    

    @Override
    public void paint(Graphics g) {
        //System.out.println("Active");
        g.setColor(Color.BLACK);
        
        Graphics2D g2d = (Graphics2D) g;
        //se genera el fondo
        g2d.setColor(Color.BLACK);
        g2d.fill3DRect(0, 0, 2000, 1000, true);
        //-----
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        for (Paintable p : paintables) {
            p.paint(g2d);
        }
    }

    private final Ball Ball = new Ball(this);
    private final Racquet racquet = new Racquet(this);
    private final ArrayList<Paintable> paintables = new ArrayList<>();
    private final KeyListener moveControls = new KeyListenerControls();
    
    
    private void initControls() {
        
        addKeyListener(moveControls);
        setFocusable(true);
        paintables.add(Ball);
        paintables.add(racquet);
    }
    
    private void move() {
        getBall().move();
        getRacquet().move();
    }

    /**
     * Clase que contiene los controles del movimiento en relación a las teclas
     * presionadas en el panel GamePanel.
     */
    class KeyListenerControls implements KeyListener {
        
        @Override
        public void keyTyped(KeyEvent e) {
        }
        @Override
        public void keyPressed(KeyEvent e) {
            getRacquet().keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            getRacquet().keyReleassed(e);
        }
        
    }
    
}
