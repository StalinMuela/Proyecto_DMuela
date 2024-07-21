package Login;

import Administrador.*;
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
    private JButton INGRESARButtonESTUDIANTE;
    private JPasswordField passAdministrador;
    private JButton INGRESARButtonADMINISTRADOR;
    private JPanel panelAdministrativo;
    private JTextField userAdministrador;
    private JPasswordField passProfesor;
    private JButton INGRESARButtonPROFESOR;
    private JPanel panelProfesor;
    private JTextField userProfesor;

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


        INGRESARButtonESTUDIANTE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/miaulaesfot";
                String user = "root";
                String password = "123456";

                String userStudent = userEstudiante.getText();
                String passwordStudent = passEstudiante.getText();

                String queryestudent = "SELECT * FROM sesionestudiante WHERE userstudent = ? AND passstudente = ?";
                try(Connection connection = DriverManager.getConnection(url,user,password)){
                    PreparedStatement preparedStatement = connection.prepareStatement(queryestudent);
                    preparedStatement.setString(1, userStudent);
                    preparedStatement.setString(2, passwordStudent);

                    if(preparedStatement.executeQuery().next()){
                        System.out.printf("Correcto");
                    }else{
                        JOptionPane.showMessageDialog(null, "Incorrecto Usuario o Contraseña");
                        userEstudiante.setText("");
                        passEstudiante.setText("");
                    }
                }catch (SQLException E){
                    E.printStackTrace();
                }

            }
        });
        INGRESARButtonPROFESOR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/miaulaesfot";
                String user = "root";
                String password = "123456";

                String userTeacher = userProfesor.getText();
                String passTeacher = passProfesor.getText();


                String query = "SELECT * FROM sesionprofesor WHERE userteacher = ? AND passteacher = ?";
                try(Connection connection = DriverManager.getConnection(url,user,password)){
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, userTeacher);
                    preparedStatement.setString(2, passTeacher);

                    if(preparedStatement.executeQuery().next()){
                        System.out.printf("Correcto");
                    }else{
                        JOptionPane.showMessageDialog(null, "Incorrecto Usuario o Contraseña");
                        userProfesor.setText("");
                        passProfesor.setText("");
                    }
                }catch (SQLException E){
                    E.printStackTrace();
                }
            }
        });
        INGRESARButtonADMINISTRADOR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/miaulaesfot";
                String user = "root";
                String password = "123456";

                String userAdmin = userAdministrador.getText();
                String passAdmin = passAdministrador.getText();


                String query = "SELECT * FROM sesionadministrador WHERE useradmin = ? AND passadmin = ?";
                try(Connection connection = DriverManager.getConnection(url,user,password)){
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, userAdmin);
                    preparedStatement.setString(2, passAdmin);

                    if(preparedStatement.executeQuery().next()){
                        System.out.printf("Correcto");
                        JFrame frame = new JFrame();
                        frame.setContentPane(new PerfilAdmin().paneladmin);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setSize(200,300);
                        frame.pack();
                        frame.setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(null, "Incorrecto Usuario o Contraseña");
                        userAdministrador.setText("");
                        passAdministrador.setText("");
                    }
                }catch (SQLException E){
                    E.printStackTrace();
                }
            }
        });
    }
}
