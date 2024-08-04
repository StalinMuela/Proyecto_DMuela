package Administrador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * La clase {@code borrarUserAdmin} una clase de administrador de ESFOT.
 * Su función principal es borrar estudiantes o profesores creados
 */
public class borrarUserAdmin {
    /**
     * Panel para borrar estudiantes o profesores en el perfil administrador
     */
    public JPanel panelborraruser;
    private JComboBox comboBox1;
    private JPanel borrarEstudiante;
    private JTextField estudianteborrar;
    private JButton BORRARESTUDIANTEButton;
    private JTextField profesorborrar;
    private JPanel borrarProfesor;
    private JButton BORRARPROFESORButton;
    private JButton REGRESARButton;


    //Crea unas constantes que permite la conexion con BASE DE DATOS
    private static final String url = "jdbc:mysql://localhost:3306/miaulaesfot";
    private static final String user = "root";
    private static final String password = "123456";


    public borrarUserAdmin() {
        // ActionListener para el botón comboBox1, para mostrar los paneles estudiante/profesor para BORRAR
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String option = (String) comboBox1.getSelectedItem();
                switch (option) {
                    case "Estudiante":
                        borrarEstudiante.setVisible(true);
                        borrarProfesor.setVisible(false);
                        break;
                    case "Profesor":
                        borrarEstudiante.setVisible(false);
                        borrarProfesor.setVisible(true);

                }
            }
        });


        // ActionListener para el botón REGRESARButton, para regresar a la pantalla anterior
        REGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Muestra el panel del perfil de administrador
                JFrame frame = new JFrame();
                frame.setContentPane(new PerfilAdmin().paneladmin);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(200,300);
                frame.pack();
                frame.setVisible(true);

                // Cierra el JFrame actual
                ((JFrame) SwingUtilities.getWindowAncestor(REGRESARButton)).dispose();

            }
        });

        // ActionListener para el botón BORRARESTUDIANTEButton, para BORRAR ESTUDIANTE
        BORRARESTUDIANTEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connection = null;
                PreparedStatement consulta = null;

                try {
                    // Establece conexión con la base de datos
                    connection = DriverManager.getConnection(url, user, password);

                    // Consulta SQL para borrar estudiante
                    String borrarestudiante = "DELETE FROM sesionestudiante WHERE userstudent = ?";
                    consulta = connection.prepareStatement(borrarestudiante);

                    // Verifica si el campo de texto está vacío
                    if (estudianteborrar.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "INGRESA VALORES");
                        return;
                    }

                    // Establece el parámetro de la consulta
                    consulta.setString(1, estudianteborrar.getText().trim());

                    // Ejecuta la consulta
                    int resultado = consulta.executeUpdate();
                    if (resultado > 0) {
                        JOptionPane.showMessageDialog(null, "Se ha eliminado el estudiante");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha encontrado el estudiante");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al eliminar el estudiante: " + ex.getMessage());
                } finally {
                    try {
                        if (consulta != null) {
                            consulta.close(); // Cierra la consulta
                        }
                        if (connection != null) {
                            connection.close(); // Cierra la conexión
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // ActionListener para el botón BORRARPROFESORButton, para BORRAR PROFESOR
        BORRARPROFESORButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connection = null;
                PreparedStatement consulta = null;

                try{
                    // Establece conexión con la base de datos
                    connection = DriverManager.getConnection(url,user,password);

                    // Consulta SQL para borrar estudiante
                    String borrarprofe = "DELETE FROM sesionprofesor WHERE userteacher = ?";
                    consulta = connection.prepareStatement(borrarprofe);

                    // Verifica si el campo de texto está vacío
                    if(profesorborrar.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"INGRESA VALORES");
                    }

                    // Establece el parámetro de la consulta
                    consulta.setString(1, profesorborrar.getText().trim());

                    // Ejecuta la consulta
                    int resultado = consulta.executeUpdate();
                    if(resultado>0){
                        JOptionPane.showMessageDialog(null,"Se ha eliminado el profesor");
                    }else{
                        JOptionPane.showMessageDialog(null,"No se ha eliminado el profesor");
                    }

                }catch (SQLException ex){
                    ex.printStackTrace();
                }finally {
                    try{
                        if(consulta != null){
                            consulta.close(); // Cierra la consulta
                        }
                        if(connection != null){
                            connection.close(); // Cierra la conexión
                        }
                    }catch (SQLException ex){
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
}
