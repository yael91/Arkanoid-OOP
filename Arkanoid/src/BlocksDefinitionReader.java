import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Blocks definition reader.
 */
public class BlocksDefinitionReader {


    /**
     * From reader blocks from symbols factory.
     *
     * @param reader the reader
     * @return the blocks from symbols factory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        Map<String, Integer> spaceWidth = new TreeMap<>();
        Map<String, BlocksCreator> blocksCreatorMap = new TreeMap<>();


        BlocksFromSymbolsFactory factory = new BlocksFromSymbolsFactory();
        try {
            char[] buffer = new char[10000];
            reader.read(buffer);
            String fileContent = String.valueOf(buffer);
            fileContent = fileContent.trim();
            String[] lines = fileContent.split("\r\n");
            if (lines.length == 1) {
                lines = fileContent.split("\n");
            }
            for (String line : lines) {
                // remove spaces from the beginning and from the end.
                line = line.trim();
                if (line.isEmpty() || (line.charAt(0) == '#')) {
                    continue;
                }
                String[] lineContent = line.split(" ");
                // remove spaces from the beginning and from the end.
                if (lineContent.length > 0) {
                    if (lineContent[0].equals("default")) {
                        for (int i = 1; i < lineContent.length; i++) {
                            String[] parameter = lineContent[i].split(":");
                            factory.addDefaultParameter(parameter[0], parameter[1]);
                        }
                        // bdef from the beginning and from the end.
                    } else if (lineContent[0].equals("bdef")) {
                        String symbol = lineContent[1].split(":")[1];
                        // start from 2 to skip the bdef and the symbol
                        for (int i = 2; i < lineContent.length; i++) {
                            String[] parameter = lineContent[i].split(":");
                            factory.addParameter(symbol, parameter[0], parameter[1]);
                        }
                    } else if (lineContent[0].equals("sdef")) {
                        String symbol = lineContent[1].split(":")[1];
                        for (int i = 2; i < lineContent.length; i++) {
                            String[] parameter = lineContent[i].split(":");
                            factory.addParameter(symbol, parameter[0], parameter[1]);
                            factory.addSpacerWidths(symbol, parameter[1]);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return factory;
    }

}
