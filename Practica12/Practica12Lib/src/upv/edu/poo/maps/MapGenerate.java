    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upv.edu.poo.maps;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author HARDCORE
 */
public class MapGenerate implements Paintable{
    public int map[][];
    public int totalBricks;
    public int w;
    public int h;
    public MapGenerate(int row,int col){
        map = new int[row][col];
        for(int i = 0; i < map.length;i++ ){
            for(int j = 0; j < map[0].length;j++){
                map[i][j]=1;
                if(map[0][j] == 1){
                    map[i][j] = 5 ;
                    totalBricks +=5;
                }else{
                totalBricks ++;
                }
                
            }
        }
        w = 1124/col;
        h = 200/row;
        System.out.println(totalBricks);
    }
    
    @Override
    public void paint(Graphics2D g) {
        for(int i = 0; i < map.length;i++ ){
            
            for(int j = 0; j < map[0].length;j++){

                //System.out.println("Paint");
                if(map[i][j] > 0){
                    //System.out.println("Active");
                    g.setColor(Color.WHITE);
                    g.fillRect(j * w + 80, i * h + 50, w, h);
                        
                    g.setColor(Color.BLACK);
                    g.setStroke(new BasicStroke(1));
                    g.drawRect(j * w + 80, i * h + 50, w, h);
                    }
            }
        }
    }
    
    
    public void setBrickValue(int value, int row, int col){
        
        this.map[row][col] = value;
    
    }
   
    
}
