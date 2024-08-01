package Login;

import Administrador.*;
import Estudiantes.PerfilEstudiante;
import Profesores.PerfilProfesores;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
/**
 * La clase {@code form1} es el inicio de sesión que permite a los usuarios
 * ingresar como estudiante, profesor o administrador.
 */
public class form1 {
    public JPanel panel1;
    private JComboBox comboBox1;
    private JPanel panelEstudiante;
    private JTextField userEstudiante;
    private JPasswordField passEstudiante;
    private JButton INGRESARButtonESTUDIANTE;
    private JPasswordField passAdministrador;
    private JButton INGRESARButtonADMINISTRADOR;
    private JPanel panelAdministrativo;
    private JTextField userAdministrador;
    private JPasswordField passProfesor;
    private JButton INGRESARButtonPROFESOR;
    private JPanel panelProfesor;
    private JTextField userProfesor;
    private static String nombreUsuario = "";
    private static String tipoUsuario = "";


    public form1() {
        // Configura el ActionListener para el ComboBox que cambia entre paneles de usuario
        comboBox1.addActionListener(new ActionListener() {
            /**
             * @param e es el evento que genera la seleccion de modulos
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                String eleccion = (String) comboBox1.getSelectedItem();
                switch (eleccion) {
                    case "Estudiante":
                        /**
                         * Muestra el panel de estudiante y oculta los paneles de profesor y administrativo.
                         * @param panelEstudiante es visible mientras los otros paneles no lo son.
                         * @return {@code true} se muestra el panel, {@code false} no se muestra.
                         */
                        panelEstudiante.setVisible(true);
                        panelProfesor.setVisible(false);
                        panelAdministrativo.setVisible(false);
                        break;
                    case "Profesor":
                        /**
                         * Muestra el panel de profesor y oculta los paneles de estudiante y administrativo.
                         * @param panelProfesor es visible mientras los otros paneles no lo son.
                         * @return {@code true} se muestra el panel, {@code false} no se muestra.
                         */
                        panelProfesor.setVisible(true);
                        panelEstudiante.setVisible(false);
                        panelAdministrativo.setVisible(false);
                        break;
                    case "Administrador":
                        /**
                         * Muestra el panel administrativo y oculta los paneles de estudiante y profesor.
                         * @param panelAdministrativo es visible mientras los otros paneles no lo son.
                         * @return {@code true} se muestra el panel, {@code false} no se muestra.
                         */
                        panelAdministrativo.setVisible(true);
                        panelProfesor.setVisible(false);
                        panelEstudiante.setVisible(false);
                        break;
                    default:
                        /**
                         * Manejo de un caso no esperado donde no se selecciona una opción válida.
                         * @return no se muestra ningún panel.
                         */
                        System.out.printf("ERROR");
                        break;
                }

            }
        });

        // Configuración del ActionListener para el ESTUDIANTE
        INGRESARButtonESTUDIANTE.addActionListener(new ActionListener() {
            /**
             *
             * @param e evento que permite mostrar MODULO LOGIN-ESTUDIANTE
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                // Configuración de la conexión a la base de datos
                String url = "jdbc:mysql://localhost:3306/miaulaesfot";
                String user = "root";
                String password = "123456";

                // Obtención de las credenciales del estudiante desde los campos de texto
                String userStudent = userEstudiante.getText();
                String passwordStudent = passEstudiante.getText();

                // Consulta SQL para verificar las credenciales del estudiante
                String queryestudent = "SELECT * FROM sesionestudiante WHERE userstudent = ? AND passstudente = ?";
                try(Connection connection = DriverManager.getConnection(url,user,password)){

                    // Preparación de la declaración SQL
                    PreparedStatement preparedStatement = connection.prepareStatement(queryestudent);
                    preparedStatement.setString(1, userStudent);
                    preparedStatement.setString(2, passwordStudent);

                    // Ejecución de la consulta y verificación de credenciales
                    if(preparedStatement.executeQuery().next()){
                        // Set global user info
                        nombreUsuario = userStudent;
                        tipoUsuario = "estudiante";

                        // Creación del modulo del perfil del estudiante
                        JFrame frame = new JFrame();
                        frame.setContentPane(new PerfilEstudiante().panelEstudiantes);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.pack();
                        frame.setVisible(true);

                        // Cierre del formulario de inicio de sesión
                        ((JFrame) SwingUtilities.getWindowAncestor(INGRESARButtonESTUDIANTE)).dispose();

                    }else{

                        // Manejo de credenciales incorrectas
                        JOptionPane.showMessageDialog(null, "Incorrecto Usuario o Contraseña");
                        userEstudiante.setText("");
                        passEstudiante.setText("");
                    }
                }catch (SQLException E){
                    // Manejo de excepciones SQL
                    E.printStackTrace();
                }

            }
        });

        // Configuración del ActionListener para el botón INGRESARButtonPROFESOR
        INGRESARButtonPROFESOR.addActionListener(new ActionListener() {
            /**
             * @param e evento que permite mostrar MODULO LOGIN-PROFESOR
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                // Configuración de la conexión a la base de datos
                String url = "jdbc:mysql://localhost:3306/miaulaesfot";
                String user = "root";
                String password = "123456";

                // Obtención de las credenciales del profesor desde los campos de texto
                String userTeacher = userProfesor.getText();
                String passTeacher = passProfesor.getText();

                // Consulta SQL para verificar las credenciales del profesor
                String query = "SELECT * FROM sesionprofesor WHERE userteacher = ? AND passteacher = ?";
                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    // Preparación de la declaración SQL
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, userTeacher);
                    preparedStatement.setString(2, passTeacher);

                    // Ejecución de la consulta y verificación de credenciales
                    if (preparedStatement.executeQuery().next()) {

                        // Establece la información del usuario global
                        nombreUsuario = userTeacher;
                        tipoUsuario = "profesor";

                        // Creación del módulo del perfil del profesor
                        JFrame panel = new JFrame("Perfil Profesor");
                        panel.setContentPane(new PerfilProfesores().perfilProfesor);
                        panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        panel.pack();
                        panel.setVisible(true);

                        // Cierre del formulario de inicio de sesión
                        ((JFrame) SwingUtilities.getWindowAncestor(INGRESARButtonPROFESOR)).dispose();

                    } else {
                        // Manejo de credenciales incorrectas
                        JOptionPane.showMessageDialog(null, "Incorrecto Usuario o Contraseña");
                        userProfesor.setText("");
                        passProfesor.setText("");
                    }
                } catch (SQLException E) {
                    // Manejo de excepciones SQL
                    E.printStackTrace();
                }
            }
        });

        // Configuración del ActionListener para el botón INGRESARButtonADMINISTRADOR
        INGRESARButtonADMINISTRADOR.addActionListener(new ActionListener() {
            /**
             * @param e evento que permite mostrar MODULO LOGIN-ADMINISTRADOR
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                // Configuración de la conexión a la base de datos
                String url = "jdbc:mysql://localhost:3306/miaulaesfot";
                String user = "root";
                String password = "123456";

                // Obtención de las credenciales del administrador desde los campos de texto
                String userAdmin = userAdministrador.getText();
                String passAdmin = passAdministrador.getText();

                // Consulta SQL para verificar las credenciales del administrador
                String query = "SELECT * FROM sesionadministrador WHERE useradmin = ? AND passadmin = ?";
                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    // Preparación de la declaración SQL
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, userAdmin);
                    preparedStatement.setString(2, passAdmin);

                    // Ejecución de la consulta y verificación de credenciales
                    if (preparedStatement.executeQuery().next()) {

                        // Establece la información del usuario global
                        nombreUsuario = userAdmin;
                        tipoUsuario = "administrador";

                        // Creación del módulo del perfil del administrador
                        JFrame frame = new JFrame("Perfil Administrador");
                        frame.setContentPane(new PerfilAdmin().paneladmin);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setSize(200, 300);
                        frame.pack();
                        frame.setVisible(true);

                        // Cierre del formulario de inicio de sesión
                        ((JFrame) SwingUtilities.getWindowAncestor(INGRESARButtonADMINISTRADOR)).dispose();

                    } else {
                        // Manejo de credenciales incorrectas
                        JOptionPane.showMessageDialog(null, "Incorrecto Usuario o Contraseña");
                        userAdministrador.setText("");
                        passAdministrador.setText("");
                    }
                } catch (SQLException E) {
                    // Manejo de excepciones SQL
                    E.printStackTrace();
                }
            }
        });

    }
}
