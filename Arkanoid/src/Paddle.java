import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * Paddle : create moving Paddle as a rectangle Collidable and Sprite.
 */
public class Paddle implements Sprite, Collidable {
    private int speed;

    //declarations.
    private Rectangle rect;
    private biuoop.KeyboardSensor keyboard;
    private int leftBorder, rightBorder;

    /**
     * Paddle constructor .
     *
     * @param rect   Rectangle.
     * @param sensor KeyboardSensor.
     * @param speed  int.
     */
    public Paddle(Rectangle rect, KeyboardSensor sensor, int speed) {
        this.rect = rect;
        this.keyboard = sensor;
        this.speed = speed;
        leftBorder = -1;
        rightBorder = -1;
    }

    /**
     * constructor : restores rectangle and keyboard.
     *
     * @param rect     Rectangle rect.
     * @param keyboard KeyboardSensor keyboard.
     */
    public Paddle(Rectangle rect, KeyboardSensor keyboard) {
        this(rect, keyboard, 0);
    }

    /**
     * getCollisionRectangle : excess to rectangle object.
     *
     * @return : rectangle object..
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * setBorders :set Borders.
     *
     * @param leftBorder1  .
     * @param rightBorder1 .
     */
    public void setBorders(int leftBorder1, int rightBorder1) {
        this.leftBorder = leftBorder1;
        this.rightBorder = rightBorder1;
    }

    /**
     * moveLeft : function moves the paddle to the left.
     *
     * @param dt double
     */
    public void moveLeft(double dt) {
        if (leftBorder < 0) {
            return;
        }
        if (this.rect.getUpperLeft().getX() - speed * dt > leftBorder) {
            this.rect.setUpperLeft(new Point(this.rect.getUpperLeft().getX() - speed * dt,
                    this.rect.getUpperLeft().getY()));
        } else {
            this.rect.setUpperLeft(new Point(leftBorder,
                    this.rect.getUpperLeft().getY()));
        }
    }

    /**
     * moveRight : function moves the paddle to the right.
     *
     * @param dt double
     */
    public void moveRight(double dt) {
        if (rightBorder < 0) {
            return;
        }
        if (this.rect.getUpperLeft().getX() + this.rect.getWidth() + speed * dt < rightBorder) {

            this.rect.setUpperLeft(new Point(this.rect.getUpperLeft().getX() + speed * dt,
                    this.rect.getUpperLeft().getY()));
        } else {
            this.rect.setUpperLeft(new Point(rightBorder - rect.getWidth(),
                    this.rect.getUpperLeft().getY()));
        }

    }

    /**
     * timePassed : function moves the paddle to the left/right according to the keyboard.
     *
     * @param dt double
     */
    public void timePassed(double dt) {

        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            this.moveLeft(dt);
        }
        if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            this.moveRight(dt);
        }
    }

    /**
     * drawOn : the function draw anf fill the blocks and add number of hit.
     *
     * @param d : DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.blue);
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
    }

    /**
     * hit : the function changes the speed of the ball according if it hits
     * the paddle.
     *
     * @param collisionPoint  : Point of the collision.
     * @param currentVelocity : current velocity of the ball.
     * @param hitter          :  ball.
     * @return Velocity.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        double eps = 0.000001;
        double fifth = (this.rect.getWidth()) / 5;


        if (collisionPoint.getX() <= (this.rect.getUpperLeft().getX() + fifth) - eps) {
            return Velocity.fromAngleAndSpeed(300.0, currentVelocity.getSpeed());
        }

        if (collisionPoint.getX() <= (this.rect.getUpperLeft().getX() + (2 * fifth)) - eps) {
            return Velocity.fromAngleAndSpeed(330.0, currentVelocity.getSpeed());
        }

        if (collisionPoint.getX() <= (this.rect.getUpperLeft().getX() + (3 * fifth)) - eps) {
            return new Velocity(currentVelocity.getDx(), (currentVelocity.getDy() * (-1)));
        }

        if (collisionPoint.getX() <= (this.rect.getUpperLeft().getX() + (4 * fifth)) - eps) {
            return Velocity.fromAngleAndSpeed(30.0, currentVelocity.getSpeed());
        }
        return Velocity.fromAngleAndSpeed(60.0, currentVelocity.getSpeed());
    }

    /**
     * addToGame : the function adds to the game the paddle ass a sprite and collidable.
     *
     * @param g : Game object.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * removeFromGame : remove From Game.
     *
     * @param g : Game object.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }
}