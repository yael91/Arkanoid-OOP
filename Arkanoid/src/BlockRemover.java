/**
 * BlockRemover: for removing a block from the game level according to the hitlistener.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;
    private HitListener hl;

    /**
     * BallRemover constructor .
     *
     * @param game          GameLevel.
     * @param removedBlocks Counter.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
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
        if (beingHit.getNumberHits() == 0) {
            //remove block.
            beingHit.removeFromGame(this.game);
            //decrease from counter.
            remainingBlocks.decrease(1);
            //remove from listener;
            beingHit.removeHitListener(hl);
        }
    }
}
