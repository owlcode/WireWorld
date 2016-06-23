import cell.Cell;

public class Rules {
    private Cell[][] map;

    public Rules(Cell[][] m) {
        map = m;
    }

    public int nextState(int x, int y) {
        int state = map[x][y].getState();
        int count = countStateNeighbour(x, y, 0);

        if (state == 3) {
            return 3;
        } else if (state == 0) {
            return 1;
        } else if (state == 1) {
            return 2;
        }

        if (count == 2 | count == 1) {
            return 0;
        }

        return 2;
    }

    private int countStateNeighbour(int x, int y, int state) {
        int count = 0;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {

                if (!(i >= map.length || i < 0 || j >= map[0].length || j < 0)) {
                    if (map[i][j].getState() == state) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
