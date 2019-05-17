package game;

import collision.Collidable;
import collision.CollisionInfo;
import geometry.Line;
import geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * A GameEnvironment class.
 *
 * @author Tomer Yona
 */
public class GameEnvironment {
    private List<Collidable> collidablelist;


    /**
     * this is the constructor of the class.
     */
    public GameEnvironment() {
        this.collidablelist = new ArrayList<Collidable>();
    }

    /**
     * Colliadable list getter.
     * @return List
     */
    public List<Collidable> getCollidablelist() {
        return this.collidablelist;
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c .
     */
    public void addCollidable(Collidable c) {
        this.collidablelist.add(c);
    }


    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory .
     * @return CollisionInfo
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Point> collidablePoint = new ArrayList<Point>();
        Point start = trajectory.start();
        int flag = -1;
        for (Collidable i : collidablelist) {
            collidablePoint.add(trajectory.closestIntersectionToStartOfLine(i.getCollisionRectangle()));
        }
        double minVal = Double.MAX_VALUE;
        for (int j = 0; j < collidablePoint.size(); j++) {
            if (collidablePoint.get(j) != null) {
                if (start.distance(collidablePoint.get(j)) < minVal) {
                    minVal = start.distance(collidablePoint.get(j));
                    flag = j;
                }
            }
        }
        if (flag != -1) {
            return new CollisionInfo(collidablePoint.get(flag), this.collidablelist.get(flag));
        } else {
            return null;
        }

    }


}
