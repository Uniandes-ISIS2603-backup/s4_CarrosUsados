/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.test.logic;

import co.edu.uniandes.csw.carrosUsados.entities.ClienteEntity;
import co.edu.uniandes.csw.carrosUsados.ejb.ClienteLogic;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.ClientePersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
public class ClienteLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();
    @Inject
    private ClienteLogic clienteLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<ClienteEntity> data = new ArrayList<ClienteEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ClienteEntity.class.getPackage())
                .addPackage(ClienteLogic.class.getPackage())
                .addPackage(ClientePersistence.class.getPackage())
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
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            ClienteEntity entity = factory.manufacturePojo(ClienteEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Cliente en la DB.
     */
    @Test
    public void createClienteTest() throws BusinessLogicException  {
        PodamFactory factory = new PodamFactoryImpl();
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setNombreUsuario("jsbravo");
        newEntity.setNombre("Sebastian");
        newEntity.setApellido("Castelo");
        newEntity.setCorreo("jsbravoc@hotmail.co");
        newEntity.setTelefono("3000000000");
        newEntity.setContrasena("ConTra$ENAMuYFu3Rte$");
        Date mayor = new GregorianCalendar(1999, Calendar.JANUARY, 1).getTime();
    newEntity.setFechaNacimiento(mayor);
    ClienteEntity result = clienteLogic.createCliente(newEntity);
    Assert.assertNotNull(result);
        ClienteEntity entity = em.find(ClienteEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getApellido(), entity.getApellido());
        Assert.assertEquals(newEntity.getNombreUsuario(), entity.getNombreUsuario());
        Assert.assertEquals(newEntity.getDireccion(), entity.getDireccion());
        Assert.assertEquals(newEntity.getContrasena(), entity.getContrasena());
        Assert.assertEquals(newEntity.getTelefono(), entity.getTelefono());
        Assert.assertEquals(newEntity.getCorreo(), entity.getCorreo());
        Assert.assertEquals(newEntity.getFechaNacimiento(), entity.getFechaNacimiento());
    }

    /**
     * Prueba para consultar la lista de Clientes.
     */
    @Test
    public void getClientesTest() {
        List<ClienteEntity> list = clienteLogic.getClientes();
        Assert.assertEquals(data.size(), list.size());
        for (ClienteEntity ent : list) {
            boolean found = false;
            for (ClienteEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Cliente.
     */
    @Test
    public void getClienteTest() throws BusinessLogicException  {
        ClienteEntity entity = data.get(0);
        ClienteEntity newEntity = clienteLogic.getCliente(entity.getId());
        Assert.assertNotNull(newEntity);

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getApellido(), entity.getApellido());

        Assert.assertEquals(newEntity.getNombreUsuario(), entity.getNombreUsuario());
        Assert.assertEquals(newEntity.getDireccion(), entity.getDireccion());
        Assert.assertEquals(newEntity.getContrasena(), entity.getContrasena());
        Assert.assertEquals(newEntity.getTelefono(), entity.getTelefono());
        Assert.assertEquals(newEntity.getCorreo(), entity.getCorreo());
        Assert.assertEquals(newEntity.getFechaNacimiento(), entity.getFechaNacimiento());
    }

    /**
     * Prueba para eliminar un Cliente.
     */
    @Test
    public void deleteClienteTest() throws BusinessLogicException{
        ClienteEntity entity = data.get(0);
        clienteLogic.deleteCliente(entity.getId());
        ClienteEntity deleted = em.find(ClienteEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Cliente con datos correctos.
     */
    
    public void updateClienteTest() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);

        newEntity.setId(entity.getId());
        newEntity.setNombreUsuario("jsbravo");
        newEntity.setNombre("Sebastian");
        newEntity.setApellido("Castelo");
        newEntity.setCorreo("jsbravoc@hotmail.co");
        newEntity.setTelefono("3000000000");
        newEntity.setContrasena("ConTra$ENAMuYFu3Rte$");
        Date mayor = new GregorianCalendar(1999, Calendar.JANUARY, 1).getTime();
      newEntity.setFechaNacimiento(mayor);


        clienteLogic.updateCliente(newEntity.getId(),newEntity);

        ClienteEntity updated = em.find(ClienteEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), updated.getNombre());
        Assert.assertEquals(newEntity.getApellido(), updated.getApellido());

        Assert.assertEquals(newEntity.getNombreUsuario(), updated.getNombreUsuario());
        Assert.assertEquals(newEntity.getDireccion(), updated.getDireccion());
        Assert.assertEquals(newEntity.getContrasena(), updated.getContrasena());
        Assert.assertEquals(newEntity.getTelefono(), updated.getTelefono());
        Assert.assertEquals(newEntity.getCorreo(), updated.getCorreo());
        Assert.assertEquals(newEntity.getFechaNacimiento(), updated.getFechaNacimiento());
    }
    /**
     * Prueba para actualizar un Cliente con datos erroneos.
     */
    @Test(expected = BusinessLogicException.class)
    public void updateClienteTestErroneo() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);

        newEntity.setId(entity.getId());
newEntity.setNombre(null);
        clienteLogic.updateCliente(newEntity.getId(),newEntity);

        ClienteEntity updated = em.find(ClienteEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), updated.getNombre());
        Assert.assertEquals(newEntity.getApellido(), updated.getApellido());

        Assert.assertEquals(newEntity.getNombreUsuario(), updated.getNombreUsuario());
        Assert.assertEquals(newEntity.getDireccion(), updated.getDireccion());
        Assert.assertEquals(newEntity.getContrasena(), updated.getContrasena());
        Assert.assertEquals(newEntity.getTelefono(), updated.getTelefono());
        Assert.assertEquals(newEntity.getCorreo(), updated.getCorreo());
        Assert.assertEquals(newEntity.getFechaNacimiento(), updated.getFechaNacimiento());
    }
    /**
     * Prueba para actualizar un Cliente con datos incorrectos.
     */
    @Test(expected = BusinessLogicException.class)
    public void updateClienteTestCorreoInvalido() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);

        newEntity.setId(entity.getId());
 newEntity.setCorreo("correo");
        clienteLogic.updateCliente(newEntity.getId(), newEntity);

    }
    /**
     * Prueba para actualizar un cliente con datos incorrectos.
     */
    @Test(expected = BusinessLogicException.class)
    public void createClienteTestTelefonoInvalido() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);

        newEntity.setId(entity.getId());
 newEntity.setTelefono("2");
        clienteLogic.updateCliente(newEntity.getId(), newEntity);

    }
    /**
     * Prueba para actualizar un cliente con datos incorrectos.
     */
    @Test(expected = BusinessLogicException.class)
    public void updateClienteTestContrasenaInvalida() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);

        newEntity.setId(entity.getId());
 newEntity.setContrasena("12345678Aa");
        clienteLogic.updateCliente(newEntity.getId(), newEntity);

    }
}
