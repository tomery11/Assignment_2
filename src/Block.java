import biuoop.DrawSurface;

import java.awt.Color;

/**
 * this class describes a block it implements the Collidable and Sprite classes.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class Block implements Collidable, Sprite {

    private Rectangle rectangle;
    private Color color;
    private String hits;

    /**
     * constructor.
     *
     * @param block .
     */
    public Block(Rectangle block) {
        this.rectangle = block;
        this.color = Color.black;
        hits = "2";
    }

    /**
     * constructor.
     *
     * @param p .
     * @param w .
     * @param h .
     */
    public Block(Point p, int w, int h) {
        this.rectangle = new Rectangle(p, w, h);
        this.color = Color.black;
        hits = "2";
    }

    /**
     * constructor.
     *
     * @param rec .
     * @param c .
     */
    public Block(Rectangle rec, Color c) {
        this.rectangle = rec;
        this.color = c;
        hits = "2";
    }


    @Override
    /**
     *Return the "collision shape" of the object.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }


    @Override
    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @param Point .
     * @param Velocity .
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double cX = collisionPoint.getX();
        double cY = collisionPoint.getY();


        if (this.rectangle.getUpperLine().onLine(collisionPoint)
                || this.rectangle.getLowerLine().onLine(collisionPoint)) {
            setHits(this.hits);
            return new Velocity(dx, -dy);
            //if ball collides with corners of block
        } else if ((Math.round(cX) == Math.round(this.rectangle.getLowerLeft().getX())
                && Math.round(cY) == Math.round(this.rectangle.getLowerLeft().getY()))
                || (Math.round(cX) == Math.round(this.rectangle.getLowerRight().getX())
                && Math.round(cY) == Math.round(this.rectangle.getLowerRight().getY()))
                || (Math.round(cX) == this.rectangle.getUpperLeft().getX()
                && Math.round(cY) == Math.round(this.rectangle.getUpperLeft().getY()))
                || ((Math.round(cX) == Math.round(this.rectangle.getUpperRight().getX()))
                && Math.round(cY) == Math.round(this.rectangle.getUpperRight().getY()))) {
            setHits(this.hits);
            return new Velocity(-dx, -dy);

        } else if (this.rectangle.getLeftSideLine().onLine(collisionPoint)
                || this.rectangle.getRightSideLine().onLine(collisionPoint)) {
            setHits(this.hits);
            return new Velocity(-dx, dy);
        } else {
            return new Velocity(dx, dy);
        }
    }

    /**
     * setter for the number of hits, decreases 1 of the current num of hits unless is already 0/X.
     *
     * @param currentNumofHits .
     */
    private void setHits(String currentNumofHits) {
        if (currentNumofHits.equals("X") || currentNumofHits.equals("1")) {
            this.hits = "X";
        } else {
            this.hits = "1";
        }

    }

    /**
     * Draws the block, frame and number of hits of the block/rectangle.
     *
     * @param surface .
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        surface.setColor(Color.WHITE);
        // here we will draw the number of hits on the blocks
        surface.drawText((int) this.rectangle.getUpperLeft().getX()
                        + (int) (0.5 * this.rectangle.getWidth()),
                (int) this.rectangle.getUpperLeft().getY() + (int) (0.5 * this.rectangle.getHeight()),
                getHits(), 20);
    }

    @Override
    /**
     * currently this function isn't used
     */
    public void timePassed() {

    }

    /**
     * return the number in String value of current hits.
     *
     * @return String
     */
    public String getHits() {
        return this.hits;
    }

    /**
     * setter for color.
     *
     * @param c .
     */
    public void setColor(Color c) {
        this.color = c;
    }
}
