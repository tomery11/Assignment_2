/**
 * Class Velocity describes a velocity of a ball .
 *
 * @author Tomer Yona
 * @version 1.2 15 Mar 2019
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Velocity constructor.
     *
     * @param dx .
     * @param dy .
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * converts computation of speed axis.
     *
     * @param angle .
     * @param speed .
     * @return velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(angle) * speed;
        double dy = Math.sin(angle) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * set Velocity.
     *
     * @param dx1 .
     * @param dy1 .
     */
    public void setVelocity(double dx1, double dy1) {
        this.dx = dx1;
        this.dy = dy1;
    }

    /**
     * set velocity.
     * @param v .
     */
    public void setVelocity(Velocity v) {
        this.dx = v.dx;
        this.dy = v.dy;
    }

    /**
     * updates point after movement.
     *
     * @param p .
     * @return Point
     */
    public Point applyToPoint(Point p) {
        Point toReturn = new Point(p.getX() + this.dx, p.getY() + this.dy);
        return toReturn;
    }

    /**
     * getter of dx.
     *
     * @return double
     */
    public double getDx() {
        return dx;
    }

    /**
     * getter of dy.
     *
     * @return double
     */
    public double getDy() {
        return dy;
    }
}
