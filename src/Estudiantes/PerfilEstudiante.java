package Estudiantes;

import Login.form1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PerfilEstudiante {
    public JPanel panelEstudiantes;
    private JButton CERRARSESIONButton;
    private JButton RESERVARAULAButton;


    public PerfilEstudiante() {
        CERRARSESIONButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("GESTION AULAS ESFOT");
                frame.setContentPane(new form1().panel1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                frame.pack();
                frame.setVisible(true);

                ((JFrame) SwingUtilities.getWindowAncestor(CERRARSESIONButton)).dispose();

            }
        });
        RESERVARAULAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("GESTION AULAS ESFOT");
                frame.setContentPane(new ReservarEstudiante().panelReservaEstudiante);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                frame.pack();
                frame.setVisible(true);

                ((JFrame) SwingUtilities.getWindowAncestor(RESERVARAULAButton)).dispose();


            }
        });
    }
}

