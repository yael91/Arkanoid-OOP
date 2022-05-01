
import java.awt.Color;

import biuoop.DrawSurface;

/**
 * The type Menu background.
 */
public class MenuBackground implements Sprite {

    private Rectangle frame;
    private Color color;

    /**
     * Constructor - create the background rectangle and give it color.
     */
    public MenuBackground() {
        this.frame = new Rectangle(new Point(0, 0), 800, 600);
        this.color = new Color(0, 128, 255);
    }

    @Override
    /**
     * draw the background.
     * <p>
     * @param surface - the given DrawSurface. */
    public void drawOn(DrawSurface surface) {
        // Draw the background.
        surface.setColor(this.color);
        surface.fillRectangle(0, 0, 600, 800);

    }

    /**
     * adds the background to the game.
     * <p>
     *
     * @param game - the game.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * remove the background from the game.
     * <p>
     *
     * @param game - the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

    /**
     * remove the background from the game.
     *
     * @param dt - the game
     */
    public void timePassed(double dt) {

    }
}