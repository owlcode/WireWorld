import cell.Cell;
import cell.CellFactory;

import java.io.*;

public class Stream {
    Stream() {
    }

    public void save(File file, Field f) {
        try {
            FileOutputStream fout = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(f);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Field read(File f) {
        String filename = f.getName();
        String ext = filename.substring(filename.lastIndexOf("."), filename.length());

        try {
            if (ext.equals(".ww")) {
                return readFromUserDefinition(f);
            } else if (ext.equals(".map")) {
                return readFromSerializedObject(f);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Field readFromSerializedObject(File f) throws IOException, ClassNotFoundException {
        Field field;

        FileInputStream fin = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fin);
        field = (Field) ois.readObject();
        ois.close();

        return field;
    }

    private Field readFromUserDefinition(File f) throws IOException {
        Field field;

        BufferedReader b = new BufferedReader(new FileReader(f));
        String line = b.readLine();
        String[] tmp = line.split("\\s+");

        if (tmp.length == 2) {
            int x = Integer.parseInt(tmp[0]);
            int y = Integer.parseInt(tmp[1]);
            Cell[][] cells = new Cell[x][y];

            line = b.readLine();
            tmp = line.split("\\s+");
            if (tmp.length == x * y) {
                int tab = 0;

                for (int i = 0; i < x; i++) {
                    for (int j = 0; j < y; j++) {
                        cells[i][j] = CellFactory.Generate(tmp[tab]);
                        tab++;
                    }
                }

                return new Field(cells);
            } else {
                throw new IOException("Błędny format pliku");
            }
        } else {
            throw new IOException("Błędny format pliku");
        }
    }

    public static void main(String args[]) {
        Stream s = new Stream();
        File f = new File("/WireWorld/things/generator.ww");
        try {
            s.readFromUserDefinition(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
