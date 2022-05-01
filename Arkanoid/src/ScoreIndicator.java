import biuoop.DrawSurface;

import java.awt.Color;

/**
 * ScoreIndicator: indicates the score remaining.
 */
public class ScoreIndicator implements Sprite {
    private Rectangle rect;
    private Counter score;
    private String levelName;

    /**
     * ScoreIndicator constructor .
     *
     * @param rect     Rectangle.
     * @param score    Counter.
     * @param levelNam String.
     */
    public ScoreIndicator(Rectangle rect, Counter score, String levelNam) {
        this.rect = rect;
        this.score = score;
        this.levelName = levelNam;
    }

    /**
     * drawOn : the function draw anf fill the blocks and add number of hit.
     *
     * @param d : DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.lightGray);
        d.fillRectangle(0, 0, 800, 25);
        int x = (int) rect.getUpperLeft().getX();
        int y = (int) rect.getUpperLeft().getY();
        int width = (int) rect.getWidth();
        int height = (int) rect.getHeight();
        d.fillRectangle(x, y, width, height);
        d.setColor(Color.BLACK);
        d.drawText(x + width / 3, y + (height / 2 + height / 3), "Score: " + String.valueOf(score.getValue()),
                height - (height / 4));
        d.drawText(x + 3 * width / 2, y + (height / 2 + height / 3), "Level Name: " + this.levelName,
                height - (height / 4));


    }


    /**
     * timePassed : function moves the paddle to the left/right according to the keyboard.
     * @param dt double
     */
    public void timePassed(double dt) {

    }
}
