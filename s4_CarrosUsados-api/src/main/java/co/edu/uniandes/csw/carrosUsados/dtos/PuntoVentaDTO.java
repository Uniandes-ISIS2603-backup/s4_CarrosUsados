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
public class PuntoVentaDTO implements Serializable{
    
    private int num_vendedores;
    private String ubicacion;
    private String ciudad;
    private long id;
    
    
     public PuntoVentaDTO(){
     }
     
     
      public void setNumVendedores(int num)
     {
         this.num_vendedores=num;
     }
     
     public int getNumVendedores()
     {
         return num_vendedores;
     }
     
       public void setUbicacion(String ubicar)
     {
         this.ubicacion=ubicar;
     }
     
     public String getUbicacion()
     {
         return ubicacion;
     }
     
       public void setCiudad(String ciudadn)
     {
         this.ciudad=ciudadn;
     }
     
     public String getCiudad()
     {
         return ciudad;
     }
     
       public void setId(long idnueva)
     {
         this.id=idnueva;
     }
     
     public long getId()
     {
         return id;
     }
}
