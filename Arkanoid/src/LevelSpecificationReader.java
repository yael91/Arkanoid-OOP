import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level specification reader.
 */
public class LevelSpecificationReader {

    private int xPosition;
    private int startX;
    private int yPosition;
    private int rowHeight;

    /**
     * From reader list.
     *
     * @param reader the reader
     * @return the list
     * @throws IOException the io exception
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) throws IOException {
        char[] buffer = new char[10000];
        LevelInformationImpl levelInfo = null;
        List<LevelInformation> levels = new ArrayList<>();
        if (reader == null) {
            return levels;
        }
        boolean readBlocks = false;
        BlocksFromSymbolsFactory factory = null;
        List<Block> blocks = null;

        try {
            reader.read(buffer);
            String fileContent = String.valueOf(buffer);
            fileContent = fileContent.trim();
            String[] lines = fileContent.split("\r\n");
            if (lines.length == 1) {
                lines = fileContent.split("\n");
            }
            //go through each line.
            for (String line : lines) {
                String[] lineContent = line.split(":");
                if (lineContent.length == 1) {
                    if (line.equals("START_LEVEL")) {
                        levelInfo = new LevelInformationImpl();
                        levels.add(levelInfo);
                    } else if (line.equals("START_BLOCKS")) {
                        readBlocks = true;
                        blocks = new ArrayList<>();
                    } else if (line.equals("END_BLOCKS")) {
                        readBlocks = false;
                        levelInfo.setBlocks(blocks);

                    } else if (readBlocks) {
                        xPosition = startX;
                        for (int i = 0; i < line.length(); i++) {

                            String symbol = Character.toString(line.charAt(i));

                            // if the space symbol exist.
                            if (factory.isSpaceSymbol(symbol)) {
                                xPosition += factory.getSpaceWidth(symbol);
                            } else if (factory.isBlockSymbol(symbol)) {
                                Block block = factory.getBlock(new String("" + line.charAt(i)),
                                        xPosition, yPosition);
                                if (block != null) {
                                    blocks.add(block);
                                }
                                xPosition += block.getCollisionRectangle().getWidth();

                            }
                        }
                        yPosition = yPosition + rowHeight;
                    }
                } else if (lineContent[0].equals("paddle_speed")) {
                    levelInfo.setSpeed(Integer.parseInt(lineContent[1]));
                } else if (lineContent[0].equals("paddle_width")) {
                    levelInfo.setPaddleWidth(Integer.parseInt(lineContent[1]));
                } else if (lineContent[0].equals("blocks_start_x")) {
                    this.xPosition = Integer.parseInt(lineContent[1]);
                    this.startX = xPosition;
                    int a = xPosition;
                } else if (lineContent[0].equals("blocks_start_y")) {
                    this.yPosition = Integer.parseInt(lineContent[1]);
                } else if (lineContent[0].equals("row_height")) {
                    this.rowHeight = Integer.parseInt(lineContent[1]);
                } else if (lineContent[0].equals("num_blocks")) {
                    levelInfo.setNumOfRemoveBlocks(Integer.parseInt(lineContent[1]));
                } else if (lineContent.length == 2) {
                    if (lineContent[0].equals("level_name")) {
                        levelInfo.setLevelName(lineContent[1]);
                    } else if (lineContent[0].equals("ball_velocities")) {
                        List<Velocity> velocitiesList = new ArrayList<>();
                        String[] velocities = lineContent[1].split(" ");
                        for (String velocityInfo : velocities) {
                            String[] velocityComponents = velocityInfo.split(",");
                            Velocity nextVelocity =
                                    new Velocity(new Double(velocityComponents[0]), new Double(velocityComponents[1]));
                            velocitiesList.add(nextVelocity);
                        }
                        levelInfo.setInitialBallVelocities(velocitiesList);
                    } else if (lineContent[0].equals("background")) {

                        if (lineContent[1].startsWith("image")) {
                            // If the background is image get the image file and put in the background.
                            String imageLine = lineContent[1].substring(6, lineContent[1].length() - 1);
                            try {
                                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(imageLine);
                                BufferedImage image = ImageIO.read(is);

                                levelInfo.setBufferedImage(image);
                            } catch (IOException e) {
                                System.exit(0);
                            }
                        } else if (lineContent[1].startsWith("color")) {
                            // If the background is a color create the color from string.
                            Color color;
                            color = ColorsParser.colorFromString(lineContent[1]);
                            levelInfo.setBackGroung2(color);
                        }
                    } else if (lineContent[0].equals("block_definitions")) {
                        BlocksDefinitionReader blocksReader = new BlocksDefinitionReader();
                        java.io.Reader blocksReaderFile = null;
                        try {
                            ClassLoader classLoader = getClass().getClassLoader();
                            //create file.
                            File file = new File(classLoader.getResource(lineContent[1]).getFile());
                            blocksReaderFile = new FileReader(file);
                        } catch (FileNotFoundException e) {
                            int i;
                            i = 0;
                        }
                        if (blocksReaderFile != null) {
                            factory = blocksReader.fromReader(blocksReaderFile);
                        }
                    }
                }

            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return levels;
    }
}
