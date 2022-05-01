/**
 * BallRemover: for removing a ball from the game level according to the hitlistener.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * BallRemover constructor .
     *
     * @param game         Counter for score.
     * @param removedBalls KeyboardSensor.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }


    /**
     * hitEvent :Blocks that are hit and reach 0 hit-points should be removed
     * from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     *
     * @param beingHit Block.
     * @param hitter   Ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (remainingBalls.getValue() > 0) {
            //remove ball.
            hitter.removeFromGame(this.game);
            //decrease from counter.
            remainingBalls.decrease(1);
        }
    }
}
