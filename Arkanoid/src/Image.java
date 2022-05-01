import biuoop.DrawSurface;

import java.awt.image.BufferedImage;

/**
 * The type Image.
 */
public class Image implements Sprite, BackGround {

    private BufferedImage image;

    /**
     * Constructor - give the buffer image.
     * <p>
     *
     * @param image the image
     */
    public Image(BufferedImage image) {
        this.image = image;
    }

    /**
     * drawOn : the function draw anf fill the blocks.
     *
     * @param d : DrawSurface.
     */
    public void drawOn(DrawSurface d) {

        d.drawImage(0, 0, image);
    }


    /**
     * timePassed : function moves the paddle to the left/right according to the keyboard.
     *
     * @param dt double
     */
    public void timePassed(double dt) {
        return;
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
     * @param game - the game
     */
    @Override
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
