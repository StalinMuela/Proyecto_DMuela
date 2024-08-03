package Estudiantes;

import Login.form1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
/**
 * La clase {@code PerfilEstudiante} representa el perfil del ESTUDIANTE de mi AULAESFOT
 * y proporciona funcionalidades para reservar solo aulas.
 */
public class PerfilEstudiante {
    public JPanel panelEstudiantes;
    private JButton CERRARSESIONButton;
    private JButton RESERVARAULAButton;

    /**
     * Constructor de la clase {@code PerfilEstudiante}
     * Configura los botones y sus respectivos eventos
     */

    public PerfilEstudiante() {

        // ActionListener para el botón CERRARSESIONButton, para regresar al INICIO DE SESION
        CERRARSESIONButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Crear un nuevo JFrame para la pantalla de inicio de sesión
                JFrame frame = new JFrame("GESTION AULAS ESFOT");
                frame.setContentPane(new form1().panel1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                frame.pack();
                frame.setVisible(true);

                // Cerrar el JFrame actual
                ((JFrame) SwingUtilities.getWindowAncestor(CERRARSESIONButton)).dispose();

            }
        });

        // ActionListener para el botón RESERVARAULAButton, para ingresar al perfil de RESERVAR AULAS
        RESERVARAULAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Crear un nuevo JFrame para la pantalla de reserva de aulas
                JFrame frame = new JFrame("GESTION AULAS ESFOT");
                frame.setContentPane(new ReservarEstudiante().panelReservaEstudiante);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                frame.pack();
                frame.setVisible(true);

                // Cerrar el JFrame actual
                ((JFrame) SwingUtilities.getWindowAncestor(RESERVARAULAButton)).dispose();


            }
        });
    }
}

