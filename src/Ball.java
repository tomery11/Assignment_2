

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Class Ball describes a ball and it's functions.
 *
 * @author Tomer Yona
 * @version 1.2 15 Mar 2019
 */
public class Ball {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private Frame frame;

    /**
     * Ball constructor.
     *
     * @param center .
     * @param r      .
     * @param color  .
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.frame = new Frame();
    }

    /**
     * Ball constructor.
     *
     * @param i   .
     * @param i1  .
     * @param i2  .
     * @param red .
     */
    public Ball(int i, int i1, int i2, Color red) {
        this.center = new Point(i, i1);
        this.r = i2;
        this.color = red;
        this.frame = new Frame();
    }

    /**
     * Ball constructor.
     *
     * @param i     .
     * @param i1    .
     * @param i2    .
     * @param red   .
     * @param frame .
     */
    public Ball(int i, int i1, int i2, Color red, Frame frame) {
        this.center = new Point(i, i1);
        this.r = i2;
        this.color = red;
        this.frame = frame;

    }

    /**
     * getter of x point.
     *
     * @return int
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * getter of y point.
     *
     * @return int
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * getter of radius.
     *
     * @return int
     */
    public int getSize() {
        return this.r;
    }

    /**
     * getter of color.
     *
     * @return color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draws on surface.
     *
     * @param surface .
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.r);
    }

    /**
     * sets velocity to v.
     *
     * @param v .
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * sets velocity to according to two axis.
     *
     * @param dx .
     * @param dy .
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * getter of velocity.
     *
     * @return Velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * This function does the next step of the ball even if reaches a "wall".
     */
    public void moveOneStep() {
        // next step of ball
        double myX = this.center.getX() + velocity.getDx();
        double myY = this.center.getY() + velocity.getDy();

        // I will check all conditions that will keep the current ball in boundaries

        // if the ball's x is outside the start point of the frame, change it's
        // change the direction of x axis
        if (myX - this.getSize() <= frame.getStartingPoint().getX()) {
            setVelocity((-1) * velocity.getDx(), velocity.getDy());
        } else if (myX + this.getSize() >= this.frame.getWidth() + frame.getStartingPoint().getX()) {
            // if the ball's x is outside the edge of the frame, change it's
            // change the direction of x axis
            setVelocity((-1) * velocity.getDx(), velocity.getDy());
        }
        // if the ball's y is outside the start point of the frame, change it's
        // change the direction of y axis
        if (myY - this.getSize() <= frame.getStartingPoint().getY()) {
            setVelocity(velocity.getDx(), (-1) * velocity.getDy());
        } else if (myY + this.getSize() >= this.frame.getHeight() + frame.getStartingPoint().getX()) {
            // if the ball's y is outside the edge of the frame, change it's
            // change the direction of y axis
            setVelocity(velocity.getDx(), (-1) * velocity.getDy());
        }
        //apply location of ball
        this.center = this.getVelocity().applyToPoint(this.center);
    }


}
