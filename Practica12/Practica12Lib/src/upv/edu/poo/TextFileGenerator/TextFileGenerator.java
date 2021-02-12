/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upv.edu.poo.TextFileGenerator;

import java.io.FileNotFoundException;
import java.util.Formatter;

/**
 *
 * @author HARDCORE
 */
public class TextFileGenerator {
    public String Direccion;
    Formatter f = null;
    public TextFileGenerator(String Direccion){
        this.Direccion = Direccion;
    }
    
    public void AddText(String Contenido) throws Exception{
    
        try{
        f = OpenFile(); 
        }catch(SecurityException ex){
            System.err.println("Access denied. " + ex.getMessage());
            System.exit(1);
        }catch(FileNotFoundException ex){
            System.err.println("File Not found "+ ex.getMessage());
            System.exit(1);
        }
        f.format("%s\n", Contenido);
        
    }
    
    public void CloseFile(){
    f.close();
    }
    
    private Formatter OpenFile() throws Exception{
        try{
            return new Formatter(Direccion);
        }catch(SecurityException ex){
            throw new Exception("Error de seguridad.", ex);
        }catch(FileNotFoundException ex){
            throw new Exception("Archivo no encontrado ", ex);
        }
    }
}
