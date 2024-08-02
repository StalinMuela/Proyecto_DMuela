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

    private void crearAdminUser() {
        // Configuración de la conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/miaulaesfot";
        String user = "root";
        String password = "123456";

        // Datos del usuario admin
        String adminUsername = "DavidAdmin";
        String adminPlainPassword = "admin";
        String adminHashedPassword = BCrypt.hashpw(adminPlainPassword, BCrypt.gensalt());

        // Verificar si el usuario ya existe
        if (usuarioExiste(adminUsername)) {
            System.out.println("El usuario administrador fue creado.");
            return; // Salir del método si el usuario ya existe
        }

        // Consulta para insertar el usuario admin en la base de datos
        String query = "INSERT INTO sesionadministrador (useradmin , passadmin) VALUES (?,?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Configuración de los parámetros de la consulta
            stmt.setString(1, adminUsername);
            stmt.setString(2, adminHashedPassword);

            // Ejecución de la consulta
            stmt.executeUpdate();
            System.out.println("Usuario admin creado exitosamente.");

        } catch (SQLException e) {
            // Manejo de errores
            e.printStackTrace();
        }
    }

    private boolean usuarioExiste(String username) {
        String url = "jdbc:mysql://localhost:3306/miaulaesfot";
        String user = "root";
        String password = "123456";

        String query = "SELECT COUNT(*) FROM sesionadministrador WHERE useradmin = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

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

        INGRESARButtonESTUDIANTE.addActionListener(new ActionListener() {
            /**
             * Maneja el evento de clic en el botón de inicio de sesión del estudiante.
             * @param e Evento que permite mostrar el módulo de perfil del estudiante.
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
                                JOptionPane.showMessageDialog(null, "Incorrecto Usuario o Contraseña");
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


        registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Registro MiAulaEsfot");
                frame.setContentPane(new registro().panelregistro);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(200, 300);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
