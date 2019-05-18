package sprite;

import collision.HitListener;
import collision.HitNotifier;
import collision.Velocity;

import geometry.Point;
import geometry.Rectangle;
import geometry.Line;
import biuoop.DrawSurface;
import collision.Collidable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * this is a class that creates frame block.
 *
 * @author tomer yona
 */
public class FrameBoundary implements Sprite, Collidable, HitNotifier {

    private Rectangle frameRec;
    private boolean deathRegion;
    private List<HitListener> hitListeners;
    private int hitsOnDeathReigon;

    /**
     * constructor.
     *
     * @param frameRectangle .
     * @param isDeathRegion .
     */
    public FrameBoundary(Rectangle frameRectangle, boolean isDeathRegion) {
        this.frameRec = frameRectangle;
        this.deathRegion = isDeathRegion;
        if (isDeathRegion) {
            this.hitListeners = new ArrayList<HitListener>();
            this.hitsOnDeathReigon = 3;
        }
    }

    /**
     * Constructor.
     * @param frameRectangle .
     * @param isDeathRegion .
     * @param isScoreIndicator .
     */
    public FrameBoundary(Rectangle frameRectangle, boolean isDeathRegion, boolean isScoreIndicator) {
        this.frameRec = frameRectangle;
        this.deathRegion = isDeathRegion;
        if (isDeathRegion) {
            this.hitListeners = new ArrayList<HitListener>();
            this.hitsOnDeathReigon = 3;
        }
        if (isScoreIndicator) {
            this.hitListeners = new ArrayList<HitListener>();

        }
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
        if (!this.deathRegion) {
            d.setColor(Color.BLACK);

        }

        d.drawRectangle((int) frameRec.getUpperLeft().getX(),
                (int) frameRec.getUpperLeft().getY(),
                (int) frameRec.getWidth(), (int) frameRec.getHeight());
        if (this.deathRegion) {
            d.setColor(new Color(0, 0, 153));
        } else {
            d.setColor(Color.GRAY);
        }

        d.fillRectangle((int) frameRec.getUpperLeft().getX(),
                (int) frameRec.getUpperLeft().getY(), (int) frameRec.getWidth(),
                (int) frameRec.getHeight());
        d.setColor(Color.WHITE);
        if (!this.deathRegion) {
            d.drawText((int) this.frameRec.getUpperLeft().getX()
                            + (int) (0.5 * this.frameRec.getWidth()),
                    (int) this.frameRec.getUpperLeft().getY() + (int) (0.5 * this.frameRec.getHeight()),
                    "X", 20);
        }

    }

    @Override
    /**
     * time passed method.
     */
    public void timePassed() {

    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

}
