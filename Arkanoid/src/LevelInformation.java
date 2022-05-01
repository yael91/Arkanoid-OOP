import java.util.List;

/**
 * LevelInformation  : interface - informations of all the levels.
 */
public interface LevelInformation {
    /**
     * numberOfBalls  : returns int number of balls.
     *
     * @return int number of balls.
     */
    int numberOfBalls();

    /**
     * initialBallVelocities  : The initial velocity of each ball.
     *
     * @return List<Velocity> .
     */
    List<Velocity> initialBallVelocities();

    /**
     * paddleSpeed  : The speed of the paddle.
     *
     * @return int number.
     */
    int paddleSpeed();

    /**
     * paddleWidth  : The width of the paddle.
     *
     * @return int number.
     */
    int paddleWidth();

    /**
     * paddleSpeed  :the level name will be displayed at the top of the screen.
     *
     * @return string level name.
     */
    String levelName();

    /**
     * getBackground  :  Returns a sprite with the background of the level.
     *
     * @return sprite. background
     */
    Sprite getBackground();

    /**
     * blocks  :  Returns The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return List<Block> .
     */
    List<Block> blocks();

    /**
     * numberOfBlocksToRemove  :  Returns The Number of levels that should be removed before the level is
     * considered to be "cleared".
     *
     * @return int. int
     */
    int numberOfBlocksToRemove();

    /**
     * Sets level name.
     *
     * @param string the string
     */
    void setLevelName(String string);

    /**
     * Sets initial ball velocities.
     *
     * @param velocitiesList the velocities list
     */
    void setInitialBallVelocities(List<Velocity> velocitiesList);

    /**
     * Sets blocks.
     *
     * @param blocks the blocks
     */
    void setBlocks(List<Block> blocks);
}