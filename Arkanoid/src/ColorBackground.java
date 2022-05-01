import biuoop.DrawSurface;

import java.awt.Color;


/**
 * The type Color background.
 */
public class ColorBackground implements BackGround, Sprite {
    private Color color;

    /**
     * Constructor - give the background.
     * <p>
     *
     * @param color the color
     */
    public ColorBackground(Color color) {
        this.color = color;
    }

    /**
     * Draw the Background.
     * <p>
     *
     * @param surface d
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle(0, 0, surface.getWidth(), surface.getHeight());
    }

    @Override
    public void timePassed(double dt) {

    }

    /**
     * adds the background to the game.
     * <p>
     *
     * @param game - the game.
     */
    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * remove the background from the game.
     * <p>
     *
     * @param game game
     */
    @Override
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
