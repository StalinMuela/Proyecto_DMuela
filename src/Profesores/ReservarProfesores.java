package Profesores;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ReservarProfesores {
    public JPanel reservarPanel;
    private JButton buttonReservar;
    private JTextField reservaraula;
    private JButton buttonCancelar;
    private JComboBox comboBox1;
    private JPanel reservarAula;
    private JPanel reserverLab;
    private JTable table2;
    private JButton visualizarButton;
    private JButton CANCELARRESERVAButton;
    private JTextField textField1;
    private JTable table1;
    private JButton visualizarButton1;
    private JButton RESERVARButton;
    private JPanel reservarLab;

    public ReservarProfesores() {
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eleccion = (String) comboBox1.getSelectedItem();
                switch (eleccion) {
                    case "Laboratorio":
                        reservarAula.setVisible(false);
                        reservarLab.setVisible(true);
                        break;
                    case "Aula":
                        reservarAula.setVisible(true);
                        reservarLab.setVisible(false);
                }
            }
        });
        buttonReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/miaulaesfot";
                String user = "root";
                String password = "123456";
                String codigoAula = reservaraula.getText();

                if (codigoAula.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese un valor");
                    return;
                }

                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    // Consultar si el aula está disponible
                    String query = "SELECT dispoaula FROM aulasreserva WHERE codigo = ?";
                    try (PreparedStatement stmt = connection.prepareStatement(query)) {
                        stmt.setString(1, codigoAula);
                        try (ResultSet rs = stmt.executeQuery()) {
                            if (rs.next()) {
                                int dispoaula = rs.getInt("dispoaula");

                                // Solo proceder si el aula está disponible (0 indica no reservado)
                                if (dispoaula == 0) {
                                    // Actualizar la disponibilidad del aula
                                    String updateQuery = "UPDATE aulasreserva SET dispoaula = 1 WHERE codigo = ?";
                                    try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                                        updateStmt.setString(1, codigoAula);
                                        int rowsAffected = updateStmt.executeUpdate();

                                        if (rowsAffected > 0) {
                                            JOptionPane.showMessageDialog(null, "Aula reservada con éxito");
                                        } else {
                                            JOptionPane.showMessageDialog(null, "No se pudo reservar el aula");
                                        }
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "El aula ya está reservada");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Código de aula no encontrado");
                            }
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
                }
            }
        });
        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/miaulaesfot";
                String user = "root";
                String password = "123456";
                String codigoAula = reservaraula.getText();

                if (codigoAula.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese un valor");
                    return;
                }

                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    // Consultar si el aula está reservada
                    String query = "SELECT dispoaula FROM aulasreserva WHERE codigo = ?";
                    try (PreparedStatement stmt = connection.prepareStatement(query)) {
                        stmt.setString(1, codigoAula);
                        try (ResultSet rs = stmt.executeQuery()) {
                            if (rs.next()) {
                                int dispoaula = rs.getInt("dispoaula");

                                // Solo proceder si el aula está reservada (1 indica reservado)
                                if (dispoaula == 1) {
                                    // Actualizar la disponibilidad del aula
                                    String updateQuery = "UPDATE aulasreserva SET dispoaula = 0 WHERE codigo = ?";
                                    try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                                        updateStmt.setString(1, codigoAula);
                                        int rowsAffected = updateStmt.executeUpdate();

                                        if (rowsAffected > 0) {
                                            JOptionPane.showMessageDialog(null, "Reserva cancelada con éxito");
                                        } else {
                                            JOptionPane.showMessageDialog(null, "No se pudo cancelar la reserva");
                                        }
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "El aula no está reservada");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Código de aula no encontrado");
                            }
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
                }
            }
        });

        visualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Configuración de la conexión a la base de datos
                String url = "jdbc:mysql://localhost:3306/miaulaesfot";
                String user = "root";
                String password = "123456";
                String query = "SELECT * FROM aulasreserva";

                // Crear un modelo de tabla y establecer columnas
                DefaultTableModel tableModel = new DefaultTableModel();
                tableModel.addColumn("Código");
                tableModel.addColumn("Nombre Aula");
                tableModel.addColumn("Capacidad");
                tableModel.addColumn("Disponible");

                table2.setModel(tableModel);
                table2.setTableHeader(new JTableHeader(table2.getColumnModel()));


                // Conectar a la base de datos y consultar datos
                try (Connection connection = DriverManager.getConnection(url, user, password);
                     Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery(query)) {

                    // Procesar resultados y añadir filas a la tabla
                    while (resultSet.next()) {
                        String codigo = resultSet.getString("codigo");
                        String nombreaula = resultSet.getString("nombreaula");
                        int capacidad = resultSet.getInt("capacidad");
                        boolean dispoaula = resultSet.getBoolean("dispoaula");

                        // Convertir boolean a String para la visualización en la tabla
                        String dispoaulaStr = dispoaula ? "Sí" : "No";

                        tableModel.addRow(new Object[]{codigo, nombreaula, capacidad, dispoaulaStr});
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        visualizarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/miaulaesfot";
                String user = "root";
                String password = "123456";
                String query = "SELECT * FROM labreserva";

                // Crear un modelo de tabla y establecer columnas
                DefaultTableModel tableModel = new DefaultTableModel();
                tableModel.addColumn("Código");
                tableModel.addColumn("Nombre Lab");
                tableModel.addColumn("Capacidad");
                tableModel.addColumn("Disponible");

                table1.setModel(tableModel);
                table1.setTableHeader(new JTableHeader(table1.getColumnModel()));


                // Conectar a la base de datos y consultar datos
                try (Connection connection = DriverManager.getConnection(url, user, password);
                     Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery(query)) {

                    // Procesar resultados y añadir filas a la tabla
                    while (resultSet.next()) {
                        String codigo = resultSet.getString("codigo");
                        String nombrelab = resultSet.getString("nombrelab");
                        int capacidad = resultSet.getInt("capacidad");
                        boolean dispolab = resultSet.getBoolean("dispolab");

                        // Convertir boolean a String para la visualización en la tabla
                        String dispolabStr = dispolab ? "Sí" : "No";

                        tableModel.addRow(new Object[]{codigo, nombrelab, capacidad, dispolabStr});
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
