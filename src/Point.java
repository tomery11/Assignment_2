
/**
 * Class Sort sorts n numbers in ascending and descending ways.
 *
 * @version 1.2 01 Mar 2019
 * @author Tomer Yona
 */
public class Point {
    private double x;
    private double y;
    /**
     * stringsToInts converts an array of strings to an array of ints.
     * @param  x,y  description
     * @return int[]
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * stringsToInts converts an array of strings to an array of ints.
     * @param other  description
     * @return int[]
     */
    public double distance(Point other) {
        double currX = this.x;
        double currY = this.y;
        double otherX = other.x;
        double otherY = other.y;
        double before_sqrt = Math.pow(Math.abs(currX - otherX), 2)
                + Math.pow(Math.abs(currY - otherY), 2);
        double distance = Math.sqrt(before_sqrt);
        return distance;
    }
    /**
     * stringsToInts converts an array of strings to an array of ints.
     * @param other
     * @return int[]
     */
    public boolean equals(Point other) {
        boolean ans = false;
        if (this.x == other.x && this.y == other.y) {
            ans = true;
        }
        return ans;
    }

    /**
     * stringsToInts converts an array of strings to an array of ints.
     * @param
     * @return int[]
     */
    public double getX() {
        return this.x;
    }
    /**
     * stringsToInts converts an array of strings to an array of ints.
     * @param
     * @return int[]
     */
    public double getY() {
        return this.y;
    }

}
