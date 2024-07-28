package Administrador;
import javax.swing.*;
import Login.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;

public class PerfilAdmin {
    public JPanel paneladmin;
    private JButton CERRARSESIONButton;
    private JButton AGREGARButton;
    private JButton VISUALIZARButton;
    private JButton BORRARButton;
    private JButton ACTUALIZARButton;

    public PerfilAdmin() {
        AGREGARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new AgregarAdmin().agregarAdmin);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(AGREGARButton)).dispose();

            }
        });
        CERRARSESIONButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new form1().panel1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(CERRARSESIONButton)).dispose();

            }
        });
        BORRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new BorrarAdmin().AdminBorrar);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(BORRARButton)).dispose();

            }
        });
        VISUALIZARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("MostrarAdmin");
                frame.setContentPane(new MostrarAdmin().mostrarAdmin);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setSize(800, 600);
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(VISUALIZARButton)).dispose();


            }
        });
    }
}