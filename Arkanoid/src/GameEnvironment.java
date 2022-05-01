import java.util.List;
import java.util.ArrayList;

/**
 * GameEnvironment : create the environment of the ball check if there is a collidable
 * and if there is a collision.
 */
public class GameEnvironment {
    //declarations.
    private List<Collidable> collidables = new ArrayList<>();

    /**
     * addCollidable :  add the given collidable to the environment.
     *
     * @param c : Collidable.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * removeCollidables : remove Collidables.
     *
     * @param c : Collidable.
     */
    public void removeCollidables(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * getList : /get list of collidables.
     *
     * @return getList :  of Collidable.
     */
    public List<Collidable> getList() {
        return collidables;
    }

    /**
     * getClosestCollision : // Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory : Line of tajectory.
     * @return CollisionInfo.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //check.
        if (collidables.isEmpty()) {
            return null;
        }
        Point coll = null;
        Collidable obj = null;
        //check id there is a collision.
        for (int i = 0; i < collidables.size(); ++i) {
            Point p = trajectory.closestIntersectionToStartOfLine(collidables.get(i).getCollisionRectangle());
            if (p == null) {
                continue;
            }
            if (coll == null) {
                coll = p;
                obj = collidables.get(i);
                continue;
            }
            //check the closet collision.
            if (trajectory.start().distance(p) < trajectory.start().distance(coll)) {
                coll = p;
                obj = collidables.get(i);
            }
        }
        if (coll == null) {
            return null;
        }
        return new CollisionInfo(coll, obj);
    }
}
