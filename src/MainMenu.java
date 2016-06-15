import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MainMenu extends JFrame {
    private JPanel contentPane;
    private JButton buttonStart;
    private JButton buttonStop;
    private JButton wczytajPlikPlanszyButton;
    private JButton wygenerujLosowąPlanszęButton;
    private JButton zapiszPlikPlanszyButton;
    private JTextField dimX;
    private JTextField dimY;
    private JTextField num;
    private boolean firstTime = true;
    private Timer timer;
    private int q;

    private JFrame field;
    private Field f;


    public MainMenu() {
        setContentPane(contentPane);

        buttonStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onStart();

            }
        });
        buttonStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onStop();
            }
        });
        wygenerujLosowąPlanszęButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onGenerateButtonClicked();
            }
        });
        zapiszPlikPlanszyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onSaveButtonClicked();
            }
        });
        wczytajPlikPlanszyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onReadButtonClicked();
            }
        });
    }

    private void onGenerateButtonClicked() {
        Integer x,y;

        if (dimX.getText().equals("") || dimY.getText().equals("")) {
            Inform inform = new Inform("Uzupełnij pola");
            return;
        } else {
            x = Integer.parseInt(dimX.getText());
            y = Integer.parseInt(dimY.getText());
        }

        f = new Field(x, y);

        if (firstTime) {
            field = new JFrame("Visualisation");
            firstTime = false;
        }

        field.setContentPane(f);
        field.pack();
        field.setVisible(true);

        timer = new Timer(200, action);
        timer.setInitialDelay(0);
    }

    ActionListener action = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (q>0) {
                Field temp = new Field(f);
                field.setContentPane(temp);
                field.pack();
                field.setVisible(true);
                f = temp;
                q--;
                num.setText(Integer.toString(q));
            } else {
                onStop();
            }
        }
    };

    private void onSaveButtonClicked() {
        JFileChooser chooser = new JFileChooser();
        int option = chooser.showSaveDialog(MainMenu.this);

        if(option == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            Stream stream = new Stream();
            stream.save(file, f);
            Inform inf = new Inform("Pomyślnie zapisałeś plik");
        }
    }

    private void onReadButtonClicked() {
        final JFileChooser fc = new JFileChooser();
        final int val = fc.showOpenDialog(MainMenu.this);

        if (val == JFileChooser.APPROVE_OPTION) {
            File sf = fc.getSelectedFile();
            Stream stream = new Stream();

            f = stream.read(sf);
            field.setContentPane(f);
            field.pack();
            field.setVisible(true);
            Inform inform = new Inform("Pomyślnie otworzono plik");
        }
    }

    private void onStart() {
        if(num.getText().equals("")) {
            Inform inf = new Inform("Wstaw liczbę generacji");
            return;
        }

        q = Integer.parseInt(num.getText());

        timer.start();
    }

    private void onStop() {
        timer.stop();
    }
}
