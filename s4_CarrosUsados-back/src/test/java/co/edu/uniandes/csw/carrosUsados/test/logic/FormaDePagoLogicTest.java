/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.test.logic;

import co.edu.uniandes.csw.carrosUsados.ejb.FormaDePagoLogic;
import co.edu.uniandes.csw.carrosUsados.entities.FormaDePagoEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.FormaDePagoPersistence;
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
public class FormaDePagoLogicTest {
    
   private PodamFactory factory = new PodamFactoryImpl();

   @Inject
   FormaDePagoLogic formaDePagoLogic;
    
   @PersistenceContext
   private EntityManager em;

   @Inject
   private UserTransaction utx;
   
    
   private List<FormaDePagoEntity> data = new ArrayList<FormaDePagoEntity>();
   /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FormaDePagoEntity.class.getPackage())
                .addPackage(FormaDePagoLogic.class.getPackage())
                .addPackage(FormaDePagoPersistence.class.getPackage())
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
        em.createQuery("delete from FormaDePagoEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        FormaDePagoEntity entity = factory.manufacturePojo(FormaDePagoEntity.class);
        em.persist(entity);
        data.add(entity);
        entity = factory.manufacturePojo(FormaDePagoEntity.class);
        em.persist(entity);
        data.add(entity);
        entity = factory.manufacturePojo(FormaDePagoEntity.class);
        em.persist(entity);
        data.add(entity);
        
    }
    
    
    /**
     * Prueba para crear un FormaDePago.
     */
    @Test
    public void createFormaDePagoTest() throws BusinessLogicException {
        
        FormaDePagoEntity newEntity = factory.manufacturePojo(FormaDePagoEntity.class);
            
        FormaDePagoEntity result = formaDePagoLogic.createFormaDePago(newEntity);
        Assert.assertNotNull(result);
        FormaDePagoEntity entity = em.find(FormaDePagoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
        Assert.assertEquals(newEntity.getNumero(), entity.getNumero());
        Assert.assertEquals(newEntity.getCodigo(), entity.getCodigo());
        Assert.assertEquals(newEntity.getFecha(), entity.getFecha());
                
        FormaDePagoEntity dupEntity = newEntity;
        
        try{
            newEntity.setNombre(null);            
            result = formaDePagoLogic.createFormaDePago(newEntity);
            Assert.fail();
        }
        catch(BusinessLogicException e){
            Assert.assertEquals(dupEntity.getId(),newEntity.getId());
            newEntity = dupEntity;
            Assert.assertEquals(dupEntity.getNombre(),newEntity.getNombre());
        }try{
            newEntity.setTipo(null);            
            result = formaDePagoLogic.createFormaDePago(newEntity);
            Assert.fail();
        }
        catch(BusinessLogicException e){            
            Assert.assertEquals(dupEntity.getId(),newEntity.getId());
            newEntity = dupEntity;
            Assert.assertEquals(dupEntity.getTipo(),newEntity.getTipo());
        }
        try{           
            newEntity.setNumero(null);
            result = formaDePagoLogic.createFormaDePago(newEntity);
            Assert.fail();
        }
        catch(BusinessLogicException e){            
            Assert.assertEquals(dupEntity.getId(),newEntity.getId());
            newEntity = dupEntity;
            Assert.assertEquals(dupEntity.getNumero(),newEntity.getNumero());
        }try{
            newEntity.setCodigo(null);            
            result = formaDePagoLogic.createFormaDePago(newEntity);
            Assert.fail();
        }
        catch(BusinessLogicException e){            
            Assert.assertEquals(dupEntity.getId(),newEntity.getId());
            newEntity = dupEntity;
            Assert.assertEquals(dupEntity.getCodigo(),newEntity.getCodigo());
        }
        try{        
            newEntity.setFecha(null);
            result = formaDePagoLogic.createFormaDePago(newEntity);
            Assert.fail();
        }
        catch(BusinessLogicException e){            
            Assert.assertEquals(dupEntity.getId(),newEntity.getId());
            newEntity = dupEntity;
            Assert.assertEquals(dupEntity.getFecha(),newEntity.getFecha());
        }
    }
    
    /**
     * Prueba para consultar la lista de FormaDePagoes
     */
    @Test
    public void getFormasDePagoTest() throws BusinessLogicException {
        List<FormaDePagoEntity> list = formaDePagoLogic.getFormasDePago();
        Assert.assertEquals(data.size(), list.size());
        for (FormaDePagoEntity entity : list) {
            boolean found = false;
            for (FormaDePagoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar un FormaDePago.
     */
    @Test
    public void getFormaDePagoTest() throws BusinessLogicException {
        FormaDePagoEntity entity = data.get(0);
        FormaDePagoEntity resultEntity = formaDePagoLogic.getFormaDePago(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(resultEntity.getId(), entity.getId());
        Assert.assertEquals(resultEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(resultEntity.getTipo(), entity.getTipo());
        
        try{        
            resultEntity = formaDePagoLogic.getFormaDePago(null);
            Assert.fail();
        }
        catch(BusinessLogicException e){}  
        resultEntity = formaDePagoLogic.getFormaDePago(-123L);
        Assert.assertNull(resultEntity);
    }
    
    /**
     * Prueba para actualizar un FormaDePago.
     */
    @Test
    public void updateFormaDePagoTest() throws BusinessLogicException {
        FormaDePagoEntity entity = data.get(0);
        FormaDePagoEntity pojoEntity = factory.manufacturePojo(FormaDePagoEntity.class);

        pojoEntity.setId(entity.getId());

        formaDePagoLogic.updateFormaDePago(pojoEntity.getId(), pojoEntity);
        

        FormaDePagoEntity resp = em.find(FormaDePagoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(pojoEntity.getTipo(), resp.getTipo());
        
        FormaDePagoEntity dupEntity = entity;
        
        try{
            entity.setNombre("");            
            formaDePagoLogic.updateFormaDePago(entity.getId(),entity);
            Assert.fail();
        }
        catch(BusinessLogicException e){
            Assert.assertEquals(dupEntity.getId(),entity.getId());
            entity = dupEntity;
            Assert.assertEquals(dupEntity.getNombre(),entity.getNombre());
        }try{
            entity.setNombre("");            
            formaDePagoLogic.updateFormaDePago(entity.getId(),entity);
            Assert.fail();
        }
        catch(BusinessLogicException e){            
            Assert.assertEquals(dupEntity.getId(),entity.getId());
            entity = dupEntity;
            Assert.assertEquals(dupEntity.getNombre(),entity.getNombre());
        }try{           
            formaDePagoLogic.updateFormaDePago(entity.getId(),entity);
            Assert.fail();
        }
        catch(BusinessLogicException e){            
            Assert.assertEquals(dupEntity.getId(),entity.getId());
            entity = dupEntity;
            Assert.assertEquals(dupEntity.getTipo(),entity.getTipo());
        }
    }
    
    /**
     * Prueba para eliminar un FormaDePago
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteFormaDePagoTest() throws BusinessLogicException {
        FormaDePagoEntity entity = data.get(0);
        formaDePagoLogic.deleteFormaDePago(entity.getId());
        FormaDePagoEntity deleted = em.find(FormaDePagoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    
}
