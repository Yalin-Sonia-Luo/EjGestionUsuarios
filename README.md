# APP_Gestion_Clientes
Prueba Tecnica Desarrollo de una Aplicación de Gestión de Clientes

Gestión de Usuarios - Aplicación Java con JPA, MySQL y Xamp.

El proyecto desarrollado en **Java** permite la gestion de usuarios mediante operaciones **CRUD**, **persistencia** (jakarta persistence API) con api y **MySql** como base de datos, a través de Xampp.


***Caractetisticas principales:***
  - Crear nuevos usuarios.
  - Validación de datos.
  - Modificar datos existentes.
  - Eliminar usuarios por ID.
  - Buscar usuarios por ciudad.
  - Conexión a base de datos mediante EntityManager y persistence.xml.

Tecnologías utilizadas

| Tecnología |    Descripción    |
| **Java 21 (JDK 21) **| Lenguaje principal |
| **JPA (Jakarta Persistence API)** | Mapeo objeto-relacional |
| **Hibernate** | Implementación de JPA |
| **MySQL** | Base de datos relacional |
| **XAMPP** | Servidor local para MySQL |
| **Maven** | Gestión de dependencias |
| **IntelliJ IDEA** | Entorno de desarrollo |


***Gestión de usuarios***
  0 - Salir
  1 - Crear usuario
  2 - Listar usuarios
  3 - Modificar usuario
  4 - Eliminar usuario
  5 - Buscar usuarios por ciudad


***Validaciones incluidas***
- El **nombre**, **apellido**, **correo**, **ciudad**, y **teléfono** son obligatorios.  
- El **correo** debe contener @.  
- La **fecha de nacimiento** debe seguir el formato *dd-MM-yyyy* y no puede ser futura.  
- Si un campo no se quiere modificar, se puede presionar **Enter** para mantener el valor actual.

***IMPORTANTE***
Conficguración de persistence.xml (ubicado en src/main/resources/META-INF/) con credenciales:

          <persistence xmlns="https://jakarta.ee/xml/ns/persistence" version="3.0">
              <persistence-unit name="PuClientes" transaction-type="RESOURCE_LOCAL">
                  <class>org.example.entities.Usuario</class>
          
                  <properties>
                      <property name="jakarta.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
                      <property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/gestionclientes?serverTimezone=UTC"/>
                      <property name="jakarta.persistence.jdbc.user" value="root"/>
                      <property name="jakarta.persistence.jdbc.password" value="tu_contraseña"/>
                      <property name="hibernate.hbm2ddl.auto" value="update"/>
                      <property name="hibernate.show_sql" value="true"/>
                  </properties>
              </persistence-unit>
          </persistence>

**Cómo usar**

  **1)** Clona o descarga este repositorio.

  **3)** Abre el proyecto en IntelliJ IDEA.

  **4)** Asegúrate de tener Java 17+ instalado y configurado.

  **5)** Arranca XAMPP y activa MySQL.

  **6)** Verifica o modifica las credenciales en persistence.xml.

  **7)** Ejecuta la clase Main.java.

  **8)** Usa el menú en consola para interactuar con la base de datos.


***Proyecto desarrollado como práctica educativa de persistencia con JPA, Hibernate y MySQL (XAMPP).
Realizado en IntelliJ IDEA para aprender sobre gestión de entidades, consultas JPQL y transacciones.***

  
