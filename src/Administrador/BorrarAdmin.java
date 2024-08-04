package Administrador;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * La clase {@code BorrarAdmin} una clase de administrador de ESFOT.
 * Su función principal es borrar las aulas y laboratorios creados
 */
public class BorrarAdmin {
    /**
     * Panel para borrar aulas o laboratorios en el perfil administrador
     */
    public JPanel AdminBorrar;
    private JComboBox comboBox1;
    private JPanel BorrarLab;
    private JTextField labelab;
    private JButton BORRARButtonLAB;
    private JPanel BorrarAula;
    private JTextField labelaula;
    private JButton BORRARButtonAULA;
    private JButton REGRESARButton;

    //Crea unas constantes que permite la conexion con BASE DE DATOS
    private static final String url = "jdbc:mysql://localhost:3306/miaulaesfot";
    private static final String user = "root";
    private static final String password = "123456";

    /**
     * Constructor de la clase {@code BorrarAdmin}
     * Configura los botones y sus respectivos eventos
     */

    public BorrarAdmin() {
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eleccion = (String) comboBox1.getSelectedItem();
                switch (eleccion) {
                    case "Aula":
                        BorrarLab.setVisible(false); // Oculta el panel de Laboratorio
                        BorrarAula.setVisible(true); // Muestra el panel de Aula
                        break;
                    case "Laboratorio":
                        BorrarLab.setVisible(true); // Muestra el panel de Aula
                        BorrarAula.setVisible(false); // Oculta el panel de Laboratorio
                        break;
                    default:
                        System.out.printf("ERROR");
                        break;
                }
            }
        });

        // ActionListener para el botón BORRARButtonLAB, para borrar un Laboratorio
        BORRARButtonLAB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Connection connection = null;
                PreparedStatement consulta = null;

                try{
                    // Establece conexión con la base de datos
                    connection=DriverManager.getConnection(url,user,password);

                    // Consulta SQL para borrar laboratorio
                    String borrarlab = "DELETE FROM labreserva WHERE codigo  = ?";
                    consulta = connection.prepareStatement(borrarlab);

                    // Asigna el código del laboratorio a la consulta
                    consulta.setString(1, labelab.getText());

                    // Verifica si el campo de texto está vacío
                    if (labelab.getText().equals("")) {
                        JOptionPane.showMessageDialog(null,"INGRESA VALORES");
                    }
                    int intento = consulta.executeUpdate();
                    if(intento>0){
                        JOptionPane.showMessageDialog(null, "Borrado el Laboratorio");
                    }else{
                        JOptionPane.showMessageDialog(null, "No se pudo borrar el Laboratorio");
                    }


                }catch (SQLException A){
                    A.printStackTrace();
                }finally {
                    try {
                        if (consulta != null){
                            consulta.close(); // Cierra la consulta
                        }
                        if(connection!= null){
                            connection.close();  // Cierra la conexión
                        }
                    }catch (SQLException A){
                        A.printStackTrace();
                    }
                }
            }
        });

        // ActionListener para el botón BORRARButtonAULA, para borrar un Aula
        BORRARButtonAULA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Connection connection = null;
                PreparedStatement consulta = null;

                try{
                    // Establece conexión con la base de datos
                    connection=DriverManager.getConnection(url,user,password);

                    // Consulta SQL para borrar aula
                    String borrarlab = "DELETE FROM aulasreserva WHERE codigo  = ?";
                    consulta = connection.prepareStatement(borrarlab);
                    // Asigna el código del aula a la consulta
                    consulta.setString(1, labelaula.getText());

                    // Verifica si el campo de texto está vacío
                    if (labelaula.getText().equals("")) {
                        JOptionPane.showMessageDialog(null,"INGRESA VALORES");
                    }
                    int intento = consulta.executeUpdate();
                    if(intento>0){
                        JOptionPane.showMessageDialog(null, "Borrado el Aula");
                    }else{
                        JOptionPane.showMessageDialog(null, "No se pudo borrar el Aula");
                    }


                }catch (SQLException A){
                    A.printStackTrace();
                }finally {
                    try {
                        if (consulta != null){
                            consulta.close(); // Cierra la consulta
                        }
                        if(connection!= null){
                            connection.close(); // Cierra la conexión
                        }
                    }catch (SQLException A){
                        A.printStackTrace();
                    }
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
    }
}
