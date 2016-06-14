package cell;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static java.lang.Math.abs;

/**
 * @author owlcode
 * @state 0 - head; 1 - tail; 2 - connector; 3 - blank
 */
public class Cell extends JLabel {
    private int state;

    public Cell() {state = -1;}
    public Cell(int x) {
        state = x;
    }

    public void setState(int x) {
        state = x;
    }

    public Color checkBackground() {
        Color[] t = {Color.RED, Color.yellow, Color.BLACK, Color.WHITE};

        return t[state];
    }

    public int checkState() {
        return state;
    }
}
