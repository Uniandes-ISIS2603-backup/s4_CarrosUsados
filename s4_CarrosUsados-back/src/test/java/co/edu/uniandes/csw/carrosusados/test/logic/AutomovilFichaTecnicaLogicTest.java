/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.test.logic;

import co.edu.uniandes.csw.carrosusados.ejb.AutomovilFichaTecnicaLogic;
import co.edu.uniandes.csw.carrosusados.ejb.AutomovilLogic;
import co.edu.uniandes.csw.carrosusados.ejb.FichaTecnicaLogic;
import co.edu.uniandes.csw.carrosusados.entities.AutomovilEntity;
import co.edu.uniandes.csw.carrosusados.entities.FichaTecnicaEntity;
import co.edu.uniandes.csw.carrosusados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosusados.persistence.AutomovilPersistence;
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

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.ArrayList;
import java.util.List;

/**
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class AutomovilFichaTecnicaLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private AutomovilLogic automovilLogic;

    @Inject
    private AutomovilFichaTecnicaLogic automovilFichaTecnicaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<AutomovilEntity> data = new ArrayList<AutomovilEntity>();

    private List<FichaTecnicaEntity> fichaTecnicaData = new ArrayList<FichaTecnicaEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AutomovilEntity.class.getPackage())
                .addPackage(FichaTecnicaLogic.class.getPackage())
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
        em.createQuery("delete from FichaTecnicaEntity").executeUpdate();
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
            FichaTecnicaEntity entity = factory.manufacturePojo(FichaTecnicaEntity.class);
            em.persist(entity);
            fichaTecnicaData.add(entity);
            if (i == 0) {
                data.get(i).setFichaTecnica(entity);
            }
            if (i == 1) {
                data.get(i).setFichaTecnica(entity);
            }
        }
    }

    /**
     * Prueba para remplazar la instancia de FichaTecnica asociada a un Automovil
     */
    @Test
    public void replaceFichaTecnicaTest() throws BusinessLogicException {
        AutomovilEntity entity = data.get(0);
        automovilFichaTecnicaLogic.replaceFichaTecnica(entity.getId(), fichaTecnicaData.get(1).getId());
        entity = automovilLogic.getAutomovil(entity.getId());
        Assert.assertEquals(entity.getFichaTecnica(), fichaTecnicaData.get(1));
    }

    /**
     * Prueba para eliminar la instancia de FichaTecnica asociada a un Automovil
     */
    
    /*
    @Test
    public void removeFichaTecnicaTest() throws BusinessLogicException {
        AutomovilEntity entity = data.get(0);
        System.out.println("ID AUTOMOVIL: "+entity.getId());
        System.out.println("ID FICHA TECNICA ASOCIADA A AUTOMOVIL: "+entity.getFichaTecnica().getId());
        automovilFichaTecnicaLogic.removeFichaTecnica(entity.getId());
        entity = automovilLogic.getAutomovil(entity.getId());
        Assert.assertNull(entity.getFichaTecnica());
    }*/
}
