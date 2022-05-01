import java.util.Map;
import java.util.HashMap;


/**
 * The type Blocks from symbols factory.
 */
public class BlocksFromSymbolsFactory {

    private Map<String, Integer> spacerWidths = new HashMap<>();
    private Map<String, BlockCreator> blockCreators = new HashMap<>();
    private Map<String, Map<String, String>> parameter = new HashMap<>();
    private Map<String, String> dParameter = new HashMap<String, String>();

    /**
     * Checks if is space symbol.
     *
     * @param s - the given symbol of space.
     * @return true if the given symbol belong to the spaces map.
     */
    public boolean isSpaceSymbol(String s) {

        return spacerWidths.containsKey(s);
    }

    /**
     * Checks if is block symbol.
     *
     * @param s - the given symbol of block.
     * @return true if the given symbol belong to the blocks map.
     */
    public boolean isBlockSymbol(String s) {

        return parameter.containsKey(s);
    }

    /**
     * Create new block by symbol and coordinate.
     * <p>
     *
     * @param s    - the given symbol.
     * @param xpos - the x value.
     * @param ypos - the y value.
     * @return new block.
     */
    public Block getBlock(String s, int xpos, int ypos) {
        BlockCreator creator = blockCreators.get(s);
        if (creator == null) {
            creator = new BlocksCreator(dParameter, parameter.get(s));
        }
        return creator.create(xpos, ypos);
    }

    /**
     * Gets the space width.
     *
     * @param s - the given symbol.
     * @return the width.
     */
    public int getSpaceWidth(String s) {
        return spacerWidths.get(s);
    }

    /**
     * Add default parameter.
     *
     * @param string  the string
     * @param string2 the string 2
     */
    public void addDefaultParameter(String string, String string2) {
        dParameter.put(string, string2);
    }

    /**
     * Add parameter.
     *
     * @param symbol  the symbol
     * @param string  the string
     * @param string2 the string 2
     */
    public void addParameter(String symbol, String string, String string2) {
        Map<String, String> map = parameter.get(symbol);
        if (map == null) {
            map = new HashMap<String, String>();
            parameter.put(symbol, map);
        }
        map.put(string, string2);
    }

    /**
     * Add spacer widths.
     *
     * @param symbol the symbol
     * @param width  the width
     */
    public void addSpacerWidths(String symbol, String width) {
        spacerWidths.put(symbol, Integer.valueOf(width));
    }
}
