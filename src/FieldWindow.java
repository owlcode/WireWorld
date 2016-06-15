import cell.Cell;
import cell.CellFactory;
import cell.EmptyCell;

import javax.swing.*;
import java.awt.*;

public class FieldWindow extends JPanel {
    private JLabel[][] grid;
    private Cell[][] cells;
    private Color[] t = {Color.RED, Color.YELLOW, Color.BLACK, Color.WHITE};

    public FieldWindow(Field f) {

        Dimension cellSize = new Dimension(Main.CELL_DIM, Main.CELL_DIM);
        ClickListener listener = new ClickListener(this);
        this.cells = f.getCellsMap();
        this.grid = new JLabel[cells.length][cells[0].length];

        setLayout(new GridLayout(cells.length, cells[0].length));

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                JLabel label = new JLabel();

                label.setOpaque(true);
                label.setBackground( t[cells[i][j].getState()] );
                label.addMouseListener(listener);
                label.setPreferredSize(cellSize);
                add(label);
                grid[i][j] = label;
            }
        }
    }

    public void labelPressed(JLabel label) {
        int x = grid.length;
        int y = grid[0].length;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (label == grid[i][j]) {
                    grid[i][j].setBackground(t[(cells[i][j].getState()+1)%t.length]);
                    cells[i][j] = CellFactory.Generate( grid[i][j].getBackground() );
                    Main.refreshFieldWindowFrame(this);
                }
            }
        }
    }
}
