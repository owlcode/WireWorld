import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
        int x = Integer.parseInt(dimX.getText());
        int y = Integer.parseInt(dimY.getText());

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
        Stream stream = new Stream();
        stream.save(f);
    }

    private void onReadButtonClicked() {
        Stream stream = new Stream();
        f = stream.read();
        field.setContentPane(f);
        field.pack();
        field.setVisible(true);
    }

    private void onStart() {
        q = Integer.parseInt(num.getText());

        timer.start();
    }

    private void onStop() {
        timer.stop();
    }
}
