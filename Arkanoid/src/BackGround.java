
import biuoop.DrawSurface;

/**
 * The interface Back ground.
 */
public interface BackGround {
    /**
     * Draw the Background
     * Draw image as the background.
     * <p>
     *
     * @param surface - the given surface to be drew on.
     */
    void drawOn(DrawSurface surface);

    /**
     * Time passed.
     *
     * @param dt the dt
     */
    void timePassed(double dt);

    /**
     * adds the background to the game.
     * <p>
     *
     * @param game - the game.
     */
    void addToGame(GameLevel game);

    /**
     * remove the background from the game.
     * <p>
     *
     * @param game - the game
     */
    void removeFromGame(GameLevel game);
}
