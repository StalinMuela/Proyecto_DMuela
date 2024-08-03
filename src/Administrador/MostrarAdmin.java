package Administrador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
/**
 * La clase {@code MostrarAdmin} es la clase de administrador de ESFOT.
 * Su función principal es mostrar las aulas y laboratorios creados
 */

public class MostrarAdmin {
    public JPanel mostrarAdmin;
    private JTable table1;
    private JButton mostrarAULAS;
    private JComboBox comboBox1;
    private JPanel mostrarAula;
    private JButton button2;
    private JPanel mostrarLAB;
    private JTable table2;
    private JButton REGRESARButton;

    //Crea unas constasten que permite la conexion con BASE DE DATOS
    private static final String url = "jdbc:mysql://localhost:3306/miaulaesfot";
    private static final String user = "root";
    private static final String password = "123456";


    /**
     * Constructor de la clase {@code MostrarAdmin}
     * Configura los botones y sus respectivos eventos
     */
    public MostrarAdmin() {

        // ActionListener para el botón comboBox1, para mostrar aulas/laboratorios
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eleccion = (String) comboBox1.getSelectedItem();
                switch (eleccion) {
                    case "Aula":
                        //Permite mostrar el de aula mientras que el de laboratorio oculta
                        mostrarAula.setVisible(true);
                        mostrarLAB.setVisible(false);
                        break;
                    case "Laboratorio":
                        //Permite mostrar el de laboratorio mientras que el de aula oculta
                        mostrarAula.setVisible(false);
                        mostrarLAB.setVisible(true);

                        break;
                    default:
                        System.out.printf("ERROR");
                        break;
                }
            }
        });

        // ActionListener para el botón comboBox1, para mostrar aulas
        mostrarAULAS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

        // ActionListener para el botón button2, para mostrar laboratorios
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String query = "SELECT * FROM labreserva";

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

        // ActionListener para el botón REGRESARButton, para regresar al PERFIL ADMINISTRADOR
        REGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Crea un panel que permite regresa al incio del PERFIL ADMIN
                JFrame frame = new JFrame();
                frame.setContentPane(new PerfilAdmin().paneladmin);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(200,300);
                frame.pack();
                frame.setVisible(true);

                //Permite cerrar el panel MonstrarAdmin
                ((JFrame) SwingUtilities.getWindowAncestor(REGRESARButton)).dispose();

            }
        });
    }
}
