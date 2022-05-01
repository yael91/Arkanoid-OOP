

import java.awt.Color;

/**
 * The type Colors parser.
 */
public class ColorsParser {

    /**
     * Color from string color.
     *
     * @param line the line
     * @return the color
     * @throws Exception the exception
     */
    public static Color colorFromString(String line) throws Exception {
        Color color;
        // Check if the color is created by RBG.
        if (line.startsWith("color(RGB")) {
            // Cut the line and give split to r, g, b.
            String colorLine = line.substring(10, line.length() - 2);
            String[] colors = colorLine.split(",");
            int r = Integer.parseInt(colors[0]);
            int g = Integer.parseInt(colors[1]);
            int b = Integer.parseInt(colors[2]);
            return new Color(r, g, b);
        }
        // else, check if the color is one of specific colors, if not throw exception.
        String cutLine = line.substring(6);
        String colorLine = cutLine.substring(0, cutLine.length() - 1);
        switch (colorLine) {
            case "black":
                color = Color.black;
                break;
            case "blue":
                color = Color.blue;
                break;
            case "gray":
                color = Color.gray;
                break;
            case "lightGray":
                color = Color.lightGray;
                break;
            case "green":
                color = Color.green;
                break;
            case "orange":
                color = Color.orange;
                break;
            case "pink":
                color = Color.pink;
                break;
            case "red":
                color = Color.red;
                break;
            case "white":
                color = Color.white;
                break;
            case "yellow":
                color = Color.yellow;
                break;
            default:
                throw new Exception(" Color not exist.");
        }
        return color;
    }
}