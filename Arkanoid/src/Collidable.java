/**
 * Collidable : interface that includes each object that thought to be a collidable.
 */
public interface Collidable  {
    /**
     * getCollisionRectangle : gets Collision Rectangle.Return the "collision shape" of the object.
     *
     * @return Rectangle.
     */
    Rectangle getCollisionRectangle();
    /**
     * hit : Notify the object that we collided with it at collisionPoint witha given velocity.
     * The return is the new velocity expected after the hit (based onthe force the object inflicted on us).
     * @param collisionPoint : point od collision.
     * @param currentVelocity : current Velocity.
     * @param hitter : ball.
     * @return Velocity.
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter);
}
