 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;

import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class CalificacionDTO implements Serializable {
    
    
    private int num_estrellas;
    private String comentarios;
    
    public CalificacionDTO(){
    }
    
    public void setNumestrellas(int num)
    {
        this.num_estrellas= num;
    }
    
    public int getNumestrellas()
    {
        return num_estrellas;
    }
    
    
      public void setComentario(String com)
     {
         this.comentarios=com;
     }
     
     public String getComentario()
     {
         return comentarios;
     }
}
