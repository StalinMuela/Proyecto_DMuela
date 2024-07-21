import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("GESTION AULAS ESFOT");
        frame.setContentPane(new PerfilAdmin().paneladmin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.pack();
        frame.setVisible(true);
    }
}