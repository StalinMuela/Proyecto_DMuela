package Profesores;

import Login.form1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La clase {@code PerfilProfesores} representa el perfil del Profesor
 * y proporciona funcionalidades mostrar Profesores
 */
public class PerfilProfesores {
    /**
     * Panel para mostrar el perfil de Profesores
     */
    public JPanel perfilProfesor;
    private JButton Visualizar;
    private JButton RESERVARButton;
    private JButton CERRARSESIONButton;

    /**
     * Constructor de la clase {@code PerfilProfesores}
     * Configura los botones y sus respectivos eventos
     */
    public PerfilProfesores() {

        // ActionListener para el botón RESERVARButton, para ir al panel de Reservar
        RESERVARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear una nueva ventana para el módulo de reservar
                JFrame frame = new JFrame();
                frame.setContentPane(new ReservarProfesores().reservarPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

                // Cierra la ventana actual del perfil
                ((JFrame) SwingUtilities.getWindowAncestor(RESERVARButton)).dispose();

            }
        });

        // ActionListener para el botón CERRARSESIONButton, para regresar al Login MiAulaEsfot
        CERRARSESIONButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Crear una nueva ventana para acceder al Login
                JFrame frame = new JFrame();
                frame.setContentPane(new form1().panel1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

                // Cierra la ventana actual del perfil
                ((JFrame) SwingUtilities.getWindowAncestor(CERRARSESIONButton)).dispose();
            }
        });
    }
}
