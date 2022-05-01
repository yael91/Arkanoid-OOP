import biuoop.DrawSurface;

/**
 * Animation: interface - screen animation.
 */
public interface Animation {

    /**
     * doOneFrame  - do one frame.
     *
     * @param d  DrawSurface.
     * @param dt double
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * shouldStop returns boolean.
     *
     * @return as above.
     */
    boolean shouldStop();

}
