import java.awt.Color;
import java.util.Iterator;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type High scores animation.
 */
public class HighScoresAnimation implements Animation {
    /**
     * The Scores.
     */
    private HighScoresTable scores;
    /**
     * The Keyboard sensor.
     */
    private KeyboardSensor keyboardSensor;
    private boolean stop;

    /**
     * Instantiates a new High scores animation.
     *
     * @param scores         the scores
     * @param keyboardSensor the keyboard sensor
     */
    public HighScoresAnimation(HighScoresTable scores, KeyboardSensor keyboardSensor) {
        this.scores = scores;
        this.keyboardSensor = keyboardSensor;
        stop = false;
    }

    /**
     * doOneFrame Instantiates a new High scores animation.
     *
     * @param d  the scores
     * @param dt the keyboard sensor
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.yellow);
        List<ScoreInfo> listOfScores = scores.getHighScores();
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        String message = "Highest scores: ";
        d.setColor(Color.RED);
        int startY = 100;
        d.drawText(40, startY, message, 34);
        Iterator<ScoreInfo> iterator = listOfScores.iterator();
        while (iterator.hasNext()) {
            startY += 45;
            ScoreInfo si = iterator.next();
            d.drawText(40, startY, si.getName() + ": " + String.valueOf(si.getScore()), 28);
        }

        String msg2 = "Press space to continue";
        d.setColor(Color.BLACK);
        d.drawText(200, 501, msg2, 27);

    }

    /**
     * doOneFrame Instantiates a new High scores animation.
     *
     * @return bool the keyboard sensor
     */
    @Override
    public boolean shouldStop() {
        return stop;
    }

}
