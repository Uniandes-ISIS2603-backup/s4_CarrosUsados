/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.ejb;

import co.edu.uniandes.csw.carrosUsados.entities.UsuarioEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.*;
import javax.ejb.Stateless;

/**
 *
 * @author js.bravo
 */
@Stateless
public class UsuarioLogic extends BaseLogic {

    private static final Pattern CORREO_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern TELEFONO_REGEX = Pattern.compile("^((\\d{2})|(\\d{3}))?3\\d{9}$");
    private static final Pattern NOMBRE_USUARIO_REGEX = Pattern.compile("(^$)|(\\s+$)");
    private static final Pattern CONTRASENA_REGEX = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    private static final int MAYOR_DE_EDAD = 18;
    
        /**
   * Verifica los atributos a persistir de un usuario.
   *
   * @param usuarioEntity - La entidad usuario a persistir.
   * @throws BusinessLogicException Si algún atributo no cumple con sus restricciones.
   */
   public void validateUsuarioWrapper(UsuarioEntity usuarioEntity) throws BusinessLogicException
   {
     if(!(validateNombre(usuarioEntity.getNombre())&&validateNombre(usuarioEntity.getApellido()))){
         throw new BusinessLogicException("El nombre y/o apellido es inválido.");
     }
     if(!(validateCorreo(usuarioEntity.getCorreo())))
     {
         throw new BusinessLogicException("El correo es inválido.");
     }
     if(!(validateContrasena(usuarioEntity.getContrasena())))
     {
         throw new BusinessLogicException("La contraseña no tiene el formato esperado.");
     }
     if(!(validateNombreUsuario(usuarioEntity.getNombreUsuario())))
     {
         throw new BusinessLogicException("El nombre de usuario no tiene el formato esperado.");
     }
     if(!(validateFecha(usuarioEntity.getFechaNacimiento(),MAYOR_DE_EDAD)))
     {
         throw new BusinessLogicException("La fecha de nacimiento no es correcta y/o no representa a un mayor de edad (" + MAYOR_DE_EDAD + ")." );
     }
     if(!(validateTelefono(usuarioEntity.getTelefono())))
     {
         throw new BusinessLogicException("El teléfono es inválido. (" + usuarioEntity.getTelefono() + ")");
     }
   }

    /**
     * Verifica si el correo enviado por parámetro cumple su expresión regular.
     * El correo debe contener un usuario, '@', un dominio.
     *
     * @param correo - correo del usuario
     * @return true si el correo cumple su expresión regular, false de lo
     * contrario.
     */
    public static boolean validateCorreo(String correo) {
        boolean valid = correo != null && CORREO_REGEX.matcher(correo).find();
        return valid;
    }

    /**
     * Verifica si el telefono enviado por parámetro cumple su expresión
     * regular. El telefono debe tener mínimo 10 caracteres su primer caracter
     * debe ser '3', opcionalmente empezando con codigo de país.
     *
     * @param telefono - telefono del usuario
     * @return true si el telefono cumple su expresión regular, false de lo
     * contrario.
     */
    public static boolean validateTelefono(String telefono) {
        boolean valid = telefono != null && TELEFONO_REGEX.matcher(telefono).matches();
        return valid;
    }

    /**
     * Verifica si el login enviado por parámetro cumple su expresión regular.
     * El login no debe contener espacios.
     *
     * @param usuario - login del usuario
     * @return true si el login no tiene espacios, false de lo contrario.
     */
    public static boolean validateNombreUsuario(String usuario) {
        boolean valid = usuario != null && !NOMBRE_USUARIO_REGEX.matcher(usuario).find();
        return valid;
    }

    /**
     * Verifica si la contrasena enviada por parámetro cumple su expresión
     * regular. La contrasena no debe tener espacios, debe tener una mayúscula,
     * una minúscula, un número, un símbolo y ser de mínimo 8 caracteres.
     *
     * @param contrasena - contrasena del usuario
     * @return true si la contrasena cumple su expresión regular, false de lo
     * contrario.
     */
    public static boolean validateContrasena(String contrasena) {
        boolean valid = contrasena == null ? false :CONTRASENA_REGEX.matcher(contrasena).find();
        return valid;
    }


    /**
     * Verifica si la fecha enviada por parámetro cumple las reglas de negocio.
     * Un usuario registrado debe tener 18 o más años.
     * Un administrador debe tener mínimo 0 años en el cargo.
     *
     * @param fecha - fecha a validar del usuario.
     * @param min - mínimo número de años hasta hoy.
     * @return true la fecha cumple las reglas de negocio, false
     * de lo contrario.
     */
    public static boolean validateFecha(Date fecha, int min) {
        if (fecha ==null)
                return false;
        Calendar hoy = Calendar.getInstance();
        hoy.set(Calendar.HOUR_OF_DAY, 0);
        Calendar nacimiento = Calendar.getInstance(Locale.US);
        nacimiento.setTime(fecha);
        int diferencia = hoy.get(Calendar.YEAR) - nacimiento.get(Calendar.YEAR);
        if (nacimiento.get(Calendar.MONTH) > hoy.get(Calendar.MONTH)
                || (nacimiento.get(Calendar.MONTH) == hoy.get(Calendar.MONTH) && nacimiento.get(Calendar.DATE) > hoy.get(Calendar.DATE))) {
            diferencia--;
        }
        return diferencia >= min;
    }

}
