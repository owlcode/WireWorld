import cell.Cell;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickListener extends MouseAdapter {
    private FieldWindow field;

    public ClickListener(FieldWindow field) {this.field = field;}

    @Override
    public void mousePressed(MouseEvent e) {
        field.labelPressed((JLabel) e.getSource());
    }

}
