package cell;

import javax.swing.*;
import java.awt.*;

public class Cell extends JLabel {
    protected int state;

    public Cell() { state = -1; }

    public Integer getState() {
        return state;
    }
}
