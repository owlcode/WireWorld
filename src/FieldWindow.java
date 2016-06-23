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

    public void addContentFromFile(JLabel label) {
        final JFileChooser fc = new JFileChooser("C:\\WireWorld\\things");
        final int val = fc.showOpenDialog(FieldWindow.this);
        int x = grid.length;
        int y = grid[0].length;
        int struct_x, struct_y;
        Field f;
        Cell[][] struct;


        if (val == JFileChooser.APPROVE_OPTION) {
            Stream stream = new Stream();
            f = stream.read(fc.getSelectedFile());
            if(f == null) {
                Inform inform = new Inform("Wystąpił błąd przy otwieraniu pliku");
                return;
            }
            struct = f.getCellsMap();
            struct_x = f.getXdim();
            struct_y = f.getYdim();

            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    if (label == grid[i][j]) {
                        if (i + struct_x <= x && j + struct_y <= y) {

                            for (int ii=0; ii < struct_x; ii++) {
                                for (int jj=0; jj < struct_y; jj++ ) {
                                    cells[i + ii][j + jj] = struct[ii][jj];
                                    grid[i + ii][j + jj].setBackground(t[(cells[i+ii][j+jj].getState())%t.length]);
                                }
                            }
                            Main.refreshFieldWindowFrame(this);
                            return;
                        }
                    }
                }
            }

        }
    }
}
