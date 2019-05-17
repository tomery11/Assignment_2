package sprite;
import collision.Velocity;
import game.*;
import geometry.*;
import biuoop.DrawSurface;
import collision.Collidable;

import java.awt.Color;

/**
 * this is a class that creates frame block.
 *
 * @author tomer yona
 */
public class FrameBoundary implements Sprite, Collidable {

    private Rectangle frameRec;

    /**
     * constructor.
     *
     * @param frameRectangle .
     */
    public FrameBoundary(Rectangle frameRectangle) {
        this.frameRec = frameRectangle;
    }

    @Override
    /**
     * get collision Rectangle.
     * @return Rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.frameRec;
    }

    @Override
    /**
     * hit method sets the velocity according to the hit that will come.
     * @param Point .
     * @param Velocity .
     * @return Velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // we get the lines of the rectangle.
        Line l1 = this.frameRec.getUpperLine();
        Line l2 = this.frameRec.getLowerLine();
        Line l3 = this.frameRec.getLeftSideLine();
        Line l4 = this.frameRec.getRightSideLine();
        //if the collision is in the top or bottom lines
        if ((l1.onLine(collisionPoint)) || (l2.onLine(collisionPoint))) {
            // change the y's velocity into it's negative if so
            return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
        } else if ((l3.onLine(collisionPoint)) || (l4.onLine(collisionPoint))) {
            // checks if the collision is in the right or left lines
            // change the x's velocity into it's negative
            return new Velocity((-1) * currentVelocity.getDx(), currentVelocity.getDy());
        } else {
            // if it didn't hit any of the 4 lines (which never happens), return the same velocity
            return currentVelocity;
        }
    }


    @Override
    /**
     * draws the frame on the surface.
     * @param DrawSurface .
     */
    public void drawOn(DrawSurface d) {
        // draw a frame in color Black.
        d.setColor(Color.BLACK);

        d.drawRectangle((int) frameRec.getUpperLeft().getX(),
                (int) frameRec.getUpperLeft().getY(),
                (int) frameRec.getWidth(), (int) frameRec.getHeight());

        d.setColor(Color.GRAY);
        d.fillRectangle((int) frameRec.getUpperLeft().getX(),
                (int) frameRec.getUpperLeft().getY(), (int) frameRec.getWidth(),
                (int) frameRec.getHeight());
        d.setColor(Color.WHITE);
        d.drawText((int) this.frameRec.getUpperLeft().getX()
                        + (int) (0.5 * this.frameRec.getWidth()),
                (int) this.frameRec.getUpperLeft().getY() + (int) (0.5 * this.frameRec.getHeight()),
                "X", 20);
    }

    @Override
    /**
     * time passed method.
     */
    public void timePassed() {

    }
}
