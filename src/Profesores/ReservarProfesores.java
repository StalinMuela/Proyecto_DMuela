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
    private JButton CANCELARRESERVAButtonLAB;
    private JTextField codigoLAB;
    private JTable table1;
    private JButton visualizarButton1;
    private JButton RESERVARButtonLAB;
    private JPanel reservarLab;
    private JButton Regresaar;
    private static String nombreUsuario = "";
    private static String tipoUsuario = "";


    public ReservarProfesores() {

        ///CAMBIO DE PANELES
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

        //RESERVAR AULA
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

        //CANCELAR AULA RESERVA

        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/miaulaesfot";
                String user = "root";
                String password = "123456";
                String codigoAula = reservaraula.getText(); // Asumiendo que tienes un JTextField llamado cancelarAula

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

        //VISUALIZAR AULASS

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

        //VISUALIZAR LABORATORIOS
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




        //REGRESAR
        Regresaar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new PerfilProfesores().perfilProfesor);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(Regresaar)).dispose();

            }
        });

        RESERVARButtonLAB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/miaulaesfot";
                String user = "root";
                String password = "123456";
                String codigoLaboratorio = codigoLAB.getText();

                if (codigoLaboratorio.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese un valor");
                    return;
                }

                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    // Consultar si el laboratorio está disponible
                    String query = "SELECT dispolab, user_reserva FROM labreserva WHERE codigo = ?";
                    try (PreparedStatement stmt = connection.prepareStatement(query)) {
                        stmt.setInt(1, Integer.parseInt(codigoLaboratorio));
                        try (ResultSet rs = stmt.executeQuery()) {
                            if (rs.next()) {
                                int dispolab = rs.getInt("dispolab");
                                String userReserva = rs.getString("user_reserva");

                                // Solo proceder si el laboratorio está disponible (0 indica no reservado)
                                if (dispolab == 0) {
                                    // Actualizar la disponibilidad del laboratorio y registrar el usuario
                                    String updateQuery = "UPDATE labreserva SET dispolab = 1, user_reserva = ? WHERE codigo = ?";
                                    try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                                        updateStmt.setString(1, tipoUsuario + "_" + nombreUsuario);
                                        updateStmt.setInt(2, Integer.parseInt(codigoLaboratorio));
                                        int rowsAffected = updateStmt.executeUpdate();

                                        if (rowsAffected > 0) {
                                            JOptionPane.showMessageDialog(null, "Laboratorio reservado con éxito");
                                        } else {
                                            JOptionPane.showMessageDialog(null, "No se pudo reservar el laboratorio");
                                        }
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "El laboratorio ya está reservado");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Código de laboratorio no encontrado");
                            }
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
                }
            }
        });

        CANCELARRESERVAButtonLAB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/miaulaesfot";
                String user = "root";
                String password = "123456";
                String codigoLaboratorio = codigoLAB.getText();

                if (codigoLaboratorio.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese un valor");
                    return;
                }

                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    // Consultar si el laboratorio está reservado y por quién
                    String query = "SELECT dispolab, user_reserva FROM labreserva WHERE codigo = ?";
                    try (PreparedStatement stmt = connection.prepareStatement(query)) {
                        stmt.setInt(1, Integer.parseInt(codigoLaboratorio));
                        try (ResultSet rs = stmt.executeQuery()) {
                            if (rs.next()) {
                                int dispolab = rs.getInt("dispolab");
                                String userReserva = rs.getString("user_reserva");

                                if (userReserva == null) {
                                    JOptionPane.showMessageDialog(null, "El laboratorio esta RESERVADA por otra persona");
                                } else if (dispolab == 1 && userReserva.equals(tipoUsuario + "_" + nombreUsuario)) {
                                    // Actualizar la disponibilidad del laboratorio y limpiar el campo user_reserva
                                    String updateQuery = "UPDATE labreserva SET dispolab = 0, user_reserva = NULL WHERE codigo = ?";
                                    try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                                        updateStmt.setInt(1, Integer.parseInt(codigoLaboratorio));
                                        int rowsAffected = updateStmt.executeUpdate();

                                        if (rowsAffected > 0) {
                                            JOptionPane.showMessageDialog(null, "Reserva cancelada con éxito");
                                        } else {
                                            JOptionPane.showMessageDialog(null, "No se pudo cancelar la reserva");
                                        }
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "El laboratorio no está reservado por usted o no está reservado");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Código de laboratorio no encontrado");
                            }
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
                }
            }
        });



    }
}
