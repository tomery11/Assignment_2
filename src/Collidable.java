/**
 * interface that describes a Collidable object.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public interface Collidable {


    /**
     * Return the "collision shape" of the object.
     *
     * @return Rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint .
     * @param currentVelocity .
     * @return Velocity
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);

}
