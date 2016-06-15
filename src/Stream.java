import java.io.*;

public class Stream {
    private Stream stream;

    public Stream() {

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
        Field field;

        try {
            FileInputStream fin = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fin);
            field = (Field)ois.readObject();
            ois.close();

            return field;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main (String args[]) {


    }
}
