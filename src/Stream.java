import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Stream {
    private Stream stream;

    public Stream() {

    }

    public void save(Field f) {
        try {
            FileOutputStream fout = new FileOutputStream("xor.wireworld");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(f);
            oos.close();
            System.out.println("done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Field read() {
        Field f;

        try {
            FileInputStream fin = new FileInputStream("xor.wireworld");
            ObjectInputStream ois = new ObjectInputStream(fin);
            f = (Field)ois.readObject();
            ois.close();

            return f;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main (String args[]) {


    }
}
