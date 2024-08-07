import Administrador.PerfilAdmin;
import javax.swing.*;

import Estudiantes.PerfilEstudiante;
import Estudiantes.ReservarEstudiante;
import Login.*;
import Profesores.PerfilProfesores;
import Profesores.ReservarProfesores;

/**
 * La clase {@code Main} es la clase principal de Mi aula ESFOT.
 * Su función principal es mostrar el INICIO DE SESIÓN.
 *
 * @author DAVID MUELA
 * @version VERSION COMPLETA DEL PROYECTO MI AULA ESFOT
 */
public class Main {

    /**
     * Constructor por defecto de la clase Main.
     */
    public Main() {
        // Constructor por defecto
    }

    /**
     * El método principal que inicia la aplicación.
     *
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {

        //Permite crear un panel conocido como INICIO DE SESION

        JFrame frame = new JFrame("GESTION AULAS ESFOT");
        frame.setContentPane(new form1().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(800, 600);
        frame.pack();
        frame.setVisible(true);
    }
}
