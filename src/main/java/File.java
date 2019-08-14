public class File {
    private String extension;
    private String filename;
    private String content;
    private double size;
    /**
     * Construct object with passed filename (only name; optional type; without path) and content, set extension based
     * on filename and calculate size as half content length
     * @param filename File name with extension
     * @param content File content
     */
    public File(String filename, String content) {
        this.filename = filename;
        this.content = content;
        this.size = content.length() / 2;
        this.extension = filename.split("\\.")[filename.split("\\.").length - 1];
    }

    /**
     * Get File extension
     * @return File extension
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Get File size
     * @return File size
     */
    public int getSize() {
        return (int) size;
    }

    /**
     * Get File content
     * @return File content
     */
    public String getContent() {
        return content;
    }

    /**
     * Get File filename
     * @return File filename
     */
    public String getFilename() {
        return filename;
    }
}
