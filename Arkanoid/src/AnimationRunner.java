import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * AnimationRunner: for runing an animations.
 */
public class AnimationRunner {

    private GUI gui;
    private int framesPerSecond;
    private long current = System.currentTimeMillis();

    /**
     * AnimationRunner constructor :run an animation.
     *
     * @param framesPerSecond int number frames per seconds.
     * @param gui             GUI.
     */
    public AnimationRunner(int framesPerSecond, GUI gui) {
        this.framesPerSecond = framesPerSecond;
        this.gui = gui;
    }

    /**
     * run  :run the animation according to the output.
     *
     * @param animation Animation.
     */
    public void run(Animation animation) {
        double dt;
        int millisecondsPerFrame = 1000 / this.framesPerSecond; /////////////////////////////////
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            dt = (double) (System.currentTimeMillis() - this.current) / 1000;
            this.current = System.currentTimeMillis();
            animation.doOneFrame(d, dt);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
