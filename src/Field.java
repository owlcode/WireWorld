import cell.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class Field extends JPanel implements Serializable {
    private Cell[][] cells;
    private Dimension cellSize;
    private Timer timer;
    private transient ClickListener listener;
    private int x;
    private int y;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
        cells = new Cell[x][y];
        cellSize = new Dimension(30, 30);
        listener = new ClickListener(this);

        setLayout(new GridLayout(y, x));

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Cell cell = new Cell(3);
                cell.setOpaque(true);
                cell.setBackground(cell.checkBackground());
                cell.addMouseListener(listener);
                cell.setPreferredSize(cellSize);
                add(cell);
                cells[i][j] = cell;
            }
        }
    }

    public Field(Field f) {
        this.x = f.getXdim();
        this.y = f.getYdim();
        Cell[][] old = f.getCellsMap();
        cells = new Cell[x][y];
        cellSize = new Dimension(30,30);
        listener = new ClickListener(this);

        setLayout(new GridLayout(y,x));

        for (int i=0;i<x;i++) {
            for (int j=0;j<y;j++) {

                Cell cell = new Cell( Rules.nextState(i,j,old) );

                cell.setOpaque(true);
                cell.setBackground(cell.checkBackground());
                cell.addMouseListener(listener);
                cell.setPreferredSize(cellSize);
                add(cell);
                cells[i][j] = cell;
            }
        }
    }

    public void labelPressed(Cell cell) {
        cell.setState(cell.checkState()%4);
        cell.setBackground(cell.checkBackground());

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (cell == cells[i][j]) {
                    cells[i][j].setState((cells[i][j].checkState()+1)%4);
                    cells[i][j].setBackground(cells[i][j].checkBackground());
                }
            }
        }
    }

    public int getXdim() {
        return this.x;
    }

    public int getYdim() {
        return this.y;
    }

    public Cell[][] getCellsMap() {
        return this.cells;
    }




}
