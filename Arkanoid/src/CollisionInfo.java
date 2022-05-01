/**
 * CollisionInfo : includes collision info od the collidable object and collision Point.
 */
public class CollisionInfo {
    private Point collisionP;
    private Collidable collid;

    /**
     * CollisionInfo : restores start and end for the Line.
     *
     * @param collisionPo : point value of collision.
     * @param collidO     : Collidable object.
     */
    public CollisionInfo(Point collisionPo, Collidable collidO) {
        this.collisionP = collisionPo;
        this.collid = collidO;
    }

    /**
     * Collision point point.
     *
     * @return the point
     */
    public Point collisionPoint() {
        return this.collisionP;
    }

    /**
     * collisionPoint : get the collidable object involved in the collisio.
     *
     * @return collid : the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collid;
    }
}
