import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * PauseScreen: screen animation for pausing.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * PauseScreen constructor .
     *
     * @param k KeyboardSensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * doOneFrame  - do one frame of the pause.
     *
     * @param d DrawSurface.
     * @param dt double
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }
    /**
     * shouldStop returns boolean.
     *
     * @return  as above.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}