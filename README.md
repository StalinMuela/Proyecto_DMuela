PROYECTO "MI AULA ESFOT" PROGRAMACION ORIENTADO A OBJECTOS

A) INSTALACIÓN

1. CONECTOR DE MYSQL PARA JAVA
   
        1. Conector de MySQL para Java
   
           1.1. Descarga del Conector:
           1.2. Visitar: https://dev.mysql.com/downloads/connector/j/ VERSION 9
           1.3. Instalación del Conector:
           1.4. Descomprimir el archivo ZIP descargado.
           1.5. Implementación en el Área de Trabajo:
           1.6. Abrir tu entorno de desarrollo (IDE) y navegar a la configuración del proyecto.
           1.7. Agregar el Conector:
           1.8. Ir a File > Project Structure.
           1.9. Seleccionar Libraries.
           1.10. Hacer clic en el botón + y buscar el archivo JAR del conector MySQL descomprimido.
           1.11. Agregar el archivo JAR y aplicar los cambios.
           1.12. Verificación de Instalación:
           1.13. Asegurarse de que el conector MySQL esté listado en las bibliotecas del proyecto.
           1.14. Realizar una prueba de conexion con la base de datos para verificar que el conector está funcionando correctamente.
   

3. ENCRIPTAR CLAVES

       2. Encriptador de Claves jBCrypt Versión 4.0
   
            2.1. Descarga e Implementación de jBCrypt:
            2.2. Agregar la Biblioteca al Proyecto:
            2.3. Abrir File > Project Structure.
            2.4. Hacer clic en el botón + y buscar jBCrypt 4.0
            2.5. Darle Ok y aplicar la libreria.
            2.6. Aplicar los cambios.
   

B) CONFIGURACIÓN

1. CONFIGURACION DEL PROYECTO
   
        1.1. Conectar BASE DE DATOS con JAVA
   
           Variables para realizar una coneccion con la BASE DE DATOS (MYSQL)
   
            String url  = "jdbc:mysql://localhost:3306/basedatosestudiantes";
            String user = "root";
            String password = "123456";
   
        1.2. Configuración de la Base de Datos:
   
          Crear una pequeño SCRIPT para verificar si existe CONECCIÓN
   
             try (Connection connection= DriverManager.getConnection(url,user,password)){
               System.out.println("BASE CONECTADA ");
             } catch (SQLException e1) {
               System.out.println(e1.getMessage());
             }
   


C) EJECUCIÓN


