/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.ejb;

import javax.ejb.Stateless;
import java.util.regex.Pattern;

/**
 * @author js.bravo
 */
@Stateless
public class BaseLogic {
    private static final Pattern NOMBRE_REGEX = Pattern.compile("^[\\p{L} .'-]+$", Pattern.CASE_INSENSITIVE);
    private static final Pattern NUMERO_REGEX = Pattern.compile("^[0-9]*$");

    public boolean validateString(String str) {
        return (!(str != null && str.trim().isEmpty()));
    }

    /**
     * Verifica si el nombre enviado por parámetro cumple su expresión regular.
     * El nombre debe ser compuesto por letras con espacios (opcionalmente).
     *
     * @param nombre - nombre a verificar.
     * @return true si el nombre cumple su expresión regular, false de lo
     * contrario.
     */
    public boolean validateNombre(String nombre) {
        return nombre == null ? false : NOMBRE_REGEX.matcher(nombre).find();
    }

    /**
     * Verifica si una cadena de texto es un número.
     *
     * @param numero - cadena de texto a verificar.
     * @return true si la cadena de texto es un numero, false de lo
     * contrario.
     */
    public boolean validateNumero(String numero) {
        return numero == null ? false : NUMERO_REGEX.matcher(numero).find();
    }

}
