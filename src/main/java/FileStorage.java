import exception.FileAlreadyExistsException;

import java.util.ArrayList;
import java.util.List;


public class FileStorage {
    private ArrayList<File> files = new ArrayList<>();
    private int availableSize = 100;
    private int maxSize = 100;

    /**
     * Construct object and set max storage size and available size based on passed value
     * @param size FileStorage size
     */
    public FileStorage(int size) {
        maxSize = size;
        availableSize += maxSize;
    }

    /**
     * Construct object and set max storage size and available size based on default value=100
     */
    public FileStorage() {
    }

    //проверка эксепшена + таймаута - стоит разделять тесты по проверяемой функциональности

    /**
     *
     * @param file to save in file storage
     * @return result of file saving
     * @throws FileAlreadyExistsException in case of already existent file
     */
    public boolean write(File file) throws FileAlreadyExistsException {
        if (files.contains(file)) {
            throw new FileAlreadyExistsException();
        }
        if (file.getSize() > availableSize) {
            return false;
        }
        files.add(file);
        availableSize -= file.getSize();
        if (availableSize % 2 == 0) {
            try {
                Thread.sleep(1800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * Check is file exist in storage
     * @param fileName to search
     * @return result of checking
     */
    public boolean isExists(String fileName) {
        for (File file: files) {
            if (file.getFilename().equals(fileName)) {
                return true;
            }
        }
        return false;
    }

    //тест будет зависеть от write и isExists(т.к. нет возможности использовать моки)
    //баг - не освобождается место

    /**
     * Delete file from storage
     * @param fileName of file to delete
     * @return result of file deleting
     */
    public boolean delete(String fileName) {
        return files.remove(getFile(fileName));
    }

    /**
     * Get all Files saved in the storage
     * @return list of files
     */
    public List<File> getFiles() {
        return files;
    }

    /**
     * Get file by filename
     * @param fileName of file to get
     * @return file
     */
    public File getFile(String fileName) {
        for (File file: files) {
            if (file.getFilename().contains(fileName)) {
                return file;
            }
        }
        return null;
    }

    /**
     * Get available storage size
     * @return available size
     */
    public int getAvailableSize() {
        return availableSize;
    }

    /**
     * Get maximum storage size
     * @return max size
     */
    public int getMaxSize() {
        return maxSize;
    }
}
