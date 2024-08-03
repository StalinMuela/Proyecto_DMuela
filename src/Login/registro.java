package Login;

import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La clase {@code registro} es una clase general de MiAulaESFOT.
 * Su función principal reservar aulas
 */
public class registro {
    public JPanel panelregistro;
    private JComboBox comboBox1;
    private JPanel registerStudent;
    private JTextField userestudiante;
    private JTextField passestudiante;
    private JButton registrarEstu;
    private JPasswordField verificarcontra;
    private JPanel registerTeacher;
    private JButton registrarProfe;
    private JPasswordField contraProfe;
    private JPasswordField verficarProfe;
    private JTextField usuarioProfe;
    private JButton REGRESARINICIOSESIONButton;

    //Crea unas constasten que permite la conexion con BASE DE DATOS
    private static final String url = "jdbc:mysql://localhost:3306/miaulaesfot";
    private static final String user = "root";
    private static final String password = "123456";

    /**
     * Constructor de la clase {@code registro}
     * Configura los botones y sus respectivos eventos
     */
    public registro() {

        // ActionListener para el botón comboBox1, para mostrar registro Estuidante o Profesor
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opcion = (String) comboBox1.getSelectedItem();
                switch (opcion) {
                    case "Estudiante":
                        //Permite mostrar el de registro de estudiantes mientras oculta el de registro profesores
                        registerStudent.setVisible(true);
                        registerTeacher.setVisible(false);
                        break;
                    case "Profesor":
                        //Permite ocultar el de registro de estudiantes mientras mustra el de registro profesores
                        registerStudent.setVisible(false);
                        registerTeacher.setVisible(true);

                }
            }
        });

        // ActionListener para el botón registrarEstu, para registrar Estuidante
        registrarEstu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String userEstudiante = userestudiante.getText();
                String passEstudiante = passestudiante.getText();
                String passConfirm = verificarcontra.getText(); // Confirmación de la contraseña

                if (userEstudiante.isEmpty() || passEstudiante.isEmpty() || passConfirm.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese todos los valores.");
                    return;
                }

                if (!passEstudiante.equals(passConfirm)) {
                    JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.");
                    return;
                }

                // Hashea la contraseña del estudiante
                String hashedPassword = BCrypt.hashpw(passEstudiante, BCrypt.gensalt());

                // Consultas SQL
                String checkSql = "SELECT COUNT(*) FROM sesionestudiante WHERE userstudent = ?";
                String insertSql = "INSERT INTO sesionestudiante (userstudent, passstudente) VALUES (?, ?)";

                try (Connection conn = DriverManager.getConnection(url, user, password);
                     PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                     PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

                    // Verificar si el usuario ya existe
                    checkStmt.setString(1, userEstudiante);
                    try (ResultSet rs = checkStmt.executeQuery()) {
                        if (rs.next() && rs.getInt(1) > 0) {
                            JOptionPane.showMessageDialog(null, "Usuario ya existente");
                            return;
                        }
                    }

                    // Insertar el nuevo usuario
                    insertStmt.setString(1, userEstudiante);
                    insertStmt.setString(2, hashedPassword);
                    insertStmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Usuario creado exitosamente.");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // ActionListener para el botón registrarProfe, para registrar Profesor
        registrarProfe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String userProfesor = usuarioProfe.getText();
                String passProfesor = contraProfe.getText();
                String validar = verficarProfe.getText();

                if (userProfesor.isEmpty() || passProfesor.isEmpty() || validar.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese todos los valores.");
                    return;
                }
                if (!passProfesor.equals(validar)) {
                    JOptionPane.showMessageDialog(null,"CONSTRASEÑAS NO COICIDEN");
                    return;
                }
                // Hashea la contraseña del estudiante
                String hashedPasswordd = BCrypt.hashpw(passProfesor, BCrypt.gensalt());

                // Consultas SQL
                String checkSql = "SELECT COUNT(*) FROM sesionprofesor WHERE userteacher = ?";

                String insertSql = "INSERT INTO sesionprofesor (userteacher, passteacher) VALUES (?, ?)";

                try (Connection connection = DriverManager.getConnection(url,user,password)){

                    PreparedStatement checkStmt = connection.prepareStatement(checkSql);
                    PreparedStatement insertStmt = connection.prepareStatement(insertSql);

                    // Verificar si el usuario ya existe
                    checkStmt.setString(1, userProfesor);
                    try(ResultSet rs = checkStmt.executeQuery()) {
                        if (rs.next() && rs.getInt(1) > 0) {
                            JOptionPane.showMessageDialog(null, "Usuario ya existente");
                            return;
                        }
                    }

                    //Insertar el nuevo usuario
                    insertStmt.setString(1, userProfesor);
                    insertStmt.setString(2, hashedPasswordd);
                    insertStmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Usuario creado exitosamente.");

                }catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });

        // ActionListener para el botón REGRESARINICIOSESIONButton, permite regresar AL INICIO DE SESION
        REGRESARINICIOSESIONButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Crea un panel que permite regresa al INICIO SE SESION
                JFrame frame = new JFrame("Inicio de Sesion MiAulaESFOT");
                frame.setContentPane(new form1().panel1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(200, 300);
                frame.pack();
                frame.setVisible(true);

                //Permite cerrar el panel
                ((JFrame) SwingUtilities.getWindowAncestor(REGRESARINICIOSESIONButton)).dispose();

            }
        });
    }
}
