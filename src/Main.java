import Administrador.PerfilAdmin;
import javax.swing.*;

import Estudiantes.PerfilEstudiante;
import Estudiantes.ReservarEstudiante;
import Login.*;
import Profesores.PerfilProfesores;
import Profesores.ReservarProfesores;

/**
 * La clase {@code Main} es la clase principal de Mi aula ESFOT.
 * Su función principal es mostrar el INCIO DE SESIÓN.
 *
 * @author DAVID MUELA
 * @version VERSION COMPLETA DEL PROYECTO MI AULA ESFOT
 */

/**
 * El paquete {@code Administrador} es el encargado de contener
 * los perfiles de Administrador
 */


public class Main {
    public static void main(String[] args) {
        //Permite crear un panel conocido como INICIO DE SESION

        JFrame frame = new JFrame("GESTION AULAS ESFOT");
        frame.setContentPane(new PerfilEstudiante().panelEstudiantes);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(800, 600);
        frame.pack();
        frame.setVisible(true);
    }
}