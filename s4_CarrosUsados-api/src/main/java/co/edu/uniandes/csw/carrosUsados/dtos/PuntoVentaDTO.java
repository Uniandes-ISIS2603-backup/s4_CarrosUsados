/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;

 
import co.edu.uniandes.csw.carrosUsados.entities.PuntoVentaEntity;
import java.io.Serializable;
 

/**
 *
 *  Objeto de transferencia de datos Puntos de venta. Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * @author Daniella Arteaga
 */
public class PuntoVentaDTO implements Serializable {

    private int numeroVendedores;
    private String ubicacion;
    private String ciudad;
    private Double latitud;
    private Double longitud;
    private long id;
   
    /**
     *Constructor
     */
    public PuntoVentaDTO() {
          //este constructor es dejado vacìo intencionalmente
    }
  
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param puntoVenta Entity: Es la entidad que se va a convertir a DTO
     */
    public PuntoVentaDTO(PuntoVentaEntity puntoVenta) {
        this.numeroVendedores = puntoVenta.getNumEmpleados();
        this.ubicacion = puntoVenta.getUbicacion();
        this.ciudad = puntoVenta.getCiudad();
        this.id = puntoVenta.getId();
        this.latitud= puntoVenta.getLatitud();
        this.longitud= puntoVenta.getLongitud();
    }

  
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public PuntoVentaEntity toEntity()
    {
        PuntoVentaEntity entity= new PuntoVentaEntity();
        entity.setCiudad(this.ciudad);
        entity.setNumEmpleados(this.numeroVendedores);
        entity.setUbicacion(this.ubicacion);
        entity.setLatitud(this.latitud);
        entity.setLongitud(this.longitud);
        entity.setId(this.id);
        
        return entity;
    }

       /**
     * Modifica el número de punto de venta
     *
     * @param num  número nuevo de vendedores
     */
    public void setNumVendedores(int num) {
        this.numeroVendedores = num;
    }

   /**
     * Devuelve numero de vendedores de punto
     *
     * @return num_vendedores numero de vendedores
     * 
     */
    public int getNumVendedores() {
        return numeroVendedores;
    }

      /**
     * Modifica la ubicación de punto
     *
     * @param ubicar ubicacion nueva
     */
    public void setUbicacion(String ubicar) {
        this.ubicacion = ubicar;
    }

   /**
     * Devuelve la ubicación de punto
     *
     * @return ubicacion ubicacion del punto
     */
    public String getUbicacion() {
        return ubicacion;
    }

       /**
     * Modifica la ciudad de punto
     *
     * @param ciudadn nueva ciudad
     */
    public void setCiudad(String ciudadn) {
        this.ciudad = ciudadn;
    }

    /**
     * Devuelve la ciudad de punto
     *
     * @return ciudad la ciudad del punto de venta
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Modifica el id de punto.
     *
     * @param idnueva nueva id 
     */
    public void setId(long idnueva) {
        this.id = idnueva;
    }

    /**
     * Devuelve el ID de punto
     *
     * @return id id de punto
     */
    public long getId() {
        return id;
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

  
    
    
}
