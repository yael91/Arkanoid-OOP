import biuoop.DrawSurface;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * The type Level information.
 */
public class LevelInformationImpl implements LevelInformation {
    private int ballsNum;
    private int speed;
    private List<Velocity> velocities;
    private List<Block> blocks;
    private int paddleWidth;
    private String levelName;
    private Sprite backGroung;
    private int numOfRemoveBlocks;
    private Color backGroung2;
    private BufferedImage bufferedImage;

    @Override
    public int numberOfBalls() {
        return ballsNum;
    }

    /**
     * Sets balls num.
     *
     * @param ballsNum1 the balls num
     */
    public void setBallsNum(int ballsNum1) {
        this.ballsNum = ballsNum1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        // TODO Auto-generated method stub
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        // TODO Auto-generated method stub
        return speed;
    }

    /**
     * Sets speed.
     *
     * @param speed1 the speed
     */
    public void setSpeed(int speed1) {
        this.speed = speed1;
    }

    @Override
    public int paddleWidth() {
        return paddleWidth;
    }

    /**
     * Sets paddle width.
     *
     * @param paddleWidth1 the paddle width
     */
    public void setPaddleWidth(int paddleWidth1) {
        this.paddleWidth = paddleWidth1;
    }

    @Override
    public String levelName() {
        return levelName;
    }


    /**
     * getBackground.
     *
     * @return sprite
     */
    public Sprite getBackground() {

        /**
         * getBackground.
         *
         */
        class Background implements Sprite {
            /**
             * drawOn.
             *
             * @param  d double
             */
            public void drawOn(DrawSurface d) {

                if (backGroung2 != null) {
                    d.setColor(backGroung2);
                    d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
                }
                Image image;
                if (bufferedImage != null) {
                    d.drawImage(0, 0, bufferedImage);
                }
            }

            /**
             * timePassed.
             *
             * @param  dt double
             */
            public void timePassed(double dt) {
            }
        }
        return new Background();
    }


    /**
     * Sets back groung 2.
     *
     * @param backGroung21 the back groung 2
     */
    public void setBackGroung2(Color backGroung21) {
        this.backGroung2 = backGroung21;
    }

    /**
     * Sets back groung.
     *
     * @param backGroung1 the back groung
     */
    public void setBackGroung(Sprite backGroung1) {
        this.backGroung = backGroung1;
    }

    @Override
    public List<Block> blocks() {
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return numOfRemoveBlocks;
    }

    /**
     * Sets num of remove blocks.
     *
     * @param numOfRemoveBlocks1 the num of remove blocks
     */
    public void setNumOfRemoveBlocks(int numOfRemoveBlocks1) {
        this.numOfRemoveBlocks = numOfRemoveBlocks1;
    }

    /**
     * Sets level name.
     *
     * @param string the string
     */
    public void setLevelName(String string) {
        this.levelName = string;

    }

    /**
     * Sets initial ball velocities.
     *
     * @param velocitiesList the velocities list
     */
    public void setInitialBallVelocities(List<Velocity> velocitiesList) {
        velocities = velocitiesList;

    }

    /**
     * Sets blocks.
     *
     * @param blocks1 the blocks
     */
    public void setBlocks(List<Block> blocks1) {
        this.blocks = blocks1;

    }

    /**
     * Sets buffered image.
     *
     * @param bufferedImage1 the buffered image
     */
    public void setBufferedImage(BufferedImage bufferedImage1) {
        this.bufferedImage = bufferedImage1;
    }


}
