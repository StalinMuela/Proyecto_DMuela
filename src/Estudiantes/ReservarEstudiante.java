package Estudiantes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * La clase {@code ReservarEstudiante} es una clase de Estudiantes de ESFOT.
 * Su función principal reservar aulas
 */

public class ReservarEstudiante {
    /**
     * Panel para reservar aulas en el perfil de Estudiante
     */
    public JPanel panelReservaEstudiante;
    private JButton REGRESARButton;
    private JTextField codigodelaula;
    private JButton CANCELARRESERVAButton;
    private JButton RESERVARButton;
    private JButton VISUALIZARTABLAButton;
    private JTable table1;
    private static String nombreUsuario = "";
    private static String tipoUsuario = "";

    //Crea unas constasten que permite la conexion con BASE DE DATOS
    private static final String url = "jdbc:mysql://localhost:3306/miaulaesfot";
    private static final String user = "root";
    private static final String password = "123456";

    /**
     * Constructor de la clase {@code ReservarEstudiante}
     * Configura los botones y sus respectivos eventos
     */

    public ReservarEstudiante() {

        // ActionListener para el botón REGRESARButton, para regresar al perfil de ESTUDIANTE
        REGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Crear un nuevo JFrame para la regresar al perfil de ESTUDIANTE
                JFrame frame = new JFrame("GESTION AULAS ESFOT");
                frame.setContentPane(new PerfilEstudiante().panelEstudiantes);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

                // Cerrar el JFrame actual
                ((JFrame) SwingUtilities.getWindowAncestor(REGRESARButton)).dispose();

            }
        });

        // ActionListener para el botón RESERVARButton, para reservar AULAS en perfil ESTUDIANTE
        RESERVARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                                        updateStmt.setString(1, tipoUsuario + "+" + nombreUsuario);
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


        // ActionListener para el botón CANCELARRESERVAButton, para CANCELAR AULAS en perfil ESTUDIANTE
        CANCELARRESERVAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                                if (dispoaula == 1 && userReserva != null && userReserva.equals(tipoUsuario + "+" + nombreUsuario)) {
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
                                } else if (userReserva == null) {
                                    JOptionPane.showMessageDialog(null, "El aula no está reservada");
                                } else {
                                    JOptionPane.showMessageDialog(null, "El aula está reservada por otra persona");
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

        // ActionListener para el botón VISUALIZARTABLAButton, para VISUALIZA AULAS en perfil ESTUDIANTE
        VISUALIZARTABLAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Configuración de la conexión a la base de datos

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
