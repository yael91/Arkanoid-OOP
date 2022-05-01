/**
 * The type Level set task.
 */
public class LevelSetTask implements Task<Void> {

    private String fileName;

    /**
     * Gets file name.
     *
     * @return the file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Instantiates a new Level set task.
     *
     * @param fileName the file name
     */
    public LevelSetTask(String fileName) {
        super();
        this.fileName = fileName;
    }

    @Override
    public Void run() {
        return null;
    }

}
