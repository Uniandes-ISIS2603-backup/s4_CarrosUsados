/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carros.usados.test.logic;

import co.edu.uniandes.csw.carros.usados.ejb.AdministradorLogic;
import co.edu.uniandes.csw.carros.usados.entities.AdministradorEntity;
import co.edu.uniandes.csw.carros.usados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carros.usados.persistence.AdministradorPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author js.bravo
 */
@RunWith(Arquillian.class)
public class AdministradorLogicTest {

   private PodamFactory factory = new PodamFactoryImpl();
   
    @Inject
    private AdministradorLogic administradorLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<AdministradorEntity> data = new ArrayList<AdministradorEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AdministradorEntity.class.getPackage())
                .addPackage(AdministradorLogic.class.getPackage())
                .addPackage(AdministradorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from AdministradorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            AdministradorEntity entity = factory.manufacturePojo(AdministradorEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Administrador en la DB.
     */
    @Test
    public void createAdministradorTest() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);
        /**
        * Datos ejemplo que sí cumplen las reglas de negocio
        * Nombres sin números
        * correo(@)dominio.(*)
        * telefono 3[9]
        * contrasena fuerte (min una mayuscula, minuscula, simbolo, numero, 8 chars)
        * Fecha nacimiento >= 18 años
        * Cargo sin números
        * Cargo empezado en el pasado (>=0 años de iniciado el cargo) 
        */

        newEntity.setNombreUsuario("js.bravo");
        newEntity.setNombre("Juan");
        newEntity.setApellido("Bravo");
        newEntity.setCorreo("js.bravo@uniandes.edu.co");
        newEntity.setTelefono("3000000000");
        newEntity.setContrasena("ConTra$EN3Fu3Rte");
        Date mayor = new GregorianCalendar(1999, Calendar.JANUARY, 1).getTime();
  newEntity.setFechaNacimiento(mayor);
    newEntity.setCargo("Desarrollador");
      Date inicio = new GregorianCalendar(2018, Calendar.SEPTEMBER, 10).getTime();
    newEntity.setFechaInicio(inicio);
        AdministradorEntity result = administradorLogic.createAdministrador(newEntity);

        Assert.assertNotNull(result);

        AdministradorEntity entity = em.find(AdministradorEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getApellido(), entity.getApellido());
        Assert.assertEquals(newEntity.getCargo(), entity.getCargo());
        Assert.assertEquals(newEntity.getNombreUsuario(), entity.getNombreUsuario());
        Assert.assertEquals(newEntity.getFechaInicio(), entity.getFechaInicio());
        Assert.assertEquals(newEntity.getContrasena(), entity.getContrasena());
        Assert.assertEquals(newEntity.getTelefono(), entity.getTelefono());
        Assert.assertEquals(newEntity.getCorreo(), entity.getCorreo());
        Assert.assertEquals(newEntity.getFechaNacimiento(), entity.getFechaNacimiento());
    }

    /**
     * Prueba para consultar la lista de Administradores.
     */
    @Test
    public void getAdministradoresTest() {
        List<AdministradorEntity> list = administradorLogic.getAdministradores();
        Assert.assertEquals(data.size(), list.size());
        for (AdministradorEntity ent : list) {
            boolean found = false;
            for (AdministradorEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Administrador.
     */
    @Test
    public void getAdministradorTest() throws BusinessLogicException {
        AdministradorEntity entity = data.get(0);
        AdministradorEntity newEntity = administradorLogic.getAdministrador(entity.getId());
        Assert.assertNotNull(newEntity);

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getApellido(), entity.getApellido());
        Assert.assertEquals(newEntity.getCargo(), entity.getCargo());
        Assert.assertEquals(newEntity.getNombreUsuario(), entity.getNombreUsuario());
        Assert.assertEquals(newEntity.getFechaInicio(), entity.getFechaInicio());
        Assert.assertEquals(newEntity.getContrasena(), entity.getContrasena());
        Assert.assertEquals(newEntity.getTelefono(), entity.getTelefono());
        Assert.assertEquals(newEntity.getCorreo(), entity.getCorreo());
        Assert.assertEquals(newEntity.getFechaNacimiento(), entity.getFechaNacimiento());
    }

    /**
     * Prueba para eliminar un Administrador.
     */
    @Test
    public void deleteAdministradorTest() {
        AdministradorEntity entity = data.get(0);
        administradorLogic.deleteAdministrador(entity.getId());
        AdministradorEntity deleted = em.find(AdministradorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Administrador.
     */
    @Test
    public void updateAdministradorTest() throws BusinessLogicException {
        AdministradorEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);
newEntity.setId(entity.getId());
/**
* Datos ejemplo que sí cumplen las reglas de negocio
* Nombres sin números
* correo(@)dominio.(*)
* telefono 3[9]
* contrasena fuerte (min una mayuscula, minuscula, simbolo, numero, 8 chars)
* Fecha nacimiento >= 18 años
* Cargo sin números
* Cargo empezado en el pasado (>=0 años de iniciado el cargo) 
*/
        newEntity.setNombreUsuario("js.bravo");
        newEntity.setNombre("Juan");
        newEntity.setApellido("Bravo");
        newEntity.setCorreo("js.bravo@uniandes.edu.co");
        newEntity.setTelefono("3000000000");
        newEntity.setContrasena("ConTra$EN3Fu3Rte");
        Date mayor = new GregorianCalendar(1999, Calendar.JANUARY, 1).getTime();
  newEntity.setFechaNacimiento(mayor);
    newEntity.setCargo("Desarrollador");
      Date inicio = new GregorianCalendar(2018, Calendar.SEPTEMBER, 10).getTime();
    newEntity.setFechaInicio(inicio);
        administradorLogic.updateAdministrador(newEntity.getId(), newEntity);

        AdministradorEntity updated = em.find(AdministradorEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), updated.getNombre());
        Assert.assertEquals(newEntity.getApellido(), updated.getApellido());
        Assert.assertEquals(newEntity.getCargo(), updated.getCargo());
        Assert.assertEquals(newEntity.getNombreUsuario(), updated.getNombreUsuario());
        Assert.assertEquals(newEntity.getFechaInicio(), updated.getFechaInicio());
        Assert.assertEquals(newEntity.getContrasena(), updated.getContrasena());
        Assert.assertEquals(newEntity.getTelefono(), updated.getTelefono());
        Assert.assertEquals(newEntity.getCorreo(), updated.getCorreo());
        Assert.assertEquals(newEntity.getFechaNacimiento(), updated.getFechaNacimiento());
    }

     /**
      * Prueba para actualizar un Administrador con datos incorrectos.
      */
     @Test(expected = BusinessLogicException.class)
     public void updateAdministradorTestNombreInvalido() throws BusinessLogicException {
         AdministradorEntity entity = data.get(0);
         PodamFactory factory = new PodamFactoryImpl();
         AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);
 
         newEntity.setId(entity.getId());
  newEntity.setNombre("");
         administradorLogic.updateAdministrador(newEntity.getId(), newEntity);

     }
     /**
      * Prueba para actualizar un Administrador con datos incorrectos.
      */
     @Test(expected = BusinessLogicException.class)
     public void updateAdministradorTestCorreoInvalido() throws BusinessLogicException {
         AdministradorEntity entity = data.get(0);
         PodamFactory factory = new PodamFactoryImpl();
         AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);
 
         newEntity.setId(entity.getId());
  newEntity.setCorreo("correo");
         administradorLogic.updateAdministrador(newEntity.getId(), newEntity);

     }
     /**
      * Prueba para actualizar un Administrador con datos incorrectos.
      */
     @Test(expected = BusinessLogicException.class)
     public void createAdministradorTestTelefonoInvalido() throws BusinessLogicException {
         AdministradorEntity entity = data.get(0);
         PodamFactory factory = new PodamFactoryImpl();
         AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);
 
         newEntity.setId(entity.getId());
  newEntity.setTelefono("2000000000");
         administradorLogic.updateAdministrador(newEntity.getId(), newEntity);

     }
     /**
      * Prueba para actualizar un Administrador con datos incorrectos.
      */
     @Test(expected = BusinessLogicException.class)
     public void updateAdministradorTestContrasenaInvalida() throws BusinessLogicException {
         AdministradorEntity entity = data.get(0);
         PodamFactory factory = new PodamFactoryImpl();
         AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);
 
         newEntity.setId(entity.getId());
  newEntity.setContrasena("contrasena");
         administradorLogic.updateAdministrador(newEntity.getId(), newEntity);

     }
     /**
      * Prueba para actualizar un Administrador con datos incorrectos.
      */
     @Test(expected = BusinessLogicException.class)
     public void updateAdministradorTestFechaInicioInvalida() throws BusinessLogicException {
         AdministradorEntity entity = data.get(0);
         PodamFactory factory = new PodamFactoryImpl();
         AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);
 
         newEntity.setId(entity.getId());
         Date inicio = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR)+1, Calendar.getInstance().get(Calendar.MONTH)+1, Calendar.getInstance().get(Calendar.DATE)+1).getTime();
       newEntity.setFechaInicio(inicio);
         administradorLogic.updateAdministrador(newEntity.getId(), newEntity);

     }
}
