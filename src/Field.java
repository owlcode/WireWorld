import cell.Cell;
import cell.CellFactory;
import cell.EmptyCell;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Field implements Serializable {
    private Cell[][] cells;
    private int x;
    private int y;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
        cells = new Cell[x][y];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                EmptyCell cell = new EmptyCell();
                cells[i][j] = cell;
            }
        }
    }

    public Field(Field f) {
        this.x = f.getXdim();
        this.y = f.getYdim();
        Cell[][] old = f.getCellsMap();
        cells = new Cell[x][y];

        for (int i=0;i<x;i++) {
            for (int j=0;j<y;j++) {
                Rules rules = new Rules(old);
                Cell cell = CellFactory.Generate(rules.nextState(i,j));
                cells[i][j] = cell;
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
