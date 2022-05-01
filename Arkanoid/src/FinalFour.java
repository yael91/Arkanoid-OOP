import biuoop.DrawSurface;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

/**
 * FinalFour : details of all the level.
 */
public class FinalFour implements LevelInformation {
    private static final int BLOCKWIDTH = 50;
    private static final int BLOCKHEIGHT = 25;
    private int blockX;
    private int blockY;
    private int blockSize;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int BORDER_SIZE = 25;

    /**
     * FinalFour constructor .
     *
     * @param width  int.
     * @param height int.
     */
    public FinalFour(int width, int height) {
        blockSize = width / 30;
        blockX = width / 2;
        blockY = height / 4;
        List<Block> blocks = new LinkedList<>();
    }

    /**
     * numberOfBalls  : returns int number of balls.
     *
     * @return int number of balls.
     */
    public int numberOfBalls() {
        return 3;
    }

    /**
     * initialBallVelocities  : The initial velocity of each ball.
     *
     * @return List<Velocity>.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new LinkedList<>();
        double speed = 7.0D;
        velocities.add(Velocity.fromAngleAndSpeed(40.0D, speed));
        velocities.add(Velocity.fromAngleAndSpeed(0.0D, speed));
        velocities.add(Velocity.fromAngleAndSpeed(-40.0D, speed));
        return velocities;
    }

    /**
     * paddleSpeed  : The speed of the paddle.
     *
     * @return int number.
     */
    public int paddleSpeed() {
        return 15;
    }

    /**
     * paddleWidth  : The width of the paddle.
     *
     * @return int number.
     */
    public int paddleWidth() {
        return 85;
    }

    /**
     * levelName  :the level name will be displayed at the top of the screen.
     *
     * @return string level name.
     */
    public String levelName() {
        return "Final Four";
    }

    /**
     * blocks  :  Returns The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return List<Block>.
     */
    public List<Block> blocks() {

        final int rows = 7;
        int cols = 15;
        //start X.
        final int startX = WIDTH - BORDER_SIZE;
        int startY = 100;
        List<Block> blocks = new ArrayList<>();
        //array of colors.
        Color[] colors = new Color[]{Color.GRAY, Color.RED, Color.YELLOW, Color.green, Color.WHITE, Color.PINK,
                Color.cyan};
        //creates blocks of given order. and set the numbers of hits.
        for (int i = 0; i < rows; ++i, startY += BLOCKHEIGHT) {
            for (int j = 0; j < cols; ++j) {
                Rectangle r = new Rectangle(new Point(startX - BLOCKWIDTH * (j + 1), startY),
                        BLOCKWIDTH, BLOCKHEIGHT);
                Block b = new Block(r, colors[i], 1);
                if (i == 0) {
                    b.setNumberHits(2);
                } else {
                    b.setNumberHits(1);
                }
                blocks.add(b);
            }
        }
        return blocks;
    }

    /**
     * numberOfBlocksToRemove  :  Returns The Number of levels that should be removed before the level is
     * considered to be "cleared".
     *
     * @return int.
     */
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }

    /**
     * getBackground  :  Returns a sprite with the background of the level.
     *
     * @return sprite.
     */
    public Sprite getBackground() {
        return new FinalFourBackground();
    }

    /**
     * FinalFourBackground  :Direct Hit Background.
     */
    private class FinalFourBackground implements Sprite {

        /**
         * drawOn  :draw On surface.
         *
         * @param d DrawSurface.
         */
        public void drawOn(DrawSurface d) {
            d.setColor(Color.decode("#1788d0"));
            d.fillRectangle(0, 18, d.getWidth(), d.getHeight());
            d.setColor(Color.WHITE);

            int i;
            for (i = 0; i < 10; ++i) {
                d.drawLine(105 + i * 10, 400, 80 + i * 10, 600);
            }

            d.setColor(Color.decode("#cccccc"));
            d.fillCircle(100, 400, 23);
            d.fillCircle(120, 420, 27);
            d.setColor(Color.decode("#bbbbbb"));
            d.fillCircle(140, 390, 29);
            d.setColor(Color.decode("#aaaaaa"));
            d.fillCircle(160, 420, 22);
            d.fillCircle(180, 400, 32);
            d.setColor(Color.WHITE);
            for (i = 0; i < 10; ++i) {
                d.drawLine(605 + i * 10, 520, 580 + i * 10, 600);
            }

            d.setColor(Color.lightGray);
            d.fillCircle(600, 500, 23);
            d.fillCircle(620, 540, 27);
            d.setColor(Color.decode("#bbbbbb"));
            d.fillCircle(640, 510, 29);
            d.setColor(Color.decode("#aaaaaa"));
            d.fillCircle(660, 530, 22);
            d.fillCircle(680, 520, 32);
        }

        /**
         * timePassed : function moves the paddle to the left/right according to the keyboard.
         *
         * @param dt double
         */
        @Override
        public void timePassed(double dt) {

        }
    }
    /**
     * setLevelName : function moves the paddle to the left/right according to the keyboard.
     *
     * @param string string
     */
    @Override
    public void setLevelName(String string) {

    }
    /**
     * setInitialBallVelocities : function moves the paddle to the left/right according to the keyboard.
     *
     * @param velocitiesList velocities List
     */
    @Override
    public void setInitialBallVelocities(List<Velocity> velocitiesList) {

    }
    /**
     * setBlocks : function moves the paddle to the left/right according to the keyboard.
     *
     * @param blocks List<Block>
     */
    @Override
    public void setBlocks(List<Block> blocks) {


    }


}
