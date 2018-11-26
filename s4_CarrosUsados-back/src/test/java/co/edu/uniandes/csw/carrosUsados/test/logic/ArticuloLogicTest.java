/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.test.logic;

import co.edu.uniandes.csw.carrosUsados.ejb.ArticuloLogic;
import co.edu.uniandes.csw.carrosUsados.entities.ArticuloEntity;
import co.edu.uniandes.csw.carrosUsados.entities.AutomovilEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.ArticuloPersistence;
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
public class ArticuloLogicTest {
    
   private PodamFactory factory = new PodamFactoryImpl();

   @Inject
   ArticuloLogic articuloLogic;
    
   @PersistenceContext
   private EntityManager em;

   @Inject
   private UserTransaction utx;
   
    
   private List<ArticuloEntity> data = new ArrayList<ArticuloEntity>();
   
   private List<AutomovilEntity> automovilData = new ArrayList<AutomovilEntity>();
   /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ArticuloEntity.class.getPackage())
                .addPackage(ArticuloLogic.class.getPackage())
                .addPackage(ArticuloPersistence.class.getPackage())
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
        em.createQuery("delete from ArticuloEntity").executeUpdate();
        em.createQuery("delete from AutomovilEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ArticuloEntity entity = factory.manufacturePojo(ArticuloEntity.class);
            if(i == 0){
                entity.setPrecio("1000");}
            if(i == 1){
                entity.setPrecio("1500");}
            if(i == 2){
                entity.setPrecio("2000");}
            em.persist(entity);
            data.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            AutomovilEntity automovilEntity = factory.manufacturePojo(AutomovilEntity.class);
            em.persist(automovilEntity);
            automovilData.add(automovilEntity);
        }
    }
    
    
    /**
     * Prueba para crear un Articulo.
     */
    @Test
    public void createArticuloTest() throws BusinessLogicException {
        
        ArticuloEntity newEntity = factory.manufacturePojo(ArticuloEntity.class);
        newEntity.setAutomovil(automovilData.get(0));
        newEntity.setPrecio("50");
            
        ArticuloEntity result = articuloLogic.createArticulo(newEntity);
        Assert.assertNotNull(result);
        ArticuloEntity entity = em.find(ArticuloEntity.class, result.getId());
        
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getPrecio(), entity.getPrecio());
        Assert.assertEquals(newEntity.getAutomovil(), newEntity.getAutomovil());
        
        ArticuloEntity dupEntity = newEntity;
        
        try{
            newEntity.setAutomovil(null);            
            result = articuloLogic.createArticulo(newEntity);
            Assert.fail();
        }
        catch(BusinessLogicException e){
            Assert.assertEquals(dupEntity.getId(),newEntity.getId());
            newEntity = dupEntity;
            Assert.assertEquals(dupEntity.getAutomovil(),newEntity.getAutomovil());
        }
        try{
            newEntity.setDescripcion(null);            
            result = articuloLogic.createArticulo(newEntity);
            Assert.fail();
            
        }
        catch(BusinessLogicException e){
            Assert.assertEquals(dupEntity.getId(),newEntity.getId());
            newEntity = dupEntity;
            Assert.assertEquals(dupEntity.getDescripcion(),newEntity.getDescripcion());
        }try{
            newEntity.setPrecio(null);            
            result = articuloLogic.createArticulo(newEntity);
            Assert.fail();
        }
        catch(BusinessLogicException e){
            Assert.assertEquals(dupEntity.getId(),newEntity.getId());
            newEntity = dupEntity;
            Assert.assertEquals(dupEntity.getPrecio(),newEntity.getPrecio());
        }try{
            newEntity.setDisponibilidad(false);            
            result = articuloLogic.createArticulo(newEntity);
            Assert.fail();
        }
        catch(BusinessLogicException e){
            Assert.assertEquals(dupEntity.getId(),newEntity.getId());
            newEntity = dupEntity;
            Assert.assertEquals(dupEntity.isDisponibilidad(),newEntity.isDisponibilidad());
        }
    }
    
    /**
     * Prueba para consultar la lista de Articuloes
     */
    @Test
    public void getArticulosTest() throws BusinessLogicException {
        List<ArticuloEntity> list = articuloLogic.getArticulos();
        Assert.assertEquals(data.size(), list.size());
        for (ArticuloEntity entity : list) {
            boolean found = false;
            for (ArticuloEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
        
    }
    
    /**
     * Prueba para consultar un Articulo.
     */
    @Test
    public void getArticuloTest() throws BusinessLogicException {
        ArticuloEntity entity = data.get(0);
        ArticuloEntity resultEntity = articuloLogic.getArticulo(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(resultEntity.getId(), entity.getId());
        Assert.assertEquals(resultEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(resultEntity.getPrecio(), entity.getPrecio());
        Assert.assertEquals(resultEntity.getAutomovil(), resultEntity.getAutomovil());
        
        try{        
            resultEntity = articuloLogic.getArticulo(null);
            Assert.fail();
        }
        catch(BusinessLogicException e){  
            resultEntity = articuloLogic.getArticulo(-123L);
            Assert.assertNull(resultEntity);
        }
              
        
    }
    
    /**
     * Prueba para actualizar un Articulo.
     */
    @Test
    public void updateArticuloTest() throws BusinessLogicException {
        ArticuloEntity entity = data.get(0);
        ArticuloEntity pojoEntity = factory.manufacturePojo(ArticuloEntity.class);

        pojoEntity.setId(entity.getId());
        pojoEntity.setAutomovil(entity.getAutomovil());
        pojoEntity.setPrecio(entity.getPrecio());

        articuloLogic.updateArticulo(pojoEntity.getId(), pojoEntity);
        

        ArticuloEntity resp = em.find(ArticuloEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescripcion());
        Assert.assertEquals(pojoEntity.getPrecio(), resp.getPrecio());
        Assert.assertEquals(pojoEntity.getAutomovil(), resp.getAutomovil()); 
        
        ArticuloEntity dupEntity = entity;
        
        try{
            entity.setDescripcion("");            
            articuloLogic.updateArticulo(entity.getId(),entity);
            Assert.fail();
        }
        catch(BusinessLogicException e){
            Assert.assertEquals(dupEntity.getId(),entity.getId());
            entity = dupEntity;
            Assert.assertEquals(dupEntity.getDescripcion(),entity.getDescripcion());
        }try{
            entity.setPrecio("");            
            articuloLogic.updateArticulo(entity.getId(),entity);
            Assert.fail();
        }
        catch(BusinessLogicException e){
            Assert.assertEquals(dupEntity.getId(),entity.getId());
            entity = dupEntity;
            Assert.assertEquals(dupEntity.getPrecio(),entity.getPrecio());
        }try{
            entity.setDisponibilidad(false);            
            articuloLogic.updateArticulo(entity.getId(),entity);
            Assert.fail();
        }
        catch(BusinessLogicException e){
            Assert.assertEquals(dupEntity.getId(),entity.getId());
            entity = dupEntity;
            Assert.assertEquals(dupEntity.isDisponibilidad(),entity.isDisponibilidad());
        }
    }
    
    /**
     * Prueba para eliminar un Articulo
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteArticuloTest() throws BusinessLogicException {
        ArticuloEntity entity = data.get(0);
        articuloLogic.deleteArticulo(entity.getId());
        ArticuloEntity deleted = em.find(ArticuloEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
