import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class form1 {
    public JPanel panel1;
    private JComboBox comboBox1;
    private JPanel panelEstudiante;
    private JTextField userEstudiante;
    private JPasswordField passEstudiante;
    private JButton INGRESARButton;
    private JPasswordField passwordField2;
    private JButton INGRESARButton2;
    private JPanel panelAdministrativo;
    private JTextField textField2;
    private JPasswordField passwordField3;
    private JButton INGRESARButton1;
    private JPanel panelProfesor;
    private JTextField textField3;

    public form1() {
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String eleccion = (String) comboBox1.getSelectedItem();
                switch (eleccion) {
                    case "Estudiante":
                        panelEstudiante.setVisible(true);
                        panelProfesor.setVisible(false);
                        panelAdministrativo.setVisible(false);
                        break;
                    case "Profesor":
                        panelProfesor.setVisible(true);
                        panelEstudiante.setVisible(false);
                        panelAdministrativo.setVisible(false);
                        break;
                    case "Administrador":
                        panelAdministrativo.setVisible(true);
                        panelProfesor.setVisible(false);
                        panelEstudiante.setVisible(false);
                        break;
                    default:
                        System.out.printf("ERROR");
                        break;
                }

            }
        });
        INGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/miaulaesfot";
                String user = "root";
                String password = "123456";

                String userStudent = userEstudiante.getText();
                String passwordStudent = passEstudiante.getText();

                String query = "SELECT * FROM sesionEstudiante WHERE user = ? AND password = ?";
                try(Connection connection = DriverManager.getConnection(url,user,password)){
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, userStudent);
                    preparedStatement.setString(2, passwordStudent);

                    if(preparedStatement.executeQuery().next()){
                        System.out.printf("Correcto");
                    }else{
                        System.out.printf("Error");
                    }

                    System.out.printf("CONECTAR BASE");
                }catch (SQLException E){
                    E.printStackTrace();
                }

            }
        });
    }
}
