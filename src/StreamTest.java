import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class StreamTest {
    @Test
    public void readingBadExtension() {
        File folder = new File("C:\\Windows");
        Stream stream = new Stream();
        Field field;

        for (final File f : folder.listFiles()) {
            if (!f.isDirectory()) {
                String ext = f.getName().substring(f.getName().lastIndexOf("."), f.getName().length());
                if (!ext.equals(".map") && !ext.equals(".ww")) {
                    field = stream.read(f);
                    assertEquals(field, null);
                }
            }
        }
    }

    @Test
    public void read() throws Exception {

    }

    @Test(expected = IOException.class)
    public void readFromUserDefinition() throws IOException {
        File folder = new File("C:\\WireWorld\\testfiles");
        Stream stream = new Stream();
        Field field;

        for (final File f : folder.listFiles()) {
            if (!f.isDirectory()) {
                String ext = f.getName().substring(f.getName().lastIndexOf("."), f.getName().length());
                if (ext.equals(".ww")) {
                    try {
                        System.out.println(f.getName());
                        stream.readFromUserDefinition(f);
                        fail("Should thrown an exception if file is invalid");
                    } catch (IOException e) {
                        assertEquals(e.getMessage(), "Błędny format pliku");
                        throw e;
                    }
                }
            }
        }
    }

}