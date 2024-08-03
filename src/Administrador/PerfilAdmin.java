package Administrador;
import javax.swing.*;
import Login.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;
/**
 * La clase {@code PerfilAdmin} representa el perfil del administrador
 * y proporciona funcionalidades para agregar, visualizar, borrar y cerrar sesión.
 */
public class PerfilAdmin {
    public JPanel paneladmin;
    private JButton CERRARSESIONButton;
    private JButton AGREGARButton;
    private JButton VISUALIZARButton;
    private JButton BORRARButton;

    /**
     * Constructor de la clase {@code PerfilAdmin}
     * Configura los botones y sus respectivos eventos
     */

    public PerfilAdmin() {
        /**
         * @param botonos Permite crear ActionListener de los diferentes acciones como ADMINISTRADOR
         */

        // ActionListener para el botón AGREGARButton, para ir al panel de Agregar
        AGREGARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear una nueva ventana para el módulo de agregar administrador
                JFrame frame = new JFrame();
                frame.setContentPane(new AgregarAdmin().agregarAdmin);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

                // Cierra la ventana actual del perfil de administrador
                ((JFrame) SwingUtilities.getWindowAncestor(AGREGARButton)).dispose();

            }
        });

        // ActionListener para el botón CERRARSESIONButton, para regresar al INICIO DE SESION
        CERRARSESIONButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear una nueva ventana para la pantalla de inicio de sesión
                JFrame frame = new JFrame();
                frame.setContentPane(new form1().panel1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

                // Cierra la ventana actual del perfil de administrador
                ((JFrame) SwingUtilities.getWindowAncestor(CERRARSESIONButton)).dispose();

            }
        });

        // ActionListener para el botón BORRARButton, para ir al panel de borrar aulas/laboratorios
        BORRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Crear una nueva ventana para el módulo de borrar administrador
                JFrame frame = new JFrame();
                frame.setContentPane(new BorrarAdmin().AdminBorrar);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

                // Cierra la ventana actual del perfil de administrador
                ((JFrame) SwingUtilities.getWindowAncestor(BORRARButton)).dispose();

            }
        });

        // ActionListener para el botón VISUALIZARButton, para ir al panel de visualizar aulas/laboratorios
        VISUALIZARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Crear una nueva ventana para el módulo de visualizar administradores
                JFrame frame = new JFrame("MostrarAdmin");
                frame.setContentPane(new MostrarAdmin().mostrarAdmin);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setSize(800, 600);
                frame.setVisible(true);

                // Cierra la ventana actual del perfil de administrador
                ((JFrame) SwingUtilities.getWindowAncestor(VISUALIZARButton)).dispose();


            }
        });
    }
}