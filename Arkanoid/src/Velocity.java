/**
 * Velocity: create Velocity with dx and dy.
 */
public class Velocity {
    //declarations.
    private double dx;
    private double dy;

    /**
     * Velocity : restores dx and dy.
     *
     * @param dx1 : double number.
     * @param dy1 : double number.
     */
    public Velocity(double dx1, double dy1) {
        this.dx = dx1;
        this.dy = dy1;
    }

    /**
     * accessor : excess to dx value of a velocity.
     *
     * @return dx  value.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * accessor : excess to dy value of a velocity.
     *
     * @return dy  value.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * fromAngleAndSpeed : calculates dx dy for the velocity.
     *
     * @param angle : double number.
     * @param speed : double number.
     * @return velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = (Math.sin(Math.toRadians(angle))) * speed;
        double dy = (Math.cos(Math.toRadians(angle))) * -speed;

        dx = ((int) (dx * 10000)) / 10000.0;
        dy = ((int) (dy * 10000)) / 10000.0;

        return new Velocity(dx, dy);
    }

    /**
     * applyToPoint : Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param point : point.
     * @param dt double
     * @return point.
     */
    public Point applyToPoint(final Point point, double dt) {

        return new Point(point.getX() + this.dx * dt, point.getY() + this.dy * dt);
    }

    /**
     * getSpeed : calculates the speed of the ball.
     *
     * @return double value of speed.
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(this.getDx(), 2) + Math.pow(this.getDy(), 2));
    }
}
