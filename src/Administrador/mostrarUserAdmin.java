package Administrador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * La clase {@code mostrarUserAdmin} es la clase de administrador de ESFOT.
 * Su función principal es mostrar usuarios creados
 */
public class mostrarUserAdmin {
    /**
     * Panel para mostrar usuarios en el perfil administrador
     */
    public JPanel panelmostrarUser;
    private JComboBox comboBox1;
    private JButton visualizarEstudianteButton;
    private JTable table1;
    private JPanel mostrarEstudiante;
    private JButton regresarBUTTON;
    private JTable table2;
    private JPanel mostrarProfesor;
    private JButton visualizarProfesorButton1;


    //Crea unas constasten que permite la conexion con BASE DE DATOS
    private static final String url = "jdbc:mysql://localhost:3306/miaulaesfot";
    private static final String user = "root";
    private static final String password = "123456";


    /**
     * Constructor de la clase {@code mostrarUserAdmin}
     * Configura los botones y sus respectivos eventos
     */
    public mostrarUserAdmin() {

        // ActionListener para el botón comboBox1, para mostrar usuarios
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eleccion = (String) comboBox1.getSelectedItem();
                switch (eleccion) {
                    case "Estudiante":
                        //Permite mostrar tabla de estudiantes mientras oculta la tabla de profesores
                        mostrarEstudiante.setVisible(true);
                        mostrarProfesor.setVisible(false);
                        break;
                    case "Profesor":
                        //Permite ocultar tabla de estudiantes mientras muestra la tabla de profesores
                        mostrarEstudiante.setVisible(false);
                        mostrarProfesor.setVisible(true);
                        break;
                }
            }
        });

        // ActionListener para el botón visualizarEstudianteButton, para visualizar estudiantes
        visualizarEstudianteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = "SELECT * FROM sesionestudiante";

                // Crear un modelo de tabla y establecer columnas
                DefaultTableModel tableModel = new DefaultTableModel();
                tableModel.addColumn("Usuario");
                tableModel.addColumn("Contraseña");

                table1.setModel(tableModel);
                table1.setTableHeader(new JTableHeader(table1.getColumnModel()));

                try(Connection connection = DriverManager.getConnection(url,user,password)){
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);

                    // Procesar resultados y añadir filas a la tabla
                    while(resultSet.next()){
                        String usuario = resultSet.getString("userstudent");
                        String contraseña = resultSet.getString("passstudente");

                        tableModel.addRow(new Object[]{usuario,contraseña});
                    }
                }catch (SQLException a){
                    a.printStackTrace();
                }
            }
        });

        // ActionListener para el botón regresarBUTTON, para regresar al ADMINISTRADOR
        regresarBUTTON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear una nueva ventana para el módulo de registrr
                JFrame frame = new JFrame("MostrarAdmin");
                frame.setContentPane(new PerfilAdmin().paneladmin);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

                // Cierra la ventana actual del perfil de administrador
                ((JFrame) SwingUtilities.getWindowAncestor(regresarBUTTON)).dispose();

            }
        });

        // ActionListener para el botón visualizarProfesorButton1, para visualizar profesores
        visualizarProfesorButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = "SELECT * FROM sesionprofesor";

                // Crear un modelo de tabla y establecer columnas
                DefaultTableModel tableModel = new DefaultTableModel();
                tableModel.addColumn("Usuario");
                tableModel.addColumn("Contraseña");

                table2.setModel(tableModel);
                table2.setTableHeader(new JTableHeader(table2.getColumnModel()));

                try(Connection connection = DriverManager.getConnection(url,user,password)){
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);

                    // Procesar resultados y añadir filas a la tabla
                    while(resultSet.next()){
                        String usuario = resultSet.getString("userteacher");
                        String contraseña = resultSet.getString("passteacher");

                        tableModel.addRow(new Object[]{usuario,contraseña});
                    }
                }catch (SQLException a){
                    a.printStackTrace();
                }
            }
        });
    }
}
