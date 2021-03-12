# **Poyecto: responsemanager**

## **Descripción**

* Proyecto **SpringBoot**.

* Arquetipo **Maven**.

* Uso de Profiles de **Maven**.

* Pruebas unitarias con **Junit5**.

* Usa arquitectura **ortogonal** (Estilo arquitectónico reducido ya que el requerimiento no necesita aplicar todas las reglas).

## **Funcionamiento Base del Proyecto**

El proyecto expone un servicio (service) el cual recibe una lista de personas (PersonDTO) y la tarea principal es identificar si en ese grupo de personas hay celebridades, una celebridad es conocida por toda la gente, pero ellos no conocen a nadie.

### **Capas Ortogonales**

* **Servicio:** Capa encargada de exponer los servicios (podría ser como muestra el ejemplo una fachada, aquí se podrían exponer los servicios REST)

* **Aplicación:** Capa encargada de orquestar las peticiones a los objetos de negocio y su respectiva funcionalidad.

* **Negocio:** Capa encargada de almacenar las clases con la lógica de negocio (el objeto Assembler “limpiaría” el proceso que no describa las reglas de negocio).

* **Acceso a Datos:** Capa encargada de manejar las consultas a Base de Datos.

### **Nombrado de Clases/Iterfaces/Paquetes por Capa Ortogonal**

En este caso el proyecto es pequeño, no se ocupa el esquema completo

## **Nombrado de paquetes**

### ***main***

* com.mx.nombreempresa.nombreproyecto.capa
* com.mx.nombreempresa.nombreproyecto.patrondiseño

### ***test***

* com.mx.nombreempresa.nombreproyecto.test.capa
* com.mx.nombreempresa.nombreproyecto.test.patrondiseño

> Para proyectos de prueba el nombrado de paquetes se ve ligeramente modificado

## **Maven**

La configuración del archivo **pom.xml** para el proyecto puede variar según se requiera.

### **Configuración de perfiles (Profiles)**

Los perfiles en maven son utilizados para facilitar la configuración en el sistema, hacen los cambios más sencillos.

En el siguinete ejemplo se muestra la configuración de dos perfiles, local y producción (prod), esto sirve para la configuración entre ambientes (Ejemplo):

~~~~xml
    <profiles>
        <profile>
            <id>local</id>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
            <properties>
                <build.profile.id>local</build.profile.id>
            </properties>
        </profile>
        <profile>
            <id>prod</id>
            <properties>
                <build.profile.id>prod</build.profile.id>
            </properties>
        </profile>
    </profiles>
~~~~

> El perfil local queda como default `<activeByDefault>true</activeByDefault>`

En la raiz del proyecto se crea la carpeta profiles, esta contendrá las carpetas local y prod. En la sección de build del archivo **pom.xml** se idica esta configuración de carpetas:

~~~~xml
    <filters>
        <filter>profiles/${build.profile.id}/config.properties</filter>
    </filters>
~~~~

El archivo **config.properties** contendrá el nombre de las propiedades que serán sustituidas según el perfil seleccionado. Los archivos dentro de la carpeta main del proyecto que hagan referencia (con Expression Language) se verán modificados.

> Archivos de propiedades y xml son los que usaulemnte se verán afectados por esta configuración.
>
> En el caso del contenido en la carpeta **test** no se puede ver afectado por perfiles maven, se debe colocar la información en duro.

### **Generación de javadocs**

Se hace uso de un plugin el cual obliga a la generación adecuada de documentación en el proyecto.

~~~~xml
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-javadoc-plugin</artifactId>
        <version>3.1.0</version>
        <executions>
            <execution>
                <id>attach-javadocs</id>
                <goals>
                    <goal>jar</goal>
                </goals>
            </execution>
        </executions>
    </plugin>
~~~~

> Si se comenten errores al documentar no será posible generar el jar o war.
>
> Si todo se hace de maenra adecuada se generarán los jars de la aplicación, uno contendra el html con la documentación.

### **Ejecución de Pruebas Junit 5 en Maven**

Por la versión de Junit se debe agregar un plugin en el **pom.xml**, este asegura la ejecución de las pruebas en la ejecución de comandos **Maven**.

~~~~xml
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>2.19.1</version>
        <dependencies>
            <dependency>
                <groupId>org.junit.platform</groupId>
                <artifactId>junit-platform-surefire-provider</artifactId>
                <version>1.1.0</version>
            </dependency>
            <dependency>
                <groupId>org.junit.jupiter</groupId>
                <artifactId>junit-jupiter-engine</artifactId>
                <version>5.1.0</version>
            </dependency>
        </dependencies>
    </plugin>
~~~~

>**Nota:** esto es necesario para versiones viejas.

## **Junit 5**

>Junit versión 5 contiene diferentes mejoras para versiones de java 8 o superiores.
>
>Como mínimo requiere java 8 para operar.
>
>Soporta pruebas para los estilos de Junit 3 y Junit4.

## Sonarqube Command

Comando para ejecutar reporte en sonarqube (local):

~~~~cmd
    mvn sonar:sonar \
    -Dsonar.projectKey=celebritymvn \
    -Dsonar.host.url=http://localhost:9000 \
    -Dsonar.login=730bbdc62d1a45e44e5c700745a2c6d846682594
~~~~
