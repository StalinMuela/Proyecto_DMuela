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


2. ORGANIZACION DEL PROYECTO


![image](https://github.com/user-attachments/assets/d8618c33-4afe-40d3-bd8f-61191b9feed9)

Se planteó la clasificación de los módulos, organizándolos para un entendimiento claro y específico. Los módulos se dividen en las siguientes categorías:

      Administrador: Contiene el CRUD para aulas y laboratorios, así como el CRUD de usuarios.
      Clase: Incluye los atributos relacionados con las clases y laboratorios.
      Estudiante: Maneja las acciones relacionadas con los estudiantes.
      Login: Se encarga del inicio de sesión.
      Profesores: Contiene las acciones específicas para los profesores.
      Imagenes: Contiene las imagenes requeridas para el proyecto.
      Base de datos: Donde se almacena la estructura y la información relevante del sistema.

3. MODELO DE LA BASE DE DATOS

   3.1 USUARIOS
   
         Se inicializa con 3 tablas adecuadas con los usuarios requeridos para el sistema
         - Estudiantes
         - Profesores
         - Administrador

      ![image](https://github.com/user-attachments/assets/68cb699f-6496-45dd-8d17-90a535581a99)

   3.2 AULAS
   

         Se inicializa la tabla para la reserva de aulas la cual requiere los siguientes atributos
      ![image](https://github.com/user-attachments/assets/45bfbbbe-26a7-4d7c-bb95-c2b91a1f9984)


   3.3 LABORATORIOS
   
         Se inicializa la tabla para la reserva de laboratorios la cual requiere los siguientes atributos

      ![image](https://github.com/user-attachments/assets/64a82b08-6566-4636-833c-420586bbfff8)



C) EJECUCIÓN

1. USO
   
         1. Login
         Los usuarios pueden acceder al sistema con diferentes roles:

   ![image](https://github.com/user-attachments/assets/5277434c-47f0-4cf5-9328-9d7f869f5f9a)

         
         1.1. Administrador
         
         Funcionalidades:
         Gestionar todos los aspectos del sistema, incluyendo la administración de usuarios
         Gestión de aulas y laboratorios (Permite Crear, Eliminar, Visualizar)

         1.1.1. AULAS Y LABORATORIOS
         

   ![image](https://github.com/user-attachments/assets/d50c1838-bc77-472c-909b-aac715aaed8f)

   ![image](https://github.com/user-attachments/assets/60c86030-d915-4ab0-8998-ee10160ddb56)

   ![image](https://github.com/user-attachments/assets/a61c8293-1961-4a6f-a27b-a073de64ea2c)


         1.1.2. USUARIOS

   ![image](https://github.com/user-attachments/assets/948a3da2-3677-4dba-aec2-0ca3029c6c34)

   ![image](https://github.com/user-attachments/assets/b08d25e2-ab49-492d-8134-aeb93464ee06)

   ![image](https://github.com/user-attachments/assets/08884b08-5d98-49fa-828a-f7bf728d89a2)


         1.2. Profesor
         
         Funcionalidades:
         Realizar reservas de aulas y laboratorios para su uso.
         Consultar la disponibilidad de estos espacios.
         Efectuar reservas según sus necesidades y cancelar reservas si es necesario.

   ![image](https://github.com/user-attachments/assets/7978b08e-6de4-4dea-bae2-6daadd4cf904)


         1.3. Estudiante
         
         Funcionalidades:
         Reservar aulas para sus actividades académicas.
         Ver la disponibilidad de las aulas y realizar reservas según sus requerimientos
         Cancelar aulas que ya no sean necesarias.
   
   ![image](https://github.com/user-attachments/assets/8ab82c32-3c3a-47be-836b-c7110a013230)

         2. Funcionalidades
         
         2.1. Administrador
         
         Gestionar usuarios:
         Crear nuevos perfiles de usuario en el sistema.
         Eliminar usuarios que ya no requieran acceso al sistema.
         Gestionar aulas y laboratorios:
         Añadir nuevas aulas y laboratorios al sistema.
         Visualizar la información de los espacios existentes.
         Eliminar espacios que ya no estén en uso o que necesiten ser retirados del sistema.
   
         2.2. Profesor
         
         Reservar aulas o laboratorios:
         Realizar reservas para aulas o laboratorios.
         Verificar la disponibilidad de estos espacios antes de efectuar la reserva.
         Cancelar aulas y laboratorios.

   
         2.3. Estudiantes
         
         Reservar aulas:
         Conocer la disponibilidad de aulas.
         Realizar reservas según sus requerimientos.
         Cancelar aulas.
