

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Class Ball describes a ball and it's functions.
 *
 * @author Tomer Yona
 * @version 1.2 15 Mar 2019
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private Frame frame;
    private GameEnvironment gameEnvironment;

    /**
     * @param center .
     * @param r .
     * @param color .
     * @param gameEnvironment1 .
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment1) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.frame = new Frame();
        this.gameEnvironment = gameEnvironment1;
    }

    /**
     *
     * @param i .
     * @param i1 .
     * @param i2 .
     * @param red .
     * @param gameEnvironment1 .
     */
    public Ball(int i, int i1, int i2, Color red, GameEnvironment gameEnvironment1) {
        this.center = new Point(i, i1);
        this.r = i2;
        this.color = red;
        this.frame = new Frame();
        this.gameEnvironment = gameEnvironment1;

    }

    /**
     * Ball constructor.
     *
     * @param i     .
     * @param i1    .
     * @param i2    .
     * @param red   .
     * @param frame .
     * @param gameEnvironment1 .
     */
    public Ball(int i, int i1, int i2, Color red, Frame frame, GameEnvironment gameEnvironment1) {
        this.center = new Point(i, i1);
        this.r = i2;
        this.color = red;
        this.frame = frame;
        this.gameEnvironment = gameEnvironment1;


    }

    /**
     * constructor.
     * @param i .
     * @param i1 .
     * @param i2 .
     * @param red .
     * @param frame .
     * @param gameEnvironment1 .
     * @param v .
     */
    public Ball(int i, int i1, int i2, Color red, Frame frame, GameEnvironment gameEnvironment1, Velocity v) {
        this.center = new Point(i, i1);
        this.r = i2;
        this.color = red;
        this.frame = frame;
        this.gameEnvironment = gameEnvironment1;
        this.velocity = v;

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

    @Override
    public void timePassed() {
        moveOneStep();
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

        // variables with values of the ball parameters.
        double dx = this.velocity.getDx();
        double dy = this.velocity.getDy();
        double centerX = this.center.getX();
        double centerY = this.center.getY();
        // the trajectory of the ball is where it will be in the next movement
        Point trajectoryStart = new Point(centerX, centerY);
        Point trajectoryEnd = new Point(centerX + dx, centerY + dy);
        Point nextCenter;
        Line trajectory = new Line(trajectoryStart, trajectoryEnd);

        CollisionInfo currColInfo = this.gameEnvironment.getClosestCollision(trajectory);
        // if there wont be collision in the trajectory of the ball then we will continue without changing the speed
        if (currColInfo == null) {
            this.center = this.velocity.applyToPoint(this.center);
            // if there is a collision get the collisionPoint the the collidable object
        } else {
            Collidable colObject = this.gameEnvironment.getClosestCollision(trajectory).collisionObject();
            Point colPoint = this.gameEnvironment.getClosestCollision(trajectory).collisionPoint();
            //move the ball to "almost" the hit point, but just slightly before it.
            double locationY = (Math.abs(colPoint.getY() - this.center.getY()) / 4);
            double locationX = (Math.abs(colPoint.getX() - this.center.getX()) / 4);

            if (colPoint.getY() > this.center.getY()) {
                centerY = colPoint.getY() - locationY;
            } else {
                centerX = colPoint.getY() + 3 * locationY;
            }
            if (colPoint.getX() > this.center.getX()) {
                centerX = colPoint.getX() - locationX;
            } else {
                centerX = colPoint.getX() + 3 * locationX;
            }
            this.center = new Point(centerX, centerY);
            //update velocity using collidable hit function.
            this.setVelocity(colObject.hit(colPoint, this.velocity));

        }
    }


    /**
     * @param gameEnvironment1 .
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment1) {
        this.gameEnvironment = gameEnvironment1;
    }

}
