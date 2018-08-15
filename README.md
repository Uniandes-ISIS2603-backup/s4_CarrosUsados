# Cascara Back
ejemplo:
nombre = escarabajos
palabraclave = bicicletas
sX = s<NumeroSeccion>

# General:
1. Proyecto Principal - Click Derecho - Rename, cambiar las 3 cosas(la carpeta es sX_palabraclave).

Obs: los modulos del pom principal corresponden a sus carpetas.

# Back
2. Modulo Back  - Click Derecho - Rename, cambiar las 3 cosas(la carpeta es sX_palabraclave-back)
3. POM Back - Cambiar artifactId del parent (linea 9) 
4. Cambiar el persistence.xml principal - En netbeans: Other Sources/src/main/resources/Meta-INF/persistence.xml 
persistence-unit con mayuscula (linea 5), jta-data-source (linea 7), javax.persistence.jdbc.url (linea 15)
5. Cambiar el persistence.xml de los test - En netbeans: Other Test Sources / src/test/resources/Meta-INF/persistence 
persistence-unit con mayuscula (linea 5), javax.persistence.jdbc.url (linea 13)
6. Cambiar los paquetes - Click derecho en cada uno - Refactor - Rename
(Si cambian el nombre de la carpeta toca cambiar el package en cada clase.)

# API
7. Modulo API - Click Derecho Rename, cambiar las 3 cosas(la carpeta es palabraclave-api)
8. POM API - Cambiar artifactId del parent (linea 9) , cambiar dependencia al back (linea 26)
9. Index - Hello World! nombre con mayuscula (linea 8)
10. Glassfish-resources - connection-pool (linea 4),databaseName (linea 21), jdbc-resource+jndi-name linea 25
11. Cambiar los paquetes - Click derecho en cada uno - Refactor - rename.
12. Nombre del archivo .sql

13. Verificar que todo este bien: CTRL+Shift+F y buscar nombre/palabraclave en todos los proyectos.
14. ReadMe Remplazar esto por # Nombre
