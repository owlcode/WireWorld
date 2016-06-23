import javax.swing.*;

public class Main {
    public static final int CELL_DIM = 15;
    private static JFrame field;


    public static void main(String[] args) {
        MainMenu MainMenu = new MainMenu();
        MainMenu.pack();
        MainMenu.setVisible(true);

        field = new JFrame("Visualisation");
    }

    public static void refreshFieldWindowFrame(JPanel f) {
        field.setContentPane(f);
        field.pack();
        field.setVisible(true);
    }
}
