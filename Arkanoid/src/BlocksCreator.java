
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
//import java.awt.image
import javax.imageio.ImageIO;

/**
 * Blockcreator : create Block with rectangle and color.
 */
public class BlocksCreator implements BlockCreator {

    private int width;
    private int height;
    private int hitPoints;
    private Map<Integer, Color> fillColor = new TreeMap<Integer, Color>();
    private Map<Integer, BufferedImage> fillImage = new TreeMap<Integer, BufferedImage>();
    private Color stroke;


    /**
     * BlocksCreator constructor - give the block all his parameters.
     * <p>
     *
     * @param defaults - a map with the width, height, hit points and stroke of the block.
     * @param fills    - map of the fills of the block.
     */
    public BlocksCreator(Map<String, String> defaults, Map<String, String> fills) {
        String paramVal = defaults.get("width");
        if (paramVal == null) {
            paramVal = fills.get("width");
        }
        this.width = Integer.parseInt(paramVal);
        paramVal = defaults.get("height");
        if (paramVal == null) {
            paramVal = fills.get("height");
        }
        if (paramVal != null) {
            this.height = Integer.parseInt(paramVal);
        } else {
            this.height = 20;
        }
        paramVal = defaults.get("hit_points");
        if (paramVal == null) {
            paramVal = fills.get("hit_points");
        }
        if (paramVal != null) {
            this.hitPoints = Integer.parseInt(paramVal);
        }
        paramVal = defaults.get("stroke");
        if (paramVal == null) {
            paramVal = fills.get("stroke");
        }
        if (paramVal != null) {
            setStroke(paramVal);
        }
        this.fillImage = new TreeMap<Integer, BufferedImage>();
        ColorsParser colorParser = new ColorsParser();
        for (Map.Entry<String, String> entry : fills.entrySet()) {
            String key = entry.getKey();
            if (key.startsWith("fill")) {
                if (entry.getValue().contains("image")) {
                    BufferedImage img = null;
                    try {
                        String[] temp = entry.getValue().split("\\(");
                        temp = temp[temp.length - 1].split("\\)");
                        String fileName = temp[0];
                        img = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(fileName));
                        if (entry.getKey().equals("fill")) {
                            fillImage.put(1, img);
                        } else {
                            fillImage.put(Integer.parseInt(entry.getKey().split("-")[1]), img);
                        }
                    } catch (IOException e) {
                        StackTraceElement[] stackTrace;
                        stackTrace = e.getStackTrace();
                    }
                } else {
                    try {
                        int num = 1;
                        if (key.startsWith("fill-")) {
                            int index = key.indexOf("-");
                            num = Integer.valueOf(key.substring(index + 1));
                        }
                        fillColor.put(num, colorParser.colorFromString(entry.getValue()));
                    } catch (Exception e) {
                        int i;
                        i = 1;
                    }
                }
            }
        }
    }

    @Override
    /**
     * Create a new block.
     * <p>
     * @param xpos - the x value of the upper left.
     * @param ypos - the y
     * @return new block.
     * */
    public Block create(int xpos, int ypos) {
        Point point = new Point(xpos, ypos);
        Color color = fillColor.get(1);
        if (color == null) {
            color = Color.BLACK;
        }
        //create block.
        Block block = new Block(new Rectangle(point, width, height), color, hitPoints);
        block.setColorMap(this.fillColor);
        block.setImageMap(this.fillImage);
        return block;
    }

    /**
     * Set the stroke color from a string.
     * <p>
     *
     * @param color - the color name..
     */
    public void setStroke(String color) {
        if (color.isEmpty()) {
            this.stroke = null;
            return;
        }
        ColorsParser colorParser = new ColorsParser();
        try {
            this.stroke = colorParser.colorFromString(color);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the block width.
     */
    public int getWidth() {
        return this.width;
    }
}


