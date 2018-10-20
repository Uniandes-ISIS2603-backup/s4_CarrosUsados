/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.test.logic;

import co.edu.uniandes.csw.carrosUsados.ejb.FacturaLogic;
import co.edu.uniandes.csw.carrosUsados.entities.FacturaEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.FichaTecnicaPersistence;
import java.util.ArrayList;
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
public class FacturaLogicTest {
     private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private FacturaLogic facturaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;
    
    private List<FacturaEntity> data = new ArrayList<FacturaEntity>();
    
   
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
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
            FacturaEntity pago = factory.manufacturePojo(FacturaEntity.class);
            em.persist(pago);
            data.add(pago);
        }
    }
    
     /**
     * Prueba para crear una factura
     *
     * @throws co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException
     */
    @Test
    public void createFacturaTest() throws BusinessLogicException {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        newEntity.setTotal(123);
        newEntity.setSubtotal(123);
        FacturaEntity result = facturaLogic.createFactura(newEntity);
        Assert.assertNotNull(result);
        FacturaEntity entity = em.find(FacturaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getTotal(), entity.getTotal());
        Assert.assertEquals(newEntity.getSubtotal(), entity.getSubtotal());
        Assert.assertEquals(newEntity.getFormaDePago(), entity.getFormaDePago());
        
        
    }
    
    /**
     * Prueba para crear un factura con un total invalido
     *
     * @throws co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createFacturaConValorTotalInvalido() throws BusinessLogicException {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        newEntity.setTotal(-2);
        facturaLogic.createFactura(newEntity);
    }
    /**
     * Prueba para crear una factura con un subtotal invalido 
     *
     * @throws co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createFacturaConValorSubTotalInvalido() throws BusinessLogicException {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        newEntity.setSubtotal(-3);
        facturaLogic.createFactura(newEntity);
    }
    /**
     * Prueba para crear un pago con una fecha invalida y un numero de tarjeta invalido
     *
     * @throws co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createFacturasSinProducto() throws BusinessLogicException {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        newEntity.setProducto(null);
        facturaLogic.createFactura(newEntity);
    }
    /**
     * Prueba para crear un pago con una fecha invalida y un numero de tarjeta invalido
     *
     * @throws co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createFacturaSinFormaDePago() throws BusinessLogicException {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        newEntity.setFormaDePago(null);
        facturaLogic.createFactura(newEntity);
    }
    /**
     * Prueba para consultar un Pago
     * @throws co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException
     */
    @Test
    public void getFacturaTest() throws BusinessLogicException {
        FacturaEntity entity = data.get(0);
        FacturaEntity resultEntity = facturaLogic.getFactura(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getTotal(), resultEntity.getTotal());
        Assert.assertEquals(entity.getSubtotal(), resultEntity.getSubtotal());
        Assert.assertEquals(entity.getFormaDePago(), resultEntity.getFormaDePago());
    }
    
    /**
     * Prueba para actualizar un Pago.
     *
     * @throws co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException
     */
    @Test
    public void updateFacturaTest() throws BusinessLogicException {
        FacturaEntity entity = data.get(0);
        FacturaEntity pojoEntity = factory.manufacturePojo(FacturaEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setTotal(123);
        pojoEntity.setSubtotal(123);
        facturaLogic.updateFactura(pojoEntity.getId(), pojoEntity);
        FacturaEntity resp = em.find(FacturaEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getTotal(), resp.getTotal());
        Assert.assertEquals(pojoEntity.getSubtotal(), resp.getSubtotal());
        Assert.assertEquals(pojoEntity.getFormaDePago(), resp.getFormaDePago());
        
        
    }
    
    
    
    /**
     * Prueba para eliminar un Pago.
     *
     * @throws co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException
     */
    @Test
    public void deleteFacturaTest() throws BusinessLogicException {
        FacturaEntity entity = data.get(0);
        facturaLogic.deleteFactura(entity.getId());
        FacturaEntity deleted = em.find(FacturaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
