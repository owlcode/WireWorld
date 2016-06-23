import cell.Cell;
import cell.EmptyCell;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class FieldTest {
    private Random r;
    private Integer a;
    private Integer b;

    public FieldTest() {
        r = new Random();
    }
    @Test
    public void getXdim() throws Exception {
        for (int i=0;i<100;i++) {
            a = r.nextInt(200);
            b = r.nextInt(200);

            Field f = new Field(a,b);
            assertEquals(new Integer(f.getXdim()), a);
        }
    }

    @Test
    public void getYdim() throws Exception {
        for (int i=0;i<100;i++) {
            a = r.nextInt(200);
            b = r.nextInt(200);

            Field f = new Field(a,b);
            assertEquals(new Integer(f.getYdim()), b);
        }
    }

    @Test
    public void getCellsMap() throws Exception {

    }

}