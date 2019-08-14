import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FileTest {
    File file_1 = new File("\\file_10\\", "Content of file 10");

    @Test
    private void testGetExtension() {
        boolean b = file_1.getExtension().contains("\\.");
        System.out.println(file_1.getExtension());
        assertEquals(b, false);
    }

    @Test
    private void testGetSize() {
        int a = file_1.getSize();
        int b = 9;
        assertEquals(a,b);
    }

    @Test
    private <string> void testGetContent() {
        String a = file_1.getContent();
        String b = "Content of file 10";
        assertEquals(a,b);
    }

    @Test
    private <string> void testGetFilename() {
        String a = file_1.getFilename();
        String b = "\\file_10\\";
        assertEquals(a,b);
    }
}