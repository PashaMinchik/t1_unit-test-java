import exception.FileAlreadyExistsException;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;

public class FileStorageTest {
    File file = new File("file_1", "Content of file 1");
    //ArrayList<File> files = new ArrayList<>();
    //FileStorage fileStorage = new FileStorage();

    @Test
    private void testWrite() throws FileAlreadyExistsException {
        ArrayList<File> files = new ArrayList<>();
        FileStorage fileStorage = new FileStorage();
        files.add(file);
        assertEquals(true, fileStorage.write(file));
    }

    @Test (expectedExceptions = FileAlreadyExistsException.class)
    private void testWriteWithThrows() throws FileAlreadyExistsException {
        ArrayList<File> files = new ArrayList<>();
        FileStorage fileStorage = new FileStorage();
        fileStorage.write(file);
        fileStorage.write(file);
        throw new FileAlreadyExistsException();
    }
    @Test
    private void testWriteSize() throws FileAlreadyExistsException {
        FileStorage fileStorage = new FileStorage();
        File fileValid = new File("\\file_1\\", "0123456789");
        assertEquals(true, fileStorage.write(fileValid));

        File fileLimit = new File("\\file_1\\", "0123456789");
        FileStorage fileStorageLimit = new FileStorage(-95);
        assertEquals(true, fileStorageLimit.write(fileLimit));

        File fileInvalid = new File("\\file_1\\", "0123456789");
        FileStorage fileStorageInvalid = new FileStorage(-99);
        assertEquals(false, fileStorageInvalid.write(fileInvalid));
    }
    @Test
    private void testWriteTimeout() throws FileAlreadyExistsException {
        File fileWithoutRemainder = new File("\\file_1\\", "");
        FileStorage fileStorageWithoutRemainder = new FileStorage(98);
        float st, en, time;
        st = System.nanoTime();
        fileStorageWithoutRemainder.write(fileWithoutRemainder);
        en = System.nanoTime();
        System.out.println((en-st)/1000000000);
        time = (en-st)/1000000000;
        assertEquals(true, ((time > 1.6) && (time < 2)));

        File fileWithRemainder = new File("\\file_1\\", "");
        FileStorage fileStorageWithRemainder = new FileStorage(99);
        float sta, end, time1;
        sta = System.nanoTime();
        fileStorageWithRemainder.write(fileWithRemainder);
        end = System.nanoTime();
        time1 = (end-sta)/1000000000;
        System.out.println((end-sta)/1000000000);
        assertEquals(true, time1 > 2.8);
    }

    @Test
    private void testIsExists() throws FileAlreadyExistsException {
        FileStorage fileStorage = new FileStorage();
        fileStorage.write(file);
        assertEquals(true, fileStorage.isExists("file_1"));
    }

    @Test
    private void testDelete()throws FileAlreadyExistsException {
        FileStorage fileStorage = new FileStorage();
        fileStorage.write(file);
        fileStorage.delete("file_1");
        assertEquals(false, fileStorage.isExists("file_1"));
    }

    @Test
    private void testGetFiles() throws FileAlreadyExistsException {
        FileStorage fileStorage = new FileStorage();
        File file1 = new File("file_2", "Content of file 1");
        fileStorage.write(file);
        fileStorage.write(file1);
        System.out.println(fileStorage.getFiles());
        assertEquals(fileStorage.isExists("file_2"), fileStorage.isExists("file_1"));
    }

    @Test
    private void testGetFile() throws FileAlreadyExistsException {
        FileStorage fileStorage = new FileStorage();
        fileStorage.write(file);
        assertEquals(fileStorage.getFile("fi"), file);
    }

    @Test
    private void testGetAvailableSize() throws FileAlreadyExistsException {
        FileStorage fileStorage = new FileStorage();
        File file1 = new File("file_2", "Content of file 1");
        fileStorage.write(file);
        fileStorage.write(file1);
        assertEquals(100 - (file.getSize()+file1.getSize()), fileStorage.getAvailableSize());
    }

    @Test
    private void testGetMaxSize()  {
        FileStorage fileStorage = new FileStorage();
        assertEquals(100, fileStorage.getMaxSize());
    }
}