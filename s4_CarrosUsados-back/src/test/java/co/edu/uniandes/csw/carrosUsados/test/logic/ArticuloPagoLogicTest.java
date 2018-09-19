/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.test.logic;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import co.edu.uniandes.csw.carrosUsados.ejb.ArticuloPagoLogic;
import co.edu.uniandes.csw.carrosUsados.ejb.ArticuloLogic;
import co.edu.uniandes.csw.carrosUsados.entities.ArticuloEntity;
import co.edu.uniandes.csw.carrosUsados.entities.PagoEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.ArticuloPersistence;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class ArticuloPagoLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ArticuloPagoLogic articuloPagoLogic;

    @Inject
    private ArticuloLogic articuloLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ArticuloEntity> data = new ArrayList<ArticuloEntity>();

    private List<PagoEntity> pagoData = new ArrayList();

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
        em.createQuery("delete from PagoEntity").executeUpdate();
        em.createQuery("delete from ArticuloEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ArticuloEntity entity = factory.manufacturePojo(ArticuloEntity.class);
            em.persist(entity);
            data.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            PagoEntity pago = factory.manufacturePojo(PagoEntity.class);
            em.persist(pago);
            pagoData.add(pago);            
            if (i == 0) {
                data.get(i).setPago(pago);
            }
            if(i==1){
                data.get(i).setPago(pago);
            }
        }
    }

    /**
     * Prueba para asociar un Pago existente a una Articulo.
     */
    @Test
    public void addPagosTest() {
        ArticuloEntity entity = data.get(2);
        PagoEntity pagoEntity = pagoData.get(2);
        PagoEntity response = articuloPagoLogic.addPago(pagoEntity.getId(), entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(pagoEntity.getId(), response.getId());
    }

    /**
     * Prueba para remplazar la instancia de un Pago asociada a un Articulo
     */
    @Test
    public void replacePagoTest() throws BusinessLogicException {
        ArticuloEntity entity = data.get(2);
        entity = articuloPagoLogic.replacePago(entity.getId(), pagoData.get(2).getId());
        
        Assert.assertEquals(pagoData.get(2),entity.getPago());
    } 
    
}
