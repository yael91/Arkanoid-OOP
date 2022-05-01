import biuoop.DrawSurface;

import java.awt.Color;


/**
 * CountdownAnimation:  The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) secods, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private SpriteCollection gs;
    private boolean stop;
    private int countFrom;
    private double timePassed;
    private double timePerNumber;
    private long lastTime;

    /**
     * CountdownAnimation constructor .
     *
     * @param numOfSeconds double.
     * @param countFrom    int number.
     * @param gameScreen   SpriteCollection.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.gs = gameScreen;
        this.stop = false;
        this.timePassed = 0;
        this.timePerNumber = numOfSeconds / countFrom;
        this.countFrom = countFrom;
        this.lastTime = -1;
    }

    /**
     * doOneFrame constructor .
     *
     * @param d DrawSurface.
     * @param dt double
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.gs.drawAllOn(d);
        d.setColor(Color.RED);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, String.valueOf(countFrom), 50);
        // if time passed since the last time.
        if (lastTime == -1) {
            lastTime = System.currentTimeMillis();
            return;
        }
        // if timepassed larger than time per number change the count.
        timePassed += (System.currentTimeMillis() - lastTime) / 1000.0;
        if (timePassed > timePerNumber) {
            --countFrom;
            timePassed -= timePerNumber;
        }

        if (countFrom <= 0) {
            stop = true;
        }
        lastTime = System.currentTimeMillis();
    }

    /**
     * shouldStop - returns a boolean.
     *
     * @return stop     boolean.
     */
    public boolean shouldStop() {
        return stop;
    }
}