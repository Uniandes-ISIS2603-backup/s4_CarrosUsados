/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class AutomovilEntity extends BaseEntity implements Serializable {

    /**
     * modelo del automovil.
    */
    private int modelo;
    /**
     * marca del automovil.
     */
    private String marca;
    /**
     * Año en que salio el modelo del automovil a la venta.
     */
    private int anio;
    /**
     * Color del carro
     */
    private String color;
    /**
     * Numero de chasis del automovil, identificador único del automovil.
     */
    private String numChasis;
    /**
     * Placa del automovil, segundo identificador único del automovil.
     */
    private String placa;
    /**
     * Fecha de agregación al sistema del automovil.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAgregacion; //Chequear el tipo de dato

    /**
     * Precio al que fue se le compro el automovil usado a un cliente.
     */
    private String precioOriginal;

    //Relacion a FichaTecnicaDTO dado que esta tiene cardinalidad
    /**
     * Se mapea relacion con una Ficha Tenica. Un automovil tiene una única ficha tecnica.
     */
    @PodamExclude
    @OneToOne(mappedBy = "automovil", cascade = CascadeType.ALL)
    private FichaTecnicaEntity fichaTecnica;

    //Falta agregar PuntoVenta y Calificacion
    /**
     * Mapea la relacion con un punto de venta. U automovil tiene un unico punto de venta.
     */
    @PodamExclude
    @OneToOne
    private PuntoVentaEntity puntoVenta;

    /**
     * Mapea relacion con un objeto Calificacion. Un automovil puede tener muchas calificaciones.
     */
    @PodamExclude
    @OneToMany(mappedBy = "automovil", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CalificacionEntity> calificaciones;

    /**
     * Mapea relacion con ub objeto Articulo. Un automovil aparece en exactamente un articulo
     */
    @PodamExclude
    @OneToOne
    private ArticuloEntity articulo;

    /**
     * Mapea relacion con un objeto Vendedor. Un vendedor puede tenermuchos automoviles, y un automovil tiene unicamente un vendedor.
     */
    @PodamExclude
    @ManyToOne
    private VendedorEntity vendedor;

    /**
     * Mapea relacion con un objeto Modelo. Un automovil tiene un unico Modelo, pero un Modelo puede tener muchos automoviles.
     */
    @PodamExclude
    @ManyToOne
    private ModeloEntity modeloAsociado;
    
    
    public AutomovilEntity(){
        
    }
    
    /**
     * Retorna el modelo del automovil.
     * @return el modelo
     */
    public int getModelo() {
        return modelo;
    }

    /**
     * Actualiza el modelo del automovil.
     * @param modelo
     */
    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    /**
     * Retorna la marca del automovil.
     * @return la marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Actualiza la marca del automovil
     * @param marca
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }
    /**
     * Retorna el anio del automovil.
     * @return el anio
     */
    public int getAnio() {
        return anio;
    }

    /**
     * Actualiza el anio del automovil.
     * @param anio
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }
    /**
     * Retorna el color del automovil.
     * @return el color
     */
    public String getColor() {
        return color;
    }
    /**
     * Actualzia el color del automovil.
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }
    /**
     * Retorna el numero de chasis del automovil.
     * @return el numero de chasis
     */
    public String getNumChasis() {
        return numChasis;
    }
    /**
     * Actualiza el numero de chasis del automovil.
     * @param numchasis
     */
    public void setNumChasis(String numchasis) {
        this.numChasis = numchasis;
    }
    /**
     * Retorna la placa del automovil.
     * @return
     */
    public String getPlaca() {
        return placa;
    }
    /**
     * Actualiza la placa del automovil.
     * @param placa
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    /**
     * Retorna la fecha de agregacion del automovil
     * @return la fecha de agregacion
     */
    public Date getFechaAgregacion() {
        return fechaAgregacion;
    }
    /**
     * Actualiza la fecha de agregacion del automovil.
     * @param fechaAgregacion
     */
    public void setFechaAgregacion(Date fechaAgregacion) {
        this.fechaAgregacion = fechaAgregacion;
    }
    /**
     * Retorna el precio original del automovil.
     * @return el precio original
     */
    public String getPrecioOriginal() {
        return precioOriginal;
    }
    /**
     * Actualiza el precio original del automovil
     * @param precioOriginal
     */
    public void setPrecioOriginal(String precioOriginal) {
        this.precioOriginal = precioOriginal;
    }
    /**
     * Retorna la ficha tecnica asociada al automovil
     * @return la ficha tecnica
     */
    public FichaTecnicaEntity getFichaTecnica() {
        return fichaTecnica;
    }
    /**
     * Actualiza la fecha tecnica del automovil
     * @param fichaTecnica
     */
    public void setFichaTecnica(FichaTecnicaEntity fichaTecnica) {
        this.fichaTecnica = fichaTecnica;
    }
    /**
     * Retorna la coleccion de calificaciones asociadas al automovil.
     * @return la coleccion de calificaciones
     */
    public List<CalificacionEntity> getCalificaciones() {
        return calificaciones;
    }
    /**
     * Actualiza la coleccion de calificaciones del automovil.
     * @param calificaciones
     */
    public void setCalificaciones(List<CalificacionEntity> calificaciones) {
        this.calificaciones = calificaciones;
    }
    /**
     * Retorna el punto de venta asociado al automovil.
     * @return el punto de venta
     */
    public PuntoVentaEntity getPuntoVenta() {
        return puntoVenta;
    }
    /**
     * Actualiza el punto de venta asociado al automovil
     * @param puntoVenta
     */
    public void setPuntoVenta(PuntoVentaEntity puntoVenta) {
        this.puntoVenta = puntoVenta;
    }
    /**
     * Retorna el vendedor del automovil
     * @return el vendedor
     */
    public VendedorEntity getVendedor() {
        return vendedor;
    }
    /**
     * Actualiza el vendedor asociado al automovil
     * @param vendedor
     */
    public void setVendedor(VendedorEntity vendedor) {
        this.vendedor = vendedor;
    }

    public ModeloEntity getModeloAsociado() {
        return modeloAsociado;
    }

    public void setModeloAsociado(ModeloEntity modeloAsociado) {
        this.modeloAsociado = modeloAsociado;
    }
    
}
