package cell;

import java.awt.*;

public class CellFactory {
    public CellFactory() {

    }

    public static Cell Generate (Color c) {
        if(c.equals(Color.RED)) {
            return new Head();
        } else if(c.equals(Color.BLACK)) {
            return new Connector();
        } else if(c.equals(Color.WHITE)) {
            return new EmptyCell();
        } else if(c.equals(Color.YELLOW)) {
            return new Tail();
        }
        return null;
    }

    public static Cell Generate(int s) {
        Integer state = new Integer(s);
        if(state.equals(0)) {
            return new Head();
        } else if (state.equals(1)) {
            return new Tail();
        } else if (state.equals(2)) {
            return new Connector();
        } else if (state.equals(3)) {
            return new EmptyCell();
        }

        return null;
    }
}
