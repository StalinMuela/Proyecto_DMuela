package Estudiantes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ReservarEstudiante {
    public JPanel panelReservaEstudiante;
    private JButton REGRESARButton;
    private JTextField codigodelaula;
    private JButton CANCELARRESERVAButton;
    private JButton RESERVARButton;
    private JButton VISUALIZARTABLAButton;
    private JTable table1;
    private static String nombreUsuario = "";
    private static String tipoUsuario = "";

    public ReservarEstudiante() {
        REGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("GESTION AULAS ESFOT");
                frame.setContentPane(new PerfilEstudiante().panelEstudiantes);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        RESERVARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/miaulaesfot";
                String user = "root";
                String password = "123456";
                String codigoAula = codigodelaula.getText();

                if (codigoAula.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese un valor");
                    return;
                }

                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    // Consultar si el aula está disponible
                    String query = "SELECT dispoaula, user_reserva FROM aulasreserva WHERE codigo = ?";
                    try (PreparedStatement stmt = connection.prepareStatement(query)) {
                        stmt.setString(1, codigoAula);
                        try (ResultSet rs = stmt.executeQuery()) {
                            if (rs.next()) {
                                int dispoaula = rs.getInt("dispoaula");

                                // Solo proceder si el aula está disponible (0 indica no reservado)
                                if (dispoaula == 0) {
                                    // Actualizar la disponibilidad del aula y registrar el usuario
                                    String updateQuery = "UPDATE aulasreserva SET dispoaula = 1, user_reserva = ? WHERE codigo = ?";
                                    try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                                        updateStmt.setString(1, tipoUsuario + "_" + nombreUsuario);
                                        updateStmt.setString(2, codigoAula);
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


        CANCELARRESERVAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/miaulaesfot";
                String user = "root";
                String password = "123456";
                String codigoAula = codigodelaula.getText(); // Asumiendo que tienes un JTextField llamado cancelarAula

                if (codigoAula.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese un valor");
                    return;
                }

                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    // Consultar si el aula está reservada y por quién
                    String query = "SELECT dispoaula, user_reserva FROM aulasreserva WHERE codigo = ?";
                    try (PreparedStatement stmt = connection.prepareStatement(query)) {
                        stmt.setString(1, codigoAula);
                        try (ResultSet rs = stmt.executeQuery()) {
                            if (rs.next()) {
                                int dispoaula = rs.getInt("dispoaula");
                                String userReserva = rs.getString("user_reserva");

                                // Verificar si el campo user_reserva es null
                                if (userReserva == null) {
                                    JOptionPane.showMessageDialog(null, "El aula esta RESERVADA por otra persona");
                                } else if (dispoaula == 1 && userReserva.equals(tipoUsuario + "_" + nombreUsuario)) {
                                    // Actualizar la disponibilidad del aula y limpiar el campo user_reserva
                                    String updateQuery = "UPDATE aulasreserva SET dispoaula = 0, user_reserva = NULL WHERE codigo = ?";
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
                                    JOptionPane.showMessageDialog(null, "El aula no está reservada por usted o no está reservada");
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
        VISUALIZARTABLAButton.addActionListener(new ActionListener() {
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

                table1.setModel(tableModel);
                table1.setTableHeader(new JTableHeader(table1.getColumnModel()));


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
    }
}
