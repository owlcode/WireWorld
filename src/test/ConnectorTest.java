package test;

import cell.Cell;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConnectorTest {
    @Test
    public void getState() throws Exception {
        Cell cell = new Cell();
        Integer i = cell.getState();
        assertEquals(new Integer(-1),i);
    }
}