package Login;

import Administrador.*;
import Estudiantes.PerfilEstudiante;
import Profesores.PerfilProfesores;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
/**
 * La clase {@code form1} es el inicio de sesión que permite a los usuarios
 * ingresar como estudiante, profesor o administrador.
 */
public class form1 {
    /**
     * Panel para el incio de sesion de Mi Aula Esfot para acceder a los perfiles
     */
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
    private JButton registrar;
    private static String nombreUsuario = "";
    private static String tipoUsuario = "";

    //Crea unas constasten que permite la conexion con BASE DE DATOS
    private static final String url = "jdbc:mysql://localhost:3306/miaulaesfot";
    private static final String user = "root";
    private static final String password = "123456";

    /**
     * Crear una clase privada de  {@code crearAdminUser}
     * Permite crear un cuenta de ADMINISTRADOR
     */
    private void crearAdminUser() {
        String adminUsername = "User";
        String adminPassword = "usuario";
        String adminHashedPassword = BCrypt.hashpw(adminPassword, BCrypt.gensalt());

        String queryCheck = "SELECT COUNT(*) FROM sesionadministrador WHERE useradmin = ?";
        String queryInsert = "INSERT INTO sesionadministrador (useradmin, passadmin) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmtCheck = conn.prepareStatement(queryCheck);
             PreparedStatement pstmtInsert = conn.prepareStatement(queryInsert)) {

            // Verificar si el usuario ya existe
            pstmtCheck.setString(1, adminUsername);
            try (ResultSet rs = pstmtCheck.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    System.out.println("El usuario administrador ya existe.");
                    return;
                }
            }

            // Insertar el nuevo usuario
            pstmtInsert.setString(1, adminUsername);
            pstmtInsert.setString(2, adminHashedPassword);
            pstmtInsert.executeUpdate();
            System.out.println("Usuario admin creado exitosamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor de la clase {@code form1}
     * Configura los botones y sus respectivos eventos
     */
    public form1() {
        crearAdminUser();
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


        // ActionListener para el botón INGRESARButtonESTUDIANTE, permite mostrar el módulo de perfil del estudiante.
        INGRESARButtonESTUDIANTE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Configuración de la conexión a la base de datos

                // Obtención de las credenciales del estudiante desde los campos de texto
                String userStudent = userEstudiante.getText();
                String passwordStudent = passEstudiante.getText();

                // Consulta SQL para verificar las credenciales del estudiante
                String queryestudent = "SELECT passstudente FROM sesionestudiante WHERE userstudent = ?";
                try (Connection connection = DriverManager.getConnection(url, user, password);
                     PreparedStatement preparedStatement = connection.prepareStatement(queryestudent)) {

                    preparedStatement.setString(1, userStudent);
                    try (ResultSet rs = preparedStatement.executeQuery()) {
                        if (rs.next()) {
                            String hashedPassword = rs.getString("passstudente");
                            if (BCrypt.checkpw(passwordStudent, hashedPassword)) {
                                // Configuración de la sesión del usuario
                                nombreUsuario = userStudent;
                                tipoUsuario = "estudiante";

                                // Creación del módulo del perfil del estudiante
                                JFrame frame = new JFrame();
                                frame.setContentPane(new PerfilEstudiante().panelEstudiantes);
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.pack();
                                frame.setVisible(true);

                                // Cierre del formulario de inicio de sesión
                                ((JFrame) SwingUtilities.getWindowAncestor(INGRESARButtonESTUDIANTE)).dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                                userEstudiante.setText("");
                                passEstudiante.setText("");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Usuario no encontrado");
                            userEstudiante.setText("");
                            passEstudiante.setText("");
                        }
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // ActionListener para el botón INGRESARButtonESTUDIANTE, permite mostrar MODULO LOGIN-PROFESOR
        INGRESARButtonPROFESOR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtención de las credenciales del profesor desde los campos de texto
                String userTeacher = userProfesor.getText();
                String passTeacher = passProfesor.getText();

                // Consulta SQL para verificar las credenciales del profesor
                String query = "SELECT passteacher FROM sesionprofesor WHERE userteacher = ?";
                try (Connection connection = DriverManager.getConnection(url, user, password);
                     PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                    preparedStatement.setString(1, userTeacher);
                    try (ResultSet rs = preparedStatement.executeQuery()) {
                        if (rs.next()) {
                            String hashedPassword = rs.getString("passteacher");
                            if (BCrypt.checkpw(passTeacher, hashedPassword)) {
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
                                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                                userProfesor.setText("");
                                passProfesor.setText("");
                            }
                        } else {
                            // Manejo de usuario no encontrado
                            JOptionPane.showMessageDialog(null, "Usuario no encontrado");
                            userProfesor.setText("");
                            passProfesor.setText("");
                        }
                    }
                } catch (SQLException ex) {
                    // Manejo de excepciones SQL
                    ex.printStackTrace();
                }
            }
        });

        // ActionListener para el botón INGRESARButtonADMINISTRADOR, permite mostrar MODULO LOGIN-ADMINISTRADOR
        INGRESARButtonADMINISTRADOR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Obtención de las credenciales del administrador desde los campos de texto
                String userAdmin = userAdministrador.getText();
                String passAdmin = passAdministrador.getText();

                // Consulta SQL para verificar las credenciales del administrador
                String query = "SELECT passadmin FROM sesionadministrador WHERE useradmin = ?";
                try (Connection connection = DriverManager.getConnection(url, user, password);
                     PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                    preparedStatement.setString(1, userAdmin);
                    try (ResultSet rs = preparedStatement.executeQuery()) {
                        if (rs.next()) {
                            String hashedPassword = rs.getString("passadmin");
                            if (BCrypt.checkpw(passAdmin, hashedPassword)) {
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
                        } else {
                            // Manejo de usuario no encontrado
                            JOptionPane.showMessageDialog(null, "Usuario no encontrado");
                            userAdministrador.setText("");
                            passAdministrador.setText("");
                        }
                    }

                } catch (SQLException ex) {
                    // Manejo de excepciones SQL
                    ex.printStackTrace();
                }
            }
        });

        // ActionListener para el botón registrar, permite ingresar para CREAR UN REGISTRO
        registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Registro MiAulaEsfot");
                frame.setContentPane(new registro().panelregistro);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(200, 300);
                frame.pack();
                frame.setVisible(true);

                ((JFrame) SwingUtilities.getWindowAncestor(registrar)).dispose();

            }
        });
    }
}
