package Administrador;

import Clases.Aula_Lab;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

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

    public AgregarAdmin() {
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eleccion = (String) comboBox1.getSelectedItem();
                switch (eleccion) {
                    case "Aula":
                        AgregarAula.setVisible(true);
                        AgregarLab.setVisible(false);
                        codigoAula.setText("");
                        break;
                    case "Laboratorio":
                        AgregarAula.setVisible(false);
                        AgregarLab.setVisible(true);
                        break;
                    default:
                        System.out.printf("ERROR");
                        break;
                }
            }
        });
        agregarAula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url  = "jdbc:mysql://localhost:3306/miaulaesfot";
                String user = "root";
                String password = "123456";

                String sql = "INSERT INTO aulasreserva (codigo, nombreaula, capacidad, dispoaula) VALUES (?, ?, ?, ?)";

                // Verificar si los campos están vacíos
                if (codigoAula.getText().trim().isEmpty() || NombreAula.getText().trim().isEmpty() || CapacidadAula.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingresa Valores");
                } else {
                    // Crear una instancia de Aula_Lab
                    Aula_Lab aula = new Aula_Lab();
                    String aulas = codigoAula.getText();
                    String nombre = NombreAula.getText();
                    int capacidad = Integer.parseInt(CapacidadAula.getText());
                    String seleccion = (String) DispoAula.getSelectedItem();

                    // Determinar el estado de reserva basado en la selección
                    Boolean ocupado = "Ocupado".equals(seleccion);

                    aula.setCodigoAula(aulas);
                    aula.setNombreAula(nombre);
                    aula.setCapacidadAula(capacidad);
                    aula.setReservarAula(ocupado);

                    try (Connection connection = DriverManager.getConnection(url, user, password)) {
                        PreparedStatement ingresarAula = connection.prepareStatement(sql);
                        ingresarAula.setString(1, aula.getCodigoAula());
                        ingresarAula.setString(2, aula.getNombreAula());
                        ingresarAula.setInt(3, aula.getCapacidadAula());
                        ingresarAula.setBoolean(4, aula.getReservarAula());
                        ingresarAula.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Registro Agregado Correctamente");
                    } catch (SQLException a) {
                        a.printStackTrace();
                    }
                }
            }
        });

        REGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new PerfilAdmin().paneladmin);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(200,300);
                frame.pack();
                frame.setVisible(true);
            }
        });
        agregarLabButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url  = "jdbc:mysql://localhost:3306/miaulaesfot";
                String user = "root";
                String password = "123456";

                String sql = "INSERT INTO labreserva (codigo, nombrelab , capacidad, dispolab ) VALUES (?, ?, ?, ?)";

                // Verificar si los campos están vacíos
                if (codigoLab.getText().trim().isEmpty() || nombreLab.getText().trim().isEmpty() || capacidadlab.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingresa Valores");
                } else {
                    // Crear una instancia de Aula_Lab
                    Aula_Lab lab = new Aula_Lab();
                    String aulas = codigoLab.getText();
                    String nombre = nombreLab.getText();
                    int capacidad = Integer.parseInt(capacidadlab.getText());
                    String seleccion = (String) dispolab.getSelectedItem();

                    // Determinar el estado de reserva basado en la selección
                    Boolean ocupado = "Ocupado".equals(seleccion);

                    lab.setCodigoAula(aulas);
                    lab.setNombreAula(nombre);
                    lab.setCapacidadAula(capacidad);
                    lab.setReservarAula(ocupado);

                    try (Connection connection = DriverManager.getConnection(url, user, password)) {
                        PreparedStatement ingresarAula = connection.prepareStatement(sql);
                        ingresarAula.setString(1, lab.getCodigoAula());
                        ingresarAula.setString(2, lab.getNombreAula());
                        ingresarAula.setInt(3, lab.getCapacidadAula());
                        ingresarAula.setBoolean(4, lab.getReservarAula());
                        ingresarAula.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Registro Agregado Correctamente");
                    } catch (SQLException a) {
                        a.printStackTrace();
                    }

                }
            }
        });
        REGRESARButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new PerfilAdmin().paneladmin);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(200,300);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
