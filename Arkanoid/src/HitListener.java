/**
 * HitListener  : interface - listen to when there is a hit.
 */
public interface HitListener {

    /**
     * hitEvent  : This method is called whenever the beingHit object is hit.
     *
     * @param hitter   The hitter parameter is the Ball that's doing the hitting..
     * @param beingHit Block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
