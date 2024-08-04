
package Administrador;

import Clases.Aula_Lab;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * La clase {@code AgregarAdmin} es una clase de administrador de ESFOT.
 * Su función principal es agregar aulas y laboratorios
 */
public class AgregarAdmin {
    public JPanel agregarAdmin;
    private JComboBox comboBox1;
    private JPanel AgregarAula;
    private JPanel AgregarLab;
    private JTextField NombreAula;
    private JTextField CapacidadAula;
    private JTextField codigoAula;
    private JButton agregarAula;
    private JComboBox DispoAula;
    private JButton REGRESARButton;
    private JButton REGRESARButton1;
    private JComboBox dispolab;
    private JTextField capacidadlab;
    private JButton agregarLabButton;
    private JTextField codigoLab;
    private JTextField nombreLab;

    //Crea unas constasten que permite la conexion con BASE DE DATOS
    private static final String url = "jdbc:mysql://localhost:3306/miaulaesfot";
    private static final String user = "root";
    private static final String password = "123456";

    /**
     * Constructor de la clase {@code AgregarAdmin}
     * Configura los botones y sus respectivos eventos
     */

    public AgregarAdmin() {

        // ActionListener para el botón comboBox1, para cambiar los paneles
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eleccion = (String) comboBox1.getSelectedItem();
                switch (eleccion) {
                    case "Aula":

                        //Permite mostrar el de aula mientras que el de laboratorio oculta
                        AgregarAula.setVisible(true);
                        AgregarLab.setVisible(false);
                        break;
                    case "Laboratorio":
                        //Permite mostrar el de aula mientras que el de laboratorio oculta
                        AgregarAula.setVisible(false);
                        AgregarLab.setVisible(true);
                        break;
                    default:
                        System.out.printf("ERROR");
                        break;
                }
            }
        });

        // ActionListener para el botón agregarAula, para agregar un aula
        agregarAula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String sql = "INSERT INTO aulasreserva (codigo, nombreaula, capacidad, dispoaula) VALUES (?, ?, ?, ?)";

                // Verificar si los campos están vacíos
                if (codigoAula.getText().trim().isEmpty() || NombreAula.getText().trim().isEmpty() || CapacidadAula.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingresa Valores");
                    return;
                }

                // Crear una instancia de Aula_Lab y asignar valores
                Aula_Lab aula = new Aula_Lab();
                aula.setCodigoAula(codigoAula.getText());
                aula.setNombreAula(NombreAula.getText());
                aula.setCapacidadAula(Integer.parseInt(CapacidadAula.getText()));
                aula.setReservarAula("Ocupado".equals((String) DispoAula.getSelectedItem()));

                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    // Verificar si el código del aula ya existe
                    String checkSql = "SELECT COUNT(*) FROM aulasreserva WHERE codigo = ?";
                    try (PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
                        checkStmt.setString(1, aula.getCodigoAula());
                        ResultSet rs = checkStmt.executeQuery();
                        if (rs.next() && rs.getInt(1) > 0) {
                            JOptionPane.showMessageDialog(null, "El aula con el código " + aula.getCodigoAula() + " ya existe");
                            return;
                        }
                    }

                    // Insertar el nuevo aula
                    try (PreparedStatement ingresarAula = connection.prepareStatement(sql)) {
                        ingresarAula.setString(1, aula.getCodigoAula());
                        ingresarAula.setString(2, aula.getNombreAula());
                        ingresarAula.setInt(3, aula.getCapacidadAula());
                        ingresarAula.setBoolean(4, aula.getReservarAula());
                        ingresarAula.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Agregado Correctamente");
                    }
                } catch (SQLException a) {
                    a.printStackTrace();
                }
            }
        });

        // ActionListener para el botón agregarLabButton, para agregar un laboratorio
        agregarLabButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String sql = "INSERT INTO labreserva (codigo, nombrelab, capacidad, dispolab) VALUES (?, ?, ?, ?)";

                // Verificar si los campos están vacíos
                if (codigoLab.getText().trim().isEmpty() || nombreLab.getText().trim().isEmpty() || capacidadlab.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingresa Valores");
                    return;
                }

                // Crear una instancia de Aula_Lab y asignar valores
                Aula_Lab lab = new Aula_Lab();
                lab.setCodigoAula(codigoLab.getText());
                lab.setNombreAula(nombreLab.getText());
                lab.setCapacidadAula(Integer.parseInt(capacidadlab.getText()));
                lab.setReservarAula("Ocupado".equals((String) dispolab.getSelectedItem()));

                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    // Verificar si el código del laboratorio ya existe
                    String checkSql = "SELECT COUNT(*) FROM labreserva WHERE codigo = ?";
                    try (PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
                        checkStmt.setString(1, lab.getCodigoAula());
                        ResultSet rs = checkStmt.executeQuery();
                        if (rs.next() && rs.getInt(1) > 0) {
                            JOptionPane.showMessageDialog(null, "El laboratorio con el código " + lab.getCodigoAula() + " ya existe");
                            return;
                        }
                    }

                    // Insertar el nuevo laboratorio
                    try (PreparedStatement ingresarLab = connection.prepareStatement(sql)) {
                        ingresarLab.setString(1, lab.getCodigoAula());
                        ingresarLab.setString(2, lab.getNombreAula());
                        ingresarLab.setInt(3, lab.getCapacidadAula());
                        ingresarLab.setBoolean(4, lab.getReservarAula());
                        ingresarLab.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Registro Agregado Correctamente");
                    }
                } catch (SQLException a) {
                    a.printStackTrace();
                }
            }
        });


        // ActionListener para el botón REGRESARButton, para regresar al Perfil Administrador

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