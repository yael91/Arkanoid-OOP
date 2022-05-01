import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level sets reader.
 */
public class LevelSetsReader {

    /**
     * Gets level sets.
     *
     * @param reader the reader
     * @return the level sets
     */
    public List<LevelSet> getLevelSets(java.io.Reader reader) {
        //reads the buffer zone.
        List<LevelSet> levelSets = new ArrayList<>();
        char[] buffer = new char[10000];
        try {
            reader.read(buffer);
            String fileContent = String.valueOf(buffer);
            fileContent = fileContent.trim();
            String[] lines = fileContent.split("\r\n");
            if (lines.length == 1) {
                lines = fileContent.split("\n");
            }
            String symbol = "";
            String name = "";
            String fileName = "";
            for (int i = 0; i < lines.length; i++) {
                if (i % 2 == 0) {
                    String[] keyAndVal = lines[i].split(":");
                    if (keyAndVal.length == 2) {
                        symbol = keyAndVal[0];
                        name = keyAndVal[1];
                    }
                } else {
                    fileName = lines[i];
                    LevelSet levelSet = new LevelSet(symbol, name, fileName);
                    levelSets.add(levelSet);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return levelSets;
    }
}
