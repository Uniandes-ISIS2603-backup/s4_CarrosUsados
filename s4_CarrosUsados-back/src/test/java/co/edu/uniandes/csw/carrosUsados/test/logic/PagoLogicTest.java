/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.test.logic;

import co.edu.uniandes.csw.carrosUsados.ejb.PagoLogic;
import co.edu.uniandes.csw.carrosUsados.entities.PagoEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.FichaTecnicaPersistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class PagoLogicTest {
     private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private PagoLogic pagoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;
    
    private List<PagoEntity> data = new ArrayList<PagoEntity>();
    
   
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PagoEntity.class.getPackage())
                .addPackage(PagoLogic.class.getPackage())
                .addPackage(FichaTecnicaPersistence.class.getPackage())
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
        em.createQuery("delete from PagoEntity").executeUpdate();
       
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        
        for (int i = 0; i < 3; i++) {
            PagoEntity pago = factory.manufacturePojo(PagoEntity.class);
            em.persist(pago);
            data.add(pago);
        }
    }
    
     /**
     * Prueba para crear un Pago
     *
     * @throws co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException
     */
    @Test
    public void createPagoTest() throws BusinessLogicException {
        PagoEntity newEntity = factory.manufacturePojo(PagoEntity.class);
        newEntity.setNum_tergeta("1234567891234567");
        Date fechaValida = new Date(2017,11, 10);
        newEntity.setFechaTarjeta(fechaValida);
        PagoEntity result = pagoLogic.createPago(newEntity);
        Assert.assertNotNull(result);
        PagoEntity entity = em.find(PagoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNum_targeta(), entity.getNum_targeta());
        Assert.assertEquals(newEntity.getCodigoTargeta(), entity.getCodigoTargeta());
        Assert.assertEquals(newEntity.getComprobanteDePago(), entity.getComprobanteDePago());
        Assert.assertEquals(newEntity.getFechaTarjeta(), entity.getFechaTarjeta());
    }
    
    /**
     * Prueba para crear un pago con una fecha invalida y un numero de tarjeta invalido
     *
     * @throws co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createPagoConValoresInvalidos() throws BusinessLogicException {
        PagoEntity newEntity = factory.manufacturePojo(PagoEntity.class);
        newEntity.setNum_tergeta("123");
        Date fechaInvalida = new Date(2019,11, 10);
        newEntity.setFechaTarjeta(fechaInvalida);
        pagoLogic.createPago(newEntity);
    }
    
    /**
     * Prueba para consultar un Pago
     * @throws co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException
     */
    @Test
    public void getPagoTest() throws BusinessLogicException {
        PagoEntity entity = data.get(0);
        PagoEntity resultEntity = pagoLogic.getPago(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNum_targeta(), resultEntity.getNum_targeta());
        Assert.assertEquals(entity.getCodigoTargeta(), resultEntity.getCodigoTargeta());
        Assert.assertEquals(entity.getComprobanteDePago(), resultEntity.getComprobanteDePago());
        Assert.assertEquals(entity.getFechaTarjeta(), resultEntity.getFechaTarjeta());
    }
  
   
    /**
     * Prueba para eliminar un Pago.
     *
     * @throws co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException
     */
    @Test
    public void deletePagoTest() throws BusinessLogicException {
        PagoEntity entity = data.get(0);
        pagoLogic.deletePago(entity.getId());
        PagoEntity deleted = em.find(PagoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
