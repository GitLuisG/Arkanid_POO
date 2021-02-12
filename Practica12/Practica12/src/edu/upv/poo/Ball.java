package edu.upv.poo;

import Sonidos.Reproductor;
import edu.upv.poo.gamesapi.ScoresClient;
import java.awt.*;
import javax.swing.JOptionPane;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import upv.edu.poo.maps.MapGenerate;
import upv.edu.poo.TextFileGenerator.TextFileGenerator;
/**
 * Representa la pelota.
 * @author luisroberto
 */
public class Ball implements Paintable {

    /**
     * Diámetro de la pelota.
     */
    public static final int DIAMETER = 30;
    public MapGenerate map;
    TextFileGenerator f;
    public int Score = 0;
    public int vidas = 3;
    public int lvl = 0;
    public int ACC=5;
    
     private Reproductor Inicio;
    
    private int x = 0;
    private int y = 0;
    private int xa = ACC;
    private int ya = ACC;
    
    private final GamePanel gamePanel;
    private final ScoresClient clnt = new ScoresClient();
    public boolean pausa = false;
    /**
     * Inicializa una nueva instancia de Ball.
     * @param gamePanel El GamePanel donde estará la pelota.
     */
    public Ball(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        Inicio = new Reproductor(2);
        
        Inicio.sonidos.play();
        map = new MapGenerate(3,7);
        
    }
    public void GetScore(Graphics2D g) throws Exception{
        //score
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(" | "+" Score " + Score+" | ", 1200, 30);      
    } 
    @Override
    public void paint(Graphics2D g) {
        
        g.setColor(Color.WHITE);
        g.fillOval(x, y, DIAMETER, DIAMETER);
        //mapa
        map.paint(g);
        try {
            GetScore(g);
        } catch (Exception ex) {
            Logger.getLogger(Ball.class.getName()).log(Level.SEVERE, null, ex);
        }
        //vidas
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(" |"+" Vidas " + vidas+" | ", 1100, 30);
        //Bricks
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("|"+"Bricks " + map.totalBricks, 990, 30);
        //Nivel
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(" |"+"Nivel " + lvl, 900, 30);
        //Acceleracion
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(" |"+"Acceleraion " + ACC, 10, 30);
        //Jugador
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(" | "+" Player " + gamePanel.nickname, 200, 30);
        
        
    }

    public void paintComponent(Graphics2D g) {
        g.drawString("Game Over", 150,200);
    }

    /**
     * Realiza el calculo correspondiente al movimiento de la pelota.
     */
    public void move() {
        if (x + xa < 0) {
            xa = ACC;
        }
        if (x + xa > gamePanel.getWidth() - DIAMETER) {
            xa = -xa;
        }
        if (y + ya < 0) {
            ya = ACC;
        }
        if (y + ya > gamePanel.getHeight() - DIAMETER) {
            ya = ya;
        }
        if (collisionWithBall()) {
            ya = -1;
            y = gamePanel.getRacquet().getTopY() - DIAMETER;
             
        }
        if (y > gamePanel.getHeight() - 31) {
            
            JOptionPane mensaje= new JOptionPane();
            mensaje.showMessageDialog(null,"Game Over");
            mensaje.getMessage();
            //Cuando el contador de vidas llege a estar en 0
            if(vidas > 0){
                //el juego mientras tengas vidas te mostrara un mensaje
                int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro Que desea continuar(perdera sus puntos)?", "Alerta!", JOptionPane.YES_NO_OPTION);
                //al aseptar el mensaje perderas tus puntos obtenidos y volveras al mapa principal
                if(resp == 0){
                    y = 52;
                    x = 0;
                    vidas--;
                    //resetear jugada
                    Score = 0;
                    map = new MapGenerate(3,7);
                    
                }else{
                    //si deside no continuar entonces se enviara los datos a la api
                    if(clnt.addScore(Score,gamePanel.nickname, "Arkanoid") == null){
                        try {
                            //si la api no esta disponible aguardara los datos en un archivo de texto
                            TempScore();
                         } catch (Exception ex) {
                            Logger.getLogger(Ball.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                    
                    System.exit(0);
                }
            }else{
            System.exit(0);
            }
          
            
        }
        y += ya;
        x += xa;
    }

    /**
     * Obtiene la posición en X.
     * @return Posición en X.
     */
    public int getX() {
        return x;
    }

    /**
     * Obtiene la posición en Y.
     * @return Posición en Y.
     */
    public int getY() {
        return y;
    }
    
    public int getXa() {
        return xa;
    }

    public int getYa() {
        return ya;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }

    public boolean collisionWithBall() {
        //Colicion con la pelota
        Brickcollision();
        //avanza al siguiente nivel
        NextLevel();
        return gamePanel.getRacquet().getBounds().intersects(getBounds());
    
    }
    private int value;
    
    public void Brickcollision(){
        //Goto A (etiqueta a) nos sirve para regresar a una posision del codigo el for genera una matris de i * j
        A:for(int i = 0; i < map.map.length; i++){
            //la cual trabaja en la posision 0 del arreglo
            for(int j = 0; j < map.map[0].length; j++){
                //la matris de i * j valida que en la posision i y j sea mayor a 0
                if(map.map[i][j] > 0){
                    //entonces multiplica la columnas de la matris por la longitud de nuestra mapa y la suma es para ajustarlo
                    int bx = j * map.w + 80;
                    int by = i * map.h + 50;
                    //se aguarda en tamaño del mapa asignado
                    int bw = map.w;
                    int bh = map.h;
                    
                    //se generan dos rectangulos uno con las dimenciones del 
                    Rectangle ballRec = new Rectangle(x,y,DIAMETER,DIAMETER);
                    Rectangle rect = new Rectangle(bx,by,bw,bh);
                    Rectangle brickRect = rect;
                    
                    if(ballRec.intersects(brickRect)){
                        //System.out.println("Active");
                        value=map.map[i][j];
                        if(value > 1){
                            value--;
                        }else{
                            value=0;
                        }
                        map.setBrickValue(value, i, j);
                        this.Score+=5;
                        map.totalBricks--;
                        if(x + 19 <= brickRect.x || x + 1 >= brickRect.x + brickRect.width){
                            xa =-xa;
                        }else{
                            ya =-ya;
                        }
                    break A;
                    }
                    
                }
            }
        }
    }

    private void NextLevel() {
         //New lvl
        if(map.totalBricks == 0){
            JOptionPane mensaje= new JOptionPane();
            mensaje.showMessageDialog(null,"Next lvl");
            mensaje.getMessage();
            lvl+=1;
            ACC+=5;
            Random r = new Random();
            int dim =r.nextInt(100);
            if(dim == 0){
                dim = 1;
            }
            map = new MapGenerate(dim,dim);
        }
    }

    private void TempScore() throws Exception {
        f = new TextFileGenerator("C:\\Users\\HARDCORE\\Desktop\\"+gamePanel.nickname +".txt");
        f.AddText(
                " | "+" Player " + gamePanel.nickname +
                        " Score " + Score +
                        "| Vidas " + vidas + 
                        " | Nivel " + lvl
        );
        f.CloseFile();
       }


}