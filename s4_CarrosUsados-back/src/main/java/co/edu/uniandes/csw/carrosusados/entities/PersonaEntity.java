package co.edu.uniandes.csw.carrosusados.entities;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 *
 * @author js.bravo
 */
@Entity
public class PersonaEntity extends BaseEntity implements Serializable {
    /**
     * Nombre de la persona.
     */
    protected String nombre;
    /**
     * Apellido de la persona.
     */
    protected String apellido;

    /**
     * Retorna el nombre de la persona.
     *
     * @return nombre de la persona.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna el nombre de la persona.
     *
     * @param nombre - nombre de la persona.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna el apellido de la persona.
     *
     * @return apellido de la persona.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Asigna el apellido de la persona.
     *
     * @param apellido - apellido de la persona.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
