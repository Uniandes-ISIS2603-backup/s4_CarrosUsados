/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.test.logic;

import co.edu.uniandes.csw.carrosUsados.ejb.FacturaLogic;
import co.edu.uniandes.csw.carrosUsados.entities.FacturaEntity;
import co.edu.uniandes.csw.carrosUsados.entities.FormaDePagoEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.FacturaPersistence;
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
 * @author juanestebanmendez
 */
@RunWith(Arquillian.class)
public class FacturaLogicTest {
    
   private PodamFactory factory = new PodamFactoryImpl();

   @Inject
   FacturaLogic facturaLogic;
    
   @PersistenceContext
   private EntityManager em;

   @Inject
   private UserTransaction utx;
   
    
   private List<FacturaEntity> data = new ArrayList<FacturaEntity>();
   
   private List<FormaDePagoEntity> formaDePagoData = new ArrayList<FormaDePagoEntity>();
   /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FacturaEntity.class.getPackage())
                .addPackage(FacturaLogic.class.getPackage())
                .addPackage(FacturaPersistence.class.getPackage())
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
        em.createQuery("delete from FacturaEntity").executeUpdate();
        em.createQuery("delete from FormaDePagoEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            FacturaEntity entity = factory.manufacturePojo(FacturaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            FormaDePagoEntity formaDePagoEntity = factory.manufacturePojo(FormaDePagoEntity.class);
            em.persist(formaDePagoEntity);
            formaDePagoData.add(formaDePagoEntity);
        }
    }
    
    
    /**
     * Prueba para crear un Factura.
     */
    @Test
    public void createFacturaTest() throws BusinessLogicException {
        
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        newEntity.setFormaDePago(formaDePagoData.get(0));
        newEntity.setTotal(123);
        newEntity.setSubtotal(123);
            
        FacturaEntity result = facturaLogic.createFactura(newEntity);
        Assert.assertNotNull(result);
        FacturaEntity entity = em.find(FacturaEntity.class, result.getId());
        
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getTotal(), entity.getTotal());
        Assert.assertEquals(newEntity.getSubtotal(), entity.getSubtotal());
        Assert.assertEquals(newEntity.getFormaDePago(), newEntity.getFormaDePago());
        
        FacturaEntity dupEntity = newEntity;
        
        try{
            newEntity.setProducto(null);            
            result = facturaLogic.createFactura(newEntity);
            Assert.fail();
        }
        catch(BusinessLogicException e){
            Assert.assertEquals(dupEntity.getId(),newEntity.getId());
            newEntity = dupEntity;
            Assert.assertEquals(dupEntity.getProducto(),newEntity.getProducto());
        }
        try{
            newEntity.setTotal(-3);            
            result = facturaLogic.createFactura(newEntity);
            Assert.fail();
        }
        catch(BusinessLogicException e){
            Assert.assertEquals(dupEntity.getId(),newEntity.getId());
            newEntity = dupEntity;
            Assert.assertEquals(dupEntity.getTotal(),newEntity.getTotal());
        }try{
            newEntity.setSubtotal(-2);            
            result = facturaLogic.createFactura(newEntity);
            Assert.fail();
        }
        catch(BusinessLogicException e){            
            Assert.assertEquals(dupEntity.getId(),newEntity.getId());
            newEntity = dupEntity;
            Assert.assertEquals(dupEntity.getSubtotal(),newEntity.getSubtotal());
        }try{
            newEntity.setFormaDePago(null);            
            result = facturaLogic.createFactura(newEntity);
            Assert.fail();
        }
        catch(BusinessLogicException e){
            Assert.assertEquals(dupEntity.getId(),newEntity.getId());
            newEntity = dupEntity;
            Assert.assertEquals(dupEntity.getFormaDePago(),newEntity.getFormaDePago());
        }
    }
    
    /**
     * Prueba para consultar la lista de Facturaes
     */
    @Test
    public void getFacturasTest() throws BusinessLogicException {
        List<FacturaEntity> list = facturaLogic.getFacturas();
        Assert.assertEquals(data.size(), list.size());
        for (FacturaEntity entity : list) {
            boolean found = false;
            for (FacturaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
        
    }
    
    /**
     * Prueba para consultar un Factura.
     */
    @Test
    public void getFacturaTest() throws BusinessLogicException {
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
        
        try{        
            pojoEntity = facturaLogic.getFactura(null);
            Assert.fail();
        }
        catch(BusinessLogicException e){  
        pojoEntity = facturaLogic.getFactura(-123L);
        Assert.assertNull(pojoEntity);
        }
              
        
    }
    
    /**
     * Prueba para actualizar un Factura.
     */
    @Test
    public void updateFacturaTest() throws BusinessLogicException {
        FacturaEntity entity = data.get(0);
        FacturaEntity pojoEntity = factory.manufacturePojo(FacturaEntity.class);

        pojoEntity.setId(entity.getId());
        pojoEntity.setFormaDePago(entity.getFormaDePago());
        pojoEntity.setTotal(124);
        pojoEntity.setSubtotal(124);

        facturaLogic.updateFactura(pojoEntity.getId(), pojoEntity);
        

        FacturaEntity resp = em.find(FacturaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getProducto(), resp.getProducto());
        Assert.assertEquals(pojoEntity.getTotal(), resp.getTotal());
        Assert.assertEquals(pojoEntity.getSubtotal(), resp.getSubtotal());
        Assert.assertEquals(pojoEntity.getFormaDePago(), resp.getFormaDePago()); 
        
        FacturaEntity dupEntity = entity;
        
        try{
            entity.setProducto(null);            
            facturaLogic.updateFactura(entity.getId(),entity);
            Assert.fail();
        }
        catch(BusinessLogicException e){
            Assert.assertEquals(dupEntity.getId(),entity.getId());
            entity = dupEntity;
            Assert.assertEquals(dupEntity.getProducto(),entity.getProducto());
        }try{
            entity.setTotal(-1);            
            facturaLogic.updateFactura(entity.getId(),entity);
            Assert.fail();
        }
        catch(BusinessLogicException e){            
            Assert.assertEquals(dupEntity.getId(),entity.getId());
            entity = dupEntity;
            Assert.assertEquals(dupEntity.getTotal(),entity.getTotal());
        }try{
            entity.setSubtotal(-2);            
            facturaLogic.updateFactura(entity.getId(),entity);
            Assert.fail();
        }
        catch(BusinessLogicException e){
            Assert.assertEquals(dupEntity.getId(),entity.getId());
            entity = dupEntity;
            Assert.assertEquals(dupEntity.getSubtotal(),entity.getSubtotal());
        }
    }
    
    /**
     * Prueba para eliminar un Factura
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteFacturaTest() throws BusinessLogicException {
        FacturaEntity entity = data.get(0);
        facturaLogic.deleteFactura(entity.getId());
        FacturaEntity deleted = em.find(FacturaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
