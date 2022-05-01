/**
 * ScoreTrackingListener: Score Tracking.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * ScoreTrackingListener constructor .
     *
     * @param scoreCounter Counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * hitEvent  : This method is called whenever the beingHit object is hit.
     *
     * @param hitter   The hitter parameter is the Ball that's doing the hitting..
     * @param beingHit Block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getNumberHits() >= 1) {

            currentScore.increase(5);
        }
        if (beingHit.getNumberHits() < 1) {

            currentScore.increase(15);
        }
    }
}
