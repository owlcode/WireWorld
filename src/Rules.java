import cell.Cell;

public class Rules {
    public static int nextState(int x, int y, Cell[][] map) {
        int state = map[x][y].checkState();
        int count = 0;

        if(state == 3) {
            return 3;
        } else if (state == 0) {
            return 1;
        } else if (state == 1) {
            return 2;
        }

        for (int i = x-1; i <= x+1; i++) {
            for (int j = y-1; j <= y+1; j++) {

                if(i >= map.length || i < 0 || j >= map[0].length || j < 0) {
                    continue;
                } else {
                    if(map[i][j].checkState() == 0) {
                        count++;
                    }
                }
            }
        }

        if(count == 2 | count == 1) {
            return 0;
        }

        return 2;
    }


}
