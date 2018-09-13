/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */

@Entity
public class PuntoVentaEntity extends BaseEntity implements Serializable{
    
    private int numEmpleados;
    private String ciudad;
    private String ubicacion;
    
    @PodamExclude
    @OneToMany(mappedBy = "puntoVenta", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<CalificacionEntity> comentarios = new ArrayList<CalificacionEntity>();
    
    @PodamExclude
    @OneToOne(mappedBy = "puntoVenta")
    private AutomovilEntity automovil;
    
  public PuntoVentaEntity()
  {
      
  }
  
  public List<CalificacionEntity> getCalificaciones()
  {
      return comentarios;
  }
  
  public void setCalificaciones(List<CalificacionEntity> cal)
  {
      this.comentarios=cal;
  }
  
  public int getEmpleados()
  {
      return numEmpleados;
  }
  
  public void setEmpleados(int num)
  {
      this.numEmpleados= num;
  }
  
  public String getCiudad()
  {
      return ciudad;
  }
  
  public void setCiudad(String nueva)
  {
      this.ciudad=nueva;
  }
    
  public String getUbicacion()
  {
      return ubicacion;
  }
  
  public void setUbicacion(String nueva)
  {
      this.ubicacion= nueva;
  }


   

    public AutomovilEntity getAutomovil() {
        return automovil;
    }

    public void setAutomovil(AutomovilEntity automovil) {
        this.automovil = automovil;
    }
    
  
  
}
