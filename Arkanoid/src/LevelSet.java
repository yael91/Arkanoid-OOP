/**
 * The type Level set.
 */
public class LevelSet {
    private String symbol;
    private String name;
    private String fileName;

    /**
     * Instantiates a new Level set.
     *
     * @param symbol   the symbol
     * @param name     the name
     * @param fileName the file name
     */
    public LevelSet(String symbol, String name, String fileName) {
        this.symbol = symbol;
        this.name = name;
        this.fileName = fileName;
    }

    /**
     * Gets symbol.
     *
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets file name.
     *
     * @return the file name
     */
    public String getFileName() {
        return fileName;
    }
}
