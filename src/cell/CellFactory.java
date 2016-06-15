package cell;

public class CellFactory {
    public Cell CellFactory(int s) {
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
