import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private boolean isAlreadyPressed = true;
    private Animation animation;
    private KeyboardSensor sensor;
    private boolean stop = false;
    private String key;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the sensor
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.sensor = sensor;
        this.key = key;
    }

    /**
     * doOneFrame.
     *
     * @param d  the key
     * @param dt the animation
     */
    public void doOneFrame(DrawSurface d, double dt) {
        animation.doOneFrame(d, dt);
        if (this.sensor.isPressed(key)) {
            if (isAlreadyPressed) {
                return;
            }
            this.stop = true;
        } else {
            isAlreadyPressed = false;
        }
    }

    /**
     * shouldStop.
     *
     * @return bool
     */
    public boolean shouldStop() {
        return stop;
    }
}
