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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
  * Clase que representa un punto de venta en la persistencia y permite su
 * serialización.
 * @author Daniella Arteaga
 */

@Entity
public class PuntoVentaEntity extends BaseEntity implements Serializable{
    
    private int numEmpleados;
    private String ciudad;
    private String ubicacion;
    private Double latitud;
    private Double longitud;
    
    @PodamExclude
    @OneToMany(mappedBy = "puntoVenta", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<CalificacionEntity> calificaciones = new ArrayList<CalificacionEntity>();

    @PodamExclude
    @ManyToOne
    private AdministradorEntity administrador;

    /*
    @PodamExclude
    @OneToOne(mappedBy = "puntoVenta")
    private AutomovilEntity automovil;
    */
    /**
     *Constructor de entidad.
     */
    public PuntoVentaEntity()
  {
      
  }
  
    /**
     *  Devuelve las lista de calificaciones del punto de venta.
     * @return comentarios calificaciones del punto de venta.
     */
    public List<CalificacionEntity> getCalificaciones()
  {
      return calificaciones;
  }
  
    /**
     *  Modifica la lista de calificaciones del punto de venta.
     * @param cal la lista de las calificaciones que se asinarán.
     */
    public void setCalificaciones(List<CalificacionEntity> cal)
  {
      this.calificaciones=cal;
  }

    /**
     * Devuelve la ciudad del punto de venta.
     * @return ciudad del punto de venta.
     */
    public String getCiudad()
  {
      return ciudad;
  }
  
    /**
     * Modifica la ciudad del punto de venta.
     * @param nueva ciudad a asignar al punto de venta.
     */
    public void setCiudad(String nueva)
  {
      this.ciudad=nueva;
  }
    
    /**
     *  Devuelve ubicación del punto de venta.
     * @return ubicacion del punto de venta
     */
    public String getUbicacion()
  {
      return ubicacion;
  }
  
    /**
     * Modifica la ubicación del punto
     * @param nueva ubicación del punto de venta.
     */
    public void setUbicacion(String nueva)
  {
      this.ubicacion= nueva;
  }

    /**
     *  Devuelve automovil del punto de venta.
     * @return automovil del punto de venta.
     */
    /*
    public AutomovilEntity getAutomovil() {
        return automovil;
    }*/

    /**
     *  Devuelve el carro que pertenece al punto
     * @param automovil nuevo que se asignarà.
     */
    /*
    public void setAutomovil(AutomovilEntity automovil) {
        this.automovil = automovil;
    }*/

    public int getNumEmpleados() {
        return numEmpleados;
    }

    
       /**
     * Modifica el número de empleados del punto.
     * @param numEmpleados número de empleados del punto.
     */
    public void setNumEmpleados(int numEmpleados) {
        this.numEmpleados = numEmpleados;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public AdministradorEntity getAdministrador() {
        return administrador;
    }

    public void setAdministrador(AdministradorEntity administrador) {
        this.administrador = administrador;
    }
    
    
  
  
}
