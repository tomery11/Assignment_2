/**
 * Class Line describes a Line and it's functions.
 *
 * @author Tomer Yona
 * @version 1.2 15 Mar 2019
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Constructor of line given two points.
     *
     * @param start start of line.
     * @param end   end of line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor of Line given to x and y of points.
     *
     * @param x1 .
     * @param y1 .
     * @param x2 .
     * @param y2 .
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point pointStart = new Point(x1, y1);
        Point pointEnd = new Point(x2, y2);
        this.start = pointStart;
        this.end = pointEnd;

    }

    /**
     * this function calculates the length of two lines.
     *
     * @return double
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * stringsToInts converts an array of strings to an array of ints.
     *
     * @param
     * @return int[]
     */
    public Point middle() {
        double x = Math.abs(this.start.getX() - this.end.getX()) / 2 + Math.min(this.start().getX(), this.end().getX());
        double y = Math.abs(this.start.getY() - this.end.getY()) / 2 + Math.min(this.start().getY(), this.end().getY());
        Point middle = new Point(x, y);
        return middle;
    }

    /**
     * stringsToInts converts an array of strings to an array of ints.
     *
     * @param
     * @return int[]
     */
    public Point start() {
        return this.start;
    }

    /**
     * stringsToInts converts an array of strings to an array of ints.
     *
     * @param
     * @return int[]
     */
    public Point end() {
        return this.end;
    }

    /**
     * this function checks if the 3 points are on the same line.
     *
     * @param p .
     * @param q .
     * @param r .
     * @return boolean
     */

    boolean onLine(Point p, Point q, Point r) {
        if (q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX())
                && q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY())) {
            return true;
        }


        return false;
    }

    /**
     * this function checks if a given point is located on the line.
     * @param r .
     * @return boolean
     */
    boolean onLine(Point r) {
        // check r on X axis
        if (!((Math.round(this.start.getX()) <= Math.round(r.getX())
                && Math.round(r.getX()) <= Math.round(this.end.getX()))
                || (Math.round(this.end.getX()) <= Math.round(r.getX())
                && Math.round(r.getX()) <= Math.round(this.start.getX())))) {
            return false;
        }

        // check r on y axis
        if (!((Math.round(this.start.getY()) <= Math.round(r.getY())
                && Math.round(r.getY()) <= Math.round(this.end.getY()))
                || (Math.round(this.end.getY()) <= Math.round(r.getY())
                && Math.round(r.getY()) <= Math.round(this.start.getY())))) {
            return false;
        }
        // p is on the line
        return true;
    }

    /**
     * this is an auxilary function that calculates the direction of the line and third point.
     *
     * @param a .
     * @param b .
     * @param c .
     * @return int[]
     */
    int direction(Point a, Point b, Point c) {
        double val = (b.getY() - a.getY()) * (c.getX() - b.getX())
                - (b.getX() - a.getX()) * (c.getY() - b.getY());

        if (val == 0) {
            return 0;
        }

        return (val > 0) ? 1 : 2;
    }

    /**
     * this function returns true if two lines intersect and false otherwise.
     *
     * @param other .
     * @return boolean
     */
    boolean isIntersecting(Line other) {
        Point p1 = this.start();
        Point q1 = this.end();
        Point p2 = other.start();
        Point q2 = other.end();

        int d1 = direction(p1, q1, p2);
        int d2 = direction(p1, q1, q2);
        int d3 = direction(p2, q2, p1);
        int d4 = direction(p2, q2, q1);


        if (d1 != d2 && d3 != d4) {
            return true;
        }


        if (d1 == 0 && onLine(p1, p2, q1)) {
            return true;
        }

        if (d2 == 0 && onLine(p1, q2, q1)) {
            return true;
        }

        if (d3 == 0 && onLine(p2, p1, q2)) {
            return true;
        }

        if (d4 == 0 && onLine(p2, q1, q2)) {
            return true;
        }

        return false;
    }


    /**
     * this function returns a point of if two lines intersect.
     *
     * @param other .
     * @return Point
     */
    public Point intersectionWith(Line other) {
        // Line AB represented as a1x + b1y = c1
        double a1 = this.end().getY() - this.start().getY();
        double b1 = this.start().getX() - this.end().getX();
        double c1 = a1 * (this.start().getX()) + b1 * (this.start().getY());

        // Line CD represented as a2x + b2y = c2
        double a2 = other.end().getY() - other.start().getY();
        double b2 = other.start().getX() - other.end().getX();
        double c2 = a2 * (other.start().getX()) + b2 * (other.start().getY());

        double d = (a1 * b2) - (a2 * b1);

        if (d == 0) {
            // The lines are parallel.
            return null;
        } else {
            double x = ((b2 * c1) - (b1 * c2)) / d;
            double y = ((a1 * c2) - (a2 * c1)) / d;
            return new Point(x, y);
        }
    }

    /**
     * this function returns equal if two lines are equal and false otherwise.
     *
     * @param other .
     * @return boolean
     */
    public boolean equals(Line other) {
        boolean ans = false;
        if (this.start() == other.start() && this.end() == other.end()) {
            ans = true;
        }
        return ans;
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the
     * line.
     *
     * @param rect rectangle
     * @return point that closest Intersection To Start Of Line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        double distanceTempMin = Double.MAX_VALUE;
        Line myLine = new Line(start, end);
        java.util.List<Point> list1 = rect.intersectionPoints(myLine);
        Point minDist = null;
        //if point doesn't exist return null
        if (rect.intersectionPoints(myLine) == null) {
            return null;
        } else {
            for (int i = 0; i < list1.size(); i++) {
                if (this.start.distance(list1.get(i)) < distanceTempMin) {
                    minDist = list1.get(i);
                    distanceTempMin = this.start.distance(list1.get(i));
                }
            }
        }
        return minDist;
    }

}
