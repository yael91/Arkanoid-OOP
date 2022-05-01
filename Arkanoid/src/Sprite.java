import biuoop.DrawSurface;

/**
 * Sprite : interface that includes each object that thought to be a Sprite.
 */
public interface Sprite {
    /**
     * drawOn : the function draw anf fill the blocks and add number of hit.
     *
     * @param d : DrawSurface.
     */
    void drawOn(DrawSurface d);


    /**
     * timePassed : function moves the paddle to the left/right according to the keyboard.
     * @param dt double
     */
    void timePassed(double dt);
}
