/**
 * Block : create blocks. rectangled shaped,colored.
 * each block has its own hit number.
 */

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;
import java.util.Map;
import java.util.TreeMap;

import biuoop.DrawSurface;

/**
 * Block : create Block with rectangle and color.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    //private declaration.
    private Rectangle rect;
    private java.awt.Color color;
    private int hitNumber;
    private static final double NUM = 0.000001;
    private List<HitListener> hitListeners = new ArrayList<>();
    private Map<Integer, Color> colorMap = new TreeMap<Integer, Color>();
    private Map<Integer, BufferedImage> imageMap = new TreeMap<Integer, BufferedImage>();

    /**
     * constructor : restores the rectangle(block),color and text.
     *
     * @param rectangle : rectangle as block.
     * @param colored   :  color.
     * @param hitNumber the hit number
     */
    public Block(Rectangle rectangle, java.awt.Color colored, int hitNumber) {
        this.rect = rectangle;
        this.color = colored;
        this.hitNumber = hitNumber;
    }

    /**
     * addHitListener   Add hl as a listener to hit events.
     *
     * @param hl HitListener.
     */
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }


    /**
     * removeHitListener    Remove hl from the list of listeners to hit events.
     *
     * @param hl HitListener.
     */
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * accessor : excess to the rectangle.
     *
     * @return rectangle value.
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * setNumberHits : sets numbers of hits.
     *
     * @param number : int numbers of hit that occurs.
     */
    public void setNumberHits(int number) {
        this.hitNumber = number;
    }

    /**
     * accessor : excess to the numbers of hit occurs.
     *
     * @return hitNumber : int value.
     */
    public int getNumberHits() {
        return this.hitNumber;
    }

    /**
     * hit : the function changes the speed of the ball according to the hit
     * in the blocks.
     *
     * @param collisionPoint  : Pointof the collision.
     * @param currentVelocity : current velocity of the ball.
     * @param hitter          ball.
     * @return Velocity.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        this.hitNumber -= 1;
        // get the dx dy values of current velocity.
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        //get the borders of the block.
        double left = this.rect.getUpperLeft().getX();
        double right = left + this.rect.getWidth();
        double up = this.rect.getUpperLeft().getY();
        double down = up + this.rect.getHeight();
        // set in case of an epsilon case.
        double eps = NUM;
        left += eps;
        right -= eps;
        up += eps;
        down -= eps;
        // change the velocity according to the collision point.
        if (collisionPoint.getX() <= left || collisionPoint.getX() >= right) {
            dx = -dx;
        }
        if (collisionPoint.getY() <= up || collisionPoint.getY() >= down) {
            dy = -dy;
        }
        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }

    /**
     * drawOn : the function draw anf fill the blocks and add number of hit.
     *
     * @param d : DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        int flag = 0;
        d.setColor(this.color);
        if (colorMap.containsKey(this.getNumberHits())) {
            d.setColor(colorMap.get(this.getNumberHits()));
            d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                    (int) rect.getWidth(), (int) rect.getHeight());
            flag++;
        }
        if (imageMap.containsKey(this.getNumberHits())) {
            d.drawImage((int) this.getCollisionRectangle().getUpperLeft().getX(),
                    (int) this.getCollisionRectangle().getUpperLeft().getY(),
                    imageMap.get(this.getNumberHits()));
            flag++;
        }


        if (flag == 0) {
            if (colorMap.containsKey(1)) {
                d.setColor(colorMap.get(1));
                d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                        (int) rect.getWidth(), (int) rect.getHeight());
                flag++;
            } else {
                if (imageMap.containsKey(1)) {
                    d.drawImage((int) this.getCollisionRectangle().getUpperLeft().getX(),
                            (int) this.getCollisionRectangle().getUpperLeft().getY(),
                            imageMap.get(1));
                    flag++;
                }
            }
        }
        if (flag == 0) {
            d.setColor(this.color);
            d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                    (int) rect.getWidth(), (int) rect.getHeight());
        }
        d.setColor(Color.BLACK);
        d.drawRectangle((int) rect.getUpperLeft().getX(), (int) (rect.getUpperLeft().getY()),
                (int) rect.getWidth(), (int) rect.getHeight());
    }


    /**
     * timePassed : the function time passed the object.
     *
     * @param dt double
     */
    public void timePassed(double dt) {

    }

    /**
     * notifyHit  : This method is called whenever the beingHit object is hit.
     *
     * @param hitter The hitter parameter is the Ball that's doing the hitting..
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * addToGame : the function dadds to the gme the block ass a sprite and collidable.
     *
     * @param g : Game object.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * removeFromGame    remove From Game.
     *
     * @param game GameLevel.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }


    /**
     * Sets color map.
     *
     * @param colorMap1 1the color map
     */
    public void setColorMap(Map<Integer, Color> colorMap1) {
        this.colorMap = colorMap1;
    }

    /**
     * Sets image map.
     *
     * @param imageMap1 the image map
     */
    public void setImageMap(Map<Integer, BufferedImage> imageMap1) {
        this.imageMap = imageMap1;
    }
}


