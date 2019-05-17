package geometry;

/**
 * Class Point describes a point.
 *
 * @author Tomer Yona
 * @version 1.2 15 Mar 2019
 */
public class Point {
    private double x;
    private double y;

    /**
     * This is a constructor of Point.
     *
     * @param x .
     * @param y .
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This function returns the distance between two points.
     *
     * @param other description
     * @return double
     */
    public double distance(Point other) {
        double currX = this.x;
        double currY = this.y;
        double otherX = other.x;
        double otherY = other.y;
        double beforeSqrt = Math.pow(Math.abs(currX - otherX), 2)
                + Math.pow(Math.abs(currY - otherY), 2);
        double distance = Math.sqrt(beforeSqrt);
        return distance;
    }

    /**
     * this function checks whether two given points are equal.
     *
     * @param other .
     * @return boolean
     */
    public boolean equals(Point other) {
        boolean ans = false;
        if (this.x == other.x && this.y == other.y) {
            ans = true;
        }
        return ans;
    }

    /**
     * this function returns x.
     *
     * @return double
     */
    public double getX() {
        return this.x;
    }

    /**
     * this function returns y.
     *
     * @return double
     */
    public double getY() {
        return this.y;
    }

}
