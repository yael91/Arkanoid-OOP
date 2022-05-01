import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.util.LinkedList;

/**
 * WideEasy  : details of all the level.
 */
public class WideEasy implements LevelInformation {
    private int blockX;
    private int blockY;
    private int blockSize;
    private int blockWidth = 50;
    private int blockHeight = 25;

    /**
     * WideEasy constructor .
     *
     * @param width  int.
     * @param height int.
     */
    public WideEasy(int width, int height) {
        blockSize = width / 30;
        blockX = width / 2;
        blockY = height / 4;
    }

    /**
     * numberOfBalls  : returns int number of balls.
     *
     * @return int number of balls.
     */
    public int numberOfBalls() {
        return 10;
    }

    /**
     * initialBallVelocities  : The initial velocity of each ball.
     *
     * @return List<Velocity>.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocy = new LinkedList();
        double speed = 10.0D;
        velocy.add(Velocity.fromAngleAndSpeed(50.0D, speed));
        velocy.add(Velocity.fromAngleAndSpeed(40.0D, speed));
        velocy.add(Velocity.fromAngleAndSpeed(30.0D, speed));
        velocy.add(Velocity.fromAngleAndSpeed(20.0D, speed));
        velocy.add(Velocity.fromAngleAndSpeed(10.0D, speed));
        velocy.add(Velocity.fromAngleAndSpeed(-10.0D, speed));
        velocy.add(Velocity.fromAngleAndSpeed(-20.0D, speed));
        velocy.add(Velocity.fromAngleAndSpeed(-30.0D, speed));
        velocy.add(Velocity.fromAngleAndSpeed(-40.0D, speed));
        velocy.add(Velocity.fromAngleAndSpeed(-50.0D, speed));
        return velocy;
    }

    /**
     * paddleSpeed  : The speed of the paddle.
     *
     * @return int number.
     */
    public int paddleSpeed() {
        return 80;
    }

    /**
     * paddleWidth  : The width of the paddle.
     *
     * @return int number.
     */
    public int paddleWidth() {
        return 600;
    }

    /**
     * paddleSpeed  :the level name will be displayed at the top of the screen.
     *
     * @return string level name.
     */
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * blocks  :  Returns The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return List<Block>.
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();
        double x = 25;
        //int blockHeight = 25;
        Color[] arrayColor = new Color[]{Color.cyan, Color.cyan, Color.pink, Color.green, Color.green, Color.green,
                Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.BLUE, Color.BLUE, Color.yellow, Color.yellow, Color.RED,
                Color.RED};
        for (int i = 0; i < 15; ++i) {
            Rectangle rectangle = new Rectangle(new Point(x, 250), blockWidth, blockHeight);
            Block block = new Block(rectangle, arrayColor[i], 1);
            block.setNumberHits(1);
            //block.set text????
            blocks.add(block);
            x = x + blockWidth;
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
        return blocks().size();
    }

    /**
     * getBackground  :  Returns a sprite with the background of the level.
     *
     * @return sprite.
     */
    public Sprite getBackground() {
        return new DirectHitBackground();
    }

    /**
     * DirectHitBackground  :Direct Hit Background.
     */
    private class DirectHitBackground implements Sprite {
        /**
         * drawOn : the function draw anf fill the blocks and add number of hit.
         *
         * @param d : DrawSurface.
         */
        @Override
        public void drawOn(DrawSurface d) {
            d.setColor(Color.WHITE);
            d.fillRectangle(0, 18, d.getWidth(), d.getHeight());
            d.setColor(Color.decode("#efe7b0"));
            d.fillCircle(150, 150, 60);
            int numRays = 100;
            int startX = 25;
            int endX = 775;

            for (int i = 1; i <= numRays; ++i) {
                d.drawLine(150, 150, (endX - startX) / numRays * i, 250);
            }

            d.setColor(Color.decode("#ecd749"));
            d.fillCircle(150, 150, 50);
            d.setColor(Color.decode("#ffe118"));
            d.fillCircle(150, 150, 40);
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

    @Override
    public void setLevelName(String string) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setInitialBallVelocities(List<Velocity> velocitiesList) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setBlocks(List<Block> blocks) {
        // TODO Auto-generated method stub

    }
}

