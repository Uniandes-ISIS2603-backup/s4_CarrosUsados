package co.edu.uniandes.csw.carrosusados.dtos;

import co.edu.uniandes.csw.carrosusados.entities.PersonaEntity;

/**
 * Clase Data Transfer Object Usuario que contiene atributos que utilizan tanto
 * ClienteDTO, AdministradorDTO y VendedorDTO.
 */
public class PersonaDTO {
    /**
     * Cadena de caracteres que representa el id del usuario en el sistema.
     */
    protected Long id;
    /**
     * Cadena de caracteres que representa el nombre del usuario en el sistema.
     */
    protected String nombre;
    /**
     * Cadena de caracteres que representa el apellido del usuario en el
     * sistema.
     */
    protected String apellido;

    /**
     * Constructor vacío generado por defecto.
     */
    public PersonaDTO() {
        // Constructor por defecto
    }

    public PersonaDTO(PersonaEntity personaEntity) {
        this.id = personaEntity.getId();
        this.nombre = personaEntity.getNombre();
        this.apellido = personaEntity.getApellido();
    }

    /**
     * Devuelve el ID de la persona.
     *
     * @return id de la persona.
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el ID de la persona.
     *
     * @param id id de la persona.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre de la persona.
     *
     * @return nombre de la persona
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre de la persona.
     *
     * @param nombre nuevo nombre de la persona.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el apellido de la persona.
     *
     * @return nombre de la persona.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Modifica el apellido de la persona.
     *
     * @param apellido apellido de la persona.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

}
