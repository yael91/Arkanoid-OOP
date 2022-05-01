import biuoop.DrawSurface;

/**
 * Ball : create ball with canter radios and color.
 */
public class Ball implements Sprite {
    //declarations.
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment gameEnvironment;
    private Rectangle rect;

    /**
     * constructor : restores start and end for the Line.
     *
     * @param cent    : point number.
     * @param rad     : int number.
     * @param colored : color.
     */
    public Ball(Point cent, int rad, java.awt.Color colored) {
        this.center = cent;
        this.r = rad;
        this.color = colored;
    }

    /**
     * accessor : excess to x value of a point.
     *
     * @return center x value.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * accessor : excess to y value of a point.
     *
     * @return y value.
     * @center x value.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * accessor : excess to r value of a point.
     *
     * @return radios value.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * accessor : excess to color of a point.
     *
     * @return color. color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * drawOn : draw the ball on the given DrawSurface.
     *
     * @param surface : surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), r);
    }

    /**
     * setGameEnvironment : sets the game enviroment..
     *
     * @param gameEnvi : GameEnvironment.
     */
    public void setGameEnvironment(GameEnvironment gameEnvi) {
        this.gameEnvironment = gameEnvi;
    }

    /**
     * setVelocity : set the velocity.
     *
     * @param velo : Velocity v.
     */
    public void setVelocity(Velocity velo) {
        this.v = velo;
    }

    /**
     * setVelocity : set the velocity.
     *
     * @param dx : double dx.
     * @param dy : double dy.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * accessor : excess the velocity.
     *
     * @return velocity. velocity
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * moveOneStep : moves step according to the velocity and borders..
     *
     * @param dt the dt
     */
    public void moveOneStep(double dt) {
        // restores the point od the ball.

        Point end = this.getVelocity().applyToPoint(this.center, dt);
        // change the dx according to the borders.
        Line trajectory = new Line(this.center, end);
        CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(trajectory);
        if (collisionInfo == null) {
            this.center = end;
            return;
        }
        Rectangle rectangle = collisionInfo.collisionObject().getCollisionRectangle();

        // move the ball back
        double dx = -v.getDx();
        double dy = -v.getDy();
        double size = trajectory.length();
        dx /= size;
        dy /= size;
        center = new Velocity(dx, dy).applyToPoint(collisionInfo.collisionPoint(), dt);
        this.v = collisionInfo.collisionObject().hit(collisionInfo.collisionPoint(), this.v, this);
        //if the ball is in the Paddle.
        if (collisionInfo.collisionObject() instanceof Paddle) {
            if (rectangle.getUpperLeft().getX() <= this.center.getX() && rectangle.getUpperLeft().getX()
                    + rectangle.getWidth() >= this.center.getX()
                    && rectangle.getUpperLeft().getY() <= this.center.getY()
                    && rectangle.getUpperLeft().getY() + rectangle.getHeight() >= this.center.getY()
                    && this.center.getY() >= rectangle.getUpperLeft().getY()) {
                this.center = new Point(this.center.getX(), rectangle.getUpperLeft().getY() - getSize());
            }
        }
    }

    /**
     * timePassed :moves the ball.
     *
     * @param dt double
     */
    public void timePassed(double dt) {
        moveOneStep(dt);
    }

    /**
     * addToGame : adds ball to the game.
     *
     * @param g : Game variable.
     */
    public void addToGame(GameLevel g) {
        this.gameEnvironment = g.getEnvironment();
        g.addSprite(this);
    }

    /**
     * removeFromGame:remove From Game ball to the game.
     *
     * @param g : Game variable.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    /**
     * getSpeed : calculate the speed by Pithagoras equasion.
     *
     * @return double value.
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(this.v.getDx(), 2) + Math.pow(this.v.getDy(), 2));
    }
}

