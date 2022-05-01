import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * DirectHit  : details of all the level.
 */
public class DirectHit implements LevelInformation {

    private int blockX;
    private int blockY;
    private int blockSize;

    /**
     * DirectHit constructor .
     *
     * @param width  int.
     * @param height int.
     */
    public DirectHit(int width, int height) {
        blockSize = width / 20;
        blockX = width / 2;
        blockY = height / 4;
    }

    @Override
    /**
     * numberOfBalls  : returns int number of balls.
     *
     * @return int number of balls.
     */
    public int numberOfBalls() {
        return 1;
    }

    @Override
    /**
     * initialBallVelocities  : The initial velocity of each ball.
     *
     * @return List<Velocity>.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vels = new ArrayList<>();
        vels.add(Velocity.fromAngleAndSpeed(0, 5));
        return vels;
    }

    @Override

    /**
     * paddleSpeed  : The speed of the paddle.
     *
     * @return int number.
     */
    public int paddleSpeed() {
        return 10;
    }

    @Override
    /**
     * paddleWidth  : The width of the paddle.
     *
     * @return int number.
     */
    public int paddleWidth() {
        return 60;
    }

    @Override

    /**
     * levelName  :the level name will be displayed at the top of the screen.
     *
     * @return string level name.
     */
    public String levelName() {
        return "DirectHit";
    }

    @Override
    /**
     * getBackground  :  Returns a sprite with the background of the level.
     *
     * @return sprite.
     */
    public Sprite getBackground() {
        return new DirectHitBackground();
    }

    @Override
    /**
     * blocks  :  Returns The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return List<Block>.
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();

        Rectangle rect = new Rectangle(new Point(blockX - blockSize / 2, blockY - blockSize / 2),
                blockSize, blockSize);
        blocks.add(new Block(rect, Color.RED, 1));
        return blocks;
    }

    @Override
    /**
     * numberOfBlocksToRemove  :  Returns The Number of levels that should be removed before the level is
     * considered to be "cleared".
     *
     * @return int.
     */
    public int numberOfBlocksToRemove() {
        return 1;
    }

    /**
     * DirectHitBackground  :Direct Hit Background.
     */
    private class DirectHitBackground implements Sprite {
        /**
         * drawOn  :draw On surface.
         *
         * @param d DrawSurface.
         */
        public void drawOn(DrawSurface d) {
            d.setColor(Color.black);
            d.fillRectangle(13, 40, 778, 575);
            d.setColor(Color.blue);
            d.drawCircle(400, 160, 101);
            d.setColor(Color.black);
            d.drawCircle(400, 160, 100);
            d.setColor(Color.blue);
            d.drawCircle(400, 160, 76);
            d.setColor(Color.black);
            d.drawCircle(400, 160, 75);
            d.setColor(Color.blue);
            d.drawCircle(400, 160, 51);
            d.setColor(Color.black);
            d.drawCircle(400, 160, 50);
            d.setColor(Color.blue);
            //d.drawLine(400, 182, 400, 302);
            d.drawLine(400, 160, 400, 302);
            d.drawLine(420, 160, 540, 162);
            d.drawLine(380, 160, 260, 162);
            d.drawLine(400, 142, 400, 22);
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
