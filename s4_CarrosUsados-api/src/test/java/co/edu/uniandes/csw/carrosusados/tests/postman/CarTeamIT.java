/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.carrosusados.tests.postman;

import co.edu.uniandes.csw.carrosusados.dtos.AdministradorDTO;
import co.edu.uniandes.csw.carrosusados.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.carrosusados.resources.AdministradorResource;
import co.edu.uniandes.csw.postman.tests.PostmanTestBuilder;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ISIS2603
 */
@RunWith(Arquillian.class)
public class CarTeamIT {

    private int sumaPeticiones;

    @Deployment(testable = true)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "s4_carTeam-api.war")//War del modulo api
                // Se agrega las dependencias
                .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
                        .importRuntimeDependencies().resolve()
                        .withTransitivity().asFile())
                // Se agregan los compilados de los paquetes de servicios
                .addPackage(AdministradorResource.class.getPackage()) //No importa cual recurso usar, lo importante es agregar el paquet
                .addPackage(AdministradorDTO.class.getPackage()) //No importa cual dto usar, lo importante es agregar el paquete.
                .addPackage(BusinessLogicExceptionMapper.class.getPackage())
                // El archivo que contiene la configuracion a la base de datos.
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                // El archivo beans.xml es necesario para injeccion de dependencias.
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                // El archivo web.xml es necesario para el despliegue de los servlets
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"))
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/glassfish-resources.xml"));
    }

    @Test
    @RunAsClient
    public void postman() throws IOException {
        File[] colecciones = new File(System.getProperty("user.dir").concat("\\collections")).listFiles();
        for (File coleccion : colecciones) {
            if (!coleccion.getName().contains("postman_environment")) {
                PostmanTestBuilder tp = new PostmanTestBuilder();
                tp.setTestWithoutLogin(coleccion.getName().replaceFirst(".json", ""), "CarTeam.postman_environment");
                String desiredResult = "0";
                String nombre = coleccion.getName().replaceFirst(".postman_environment.json", "");

                Assert.assertEquals("Error en Iterations de: " + nombre, desiredResult, tp.getIterations_failed());

                Assert.assertEquals("Error en Requests de: " + nombre, desiredResult, tp.getRequests_failed());

                Assert.assertEquals("Error en Test-Scripts de: " + nombre, desiredResult, tp.getTest_scripts_failed());

                Assert.assertEquals("Error en Prerequest-Scripts de: " + nombre, desiredResult, tp.getPrerequest_scripts_failed());

                Assert.assertEquals("Error en Assertions de: " + nombre, desiredResult, tp.getAssertions_failed());

                sumaPeticiones += Integer.parseInt(tp.getTotal_Requests());
            }
        }
    }

    @After
    public void totalPeticiones() {
        Logger.getLogger(CarTeamIT.class.getName()).log(Level.INFO, "TOTAL-PETICIONES: {0}", sumaPeticiones);
    }
}