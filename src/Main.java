import Administrador.PerfilAdmin;
import javax.swing.*;
import Login.*;
import Profesores.PerfilProfesores;
import Profesores.ReservarProfesores;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("GESTION AULAS ESFOT");
        frame.setContentPane(new ReservarProfesores().reservarPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.pack();
        frame.setVisible(true);
    }
}