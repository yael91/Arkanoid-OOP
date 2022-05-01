import biuoop.DrawSurface;

import java.awt.Color;

/**
 * LivesIndicator: indicates the lives remaining.
 */
public class LivesIndicator implements Sprite {

    private Rectangle rect;
    private Counter livesScore;
    private String levelName;

    /**
     * LivesIndicator constructor .
     *
     * @param rect      Rectangle.
     * @param liveScore Counter.
     * @param levelNam  String.
     */
    public LivesIndicator(Rectangle rect, Counter liveScore, String levelNam) {
        this.rect = rect;
        this.livesScore = liveScore;
        this.levelName = levelNam;
    }

    /**
     * drawOn : the function draw anf fill the blocks and add number of hit.
     *
     * @param d : DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        int x = (int) rect.getUpperLeft().getX();
        int y = (int) rect.getUpperLeft().getY();
        int width = (int) rect.getWidth();
        int height = (int) rect.getHeight();

        d.setColor(Color.BLACK);
        ;
        d.drawText(x + width / 2, y + (height / 2 + height / 3), "Lives: " + String.valueOf(livesScore.getValue()),
                height - (height / 4));

    }

    /**
     * timePassed : function moves the paddle to the left/right according to the keyboard.
     *
     * @param dt double
     */
    public void timePassed(double dt) {
    }
}


