import cell.Cell;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Dawid on 2016-06-12.
 */
public class ClickListener extends MouseAdapter {
    private Field field;

    public ClickListener(Field field) {this.field = field;}

    @Override
    public void mousePressed(MouseEvent e) {
        field.labelPressed((Cell)e.getSource());
    }

}
