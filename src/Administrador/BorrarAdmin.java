package Administrador;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BorrarAdmin {
    public JPanel AdminBorrar;
    private JComboBox comboBox1;
    private JPanel BorrarLab;
    private JTextField labelab;
    private JButton BORRARButtonLAB;
    private JPanel BorrarAula;
    private JTextField labelaula;
    private JButton BORRARButtonAULA;
    private JButton REGRESARButton;

    public BorrarAdmin() {
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eleccion = (String) comboBox1.getSelectedItem();
                switch (eleccion) {
                    case "Aula":
                        BorrarLab.setVisible(false);
                        BorrarAula.setVisible(true);
                        break;
                    case "Laboratorio":
                        BorrarLab.setVisible(true);
                        BorrarAula.setVisible(false);
                        break;
                    default:
                        System.out.printf("ERROR");
                        break;
                }
            }
        });
        BORRARButtonLAB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url  = "jdbc:mysql://localhost:3306/miaulaesfot";
                String user = "root";
                String password = "123456";
                Connection connection = null;
                PreparedStatement consulta = null;

                try{
                    connection=DriverManager.getConnection(url,user,password);
                    String borrarlab = "DELETE FROM labreserva WHERE codigo  = ?";
                    consulta = connection.prepareStatement(borrarlab);
                    consulta.setString(1, labelab.getText());
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
                            consulta.close();
                        }
                        if(connection!= null){
                            connection.close();
                        }
                    }catch (SQLException A){
                        A.printStackTrace();
                    }
                }
            }
        });
        BORRARButtonAULA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url  = "jdbc:mysql://localhost:3306/miaulaesfot";
                String user = "root";
                String password = "123456";
                Connection connection = null;
                PreparedStatement consulta = null;

                try{
                    connection=DriverManager.getConnection(url,user,password);
                    String borrarlab = "DELETE FROM aulasreserva WHERE codigo  = ?";
                    consulta = connection.prepareStatement(borrarlab);
                    consulta.setString(1, labelaula.getText());
                    if (labelaula.getText().equals("")) {
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
                            consulta.close();
                        }
                        if(connection!= null){
                            connection.close();
                        }
                    }catch (SQLException A){
                        A.printStackTrace();
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
    }
}
