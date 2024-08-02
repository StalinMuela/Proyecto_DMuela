package Login;

import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class registro {
    public JPanel panelregistro;
    private JComboBox comboBox1;
    private JPanel registerStudent;
    private JTextField userestudiante;
    private JTextField passestudiante;
    private JButton button1;
    private JPasswordField verificarcontra;

    public registro() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/miaulaesfot";
                String user = "root";
                String password = "123456";

                String userEstudiante = userestudiante.getText();
                String passEstudiante = passestudiante.getText();
                String passConfirm = verificarcontra.getText(); // Confirmación de la contraseña

                if (userEstudiante.isEmpty() || passEstudiante.isEmpty()) {
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
    }
}
