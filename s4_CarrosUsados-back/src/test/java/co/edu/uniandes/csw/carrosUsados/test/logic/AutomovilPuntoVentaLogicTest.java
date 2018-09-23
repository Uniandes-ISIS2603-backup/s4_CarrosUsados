/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.test.logic;

import co.edu.uniandes.csw.carrosUsados.ejb.AutomovilFichaTecnicaLogic;
import co.edu.uniandes.csw.carrosUsados.ejb.AutomovilLogic;
import co.edu.uniandes.csw.carrosUsados.ejb.AutomovilPuntoVentaLogic;
import co.edu.uniandes.csw.carrosUsados.ejb.PuntoVentaLogic;
import co.edu.uniandes.csw.carrosUsados.entities.AutomovilEntity;
import co.edu.uniandes.csw.carrosUsados.entities.FichaTecnicaEntity;
import co.edu.uniandes.csw.carrosUsados.entities.PuntoVentaEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.AutomovilPersistence;
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
public class AutomovilPuntoVentaLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private AutomovilLogic automovilLogic;
    
    @Inject
    private AutomovilPuntoVentaLogic automovilPuntoVentaLogic;
    
    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<AutomovilEntity> data = new ArrayList<AutomovilEntity>();

    private List<PuntoVentaEntity> puntoVentaData = new ArrayList<PuntoVentaEntity>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AutomovilEntity.class.getPackage())
                .addPackage(PuntoVentaLogic.class.getPackage())
                .addPackage(AutomovilPersistence.class.getPackage())
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
        em.createQuery("delete from AutomovilEntity").executeUpdate();
        em.createQuery("delete from PuntoVentaEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            AutomovilEntity automoviles = factory.manufacturePojo(AutomovilEntity.class);
            em.persist(automoviles);
            data.add(automoviles);
        }
        for (int i = 0; i < 3; i++) {
            PuntoVentaEntity entity = factory.manufacturePojo(PuntoVentaEntity.class);
            em.persist(entity);
            puntoVentaData.add(entity);
            if (i == 0) {
                data.get(i).setPuntoVenta(entity);
            }
            if(i==1){
                data.get(i).setPuntoVenta(entity);
            }
        }
    }
    
    /**
     * Prueba para remplazar la instancia de PuntoVenta asociada a un Automovil
     * @throws co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException
     */
    @Test
    public void replaceFichaTecnicaTest() throws BusinessLogicException {
        AutomovilEntity entity = data.get(0);
        automovilPuntoVentaLogic.replacePuntoVenta(entity.getId(), puntoVentaData.get(1).getId());
        entity = automovilLogic.getAutomovil(entity.getId());
        Assert.assertEquals(entity.getPuntoVenta(), puntoVentaData.get(1));
    }
}
