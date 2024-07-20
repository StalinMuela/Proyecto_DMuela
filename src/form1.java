import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class form1 {
    public JPanel panel1;
    private JComboBox comboBox1;
    private JLabel label;
    private JPanel panelEstudiante;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton button1;

    public form1() {
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eleccion = (String) comboBox1.getSelectedItem();
                switch (eleccion) {
                    case "Estudiante":
                        panelEstudiante.setVisible(true);
                        break;
                    case "Profesor":
                        panelEstudiante.setVisible(false);
                        break;
                    case "Administrador":
                        label.setText("Administrador");
                        break;
                    default:
                        System.out.printf("ERROR");
                        break;
                }


            }
        });
    }
}
