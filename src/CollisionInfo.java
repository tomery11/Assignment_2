

/**
 * A CollisionInfo class.
 * describes the info of a Collidable.
 *
 * @author Tomer Yona
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor of CollisionInfo.
     *
     * @param p .
     * @param c .
     */
    public CollisionInfo(Point p, Collidable c) {
        this.collisionPoint = p;
        this.collisionObject = c;
    }

    /**
     * the point at which the collision occurs.
     * @return Point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * the collidable object involved in the collision.
     * @return Collidable
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
