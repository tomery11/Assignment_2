public class Line {
    private Point start;
    private Point end;


    public Line(Point start, Point end){
        this.start = start;
        this.end = end;
    }

    public Line(double x1, double y1, double x2, double y2){
        Point point_start = new Point(x1,y1);
        Point point_end = new Point(x2,y2);
        this.start = point_start;
        this.end = point_end;

    }


    public double length(){
        return this.start.distance(this.end);
    }

    public Point middle(){
        double x = Math.abs(this.start.getX() - this.end.getX()) / 2 + Math.min(this.start().getX(),this.end().getX());
        double y = Math.abs(this.start.getY() - this.end.getY()) / 2 + Math.min(this.start().getY(),this.end().getY());
        Point middle = new Point(x,y);
        return middle;
    }

    public Point start(){
        return this.start;
    }


    public Point end(){
        return this.end;
    }


    boolean onLine(Point p, Point q, Point r)
    {
        if (q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX()) &&
                q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY()))
            return true;

        return false;
    }

    // To find orientation of ordered triplet (p, q, r).
// The function returns following values
// 0 --> p, q and r are colinear
// 1 --> Clockwise
// 2 --> Counterclockwise
    int direction(Point p, Point q, Point r)
    {
        // See https://www.geeksforgeeks.org/orientation-3-ordered-points/
        // for details of below formula.
        double val = (q.getY() - p.getY()) * (r.getX() - q.getX()) -
                (q.getX() - p.getX()) * (r.getY() - q.getY());

        if (val == 0) return 0;  // colinear

        return (val > 0)? 1: 2; // clock or counterclock wise
    }

    // The main function that returns true if line segment 'p1q1'
// and 'p2q2' intersect.
    boolean isIntersecting(Line other)
    {
        Point p1 = this.start(); Point q1 = this.end(); Point p2 = other.start(); Point q2 = other.end();
        // Find the four orientations needed for general and
        // special cases
        int d1 = direction(p1, q1, p2);
        int d2 = direction(p1, q1, q2);
        int d3 = direction(p2, q2, p1);
        int d4 = direction(p2, q2, q1);

        // General case
        if (d1 != d2 && d3 != d4)
            return true;

        // Special Cases
        // p1, q1 and p2 are colinear and p2 lies on segment p1q1
        if (d1 == 0 && onLine(p1, p2, q1)) return true;

        // p1, q1 and q2 are colinear and q2 lies on segment p1q1
        if (d2 == 0 && onLine(p1, q2, q1)) return true;

        // p2, q2 and p1 are colinear and p1 lies on segment p2q2
        if (d3 == 0 && onLine(p2, p1, q2)) return true;

        // p2, q2 and q1 are colinear and q1 lies on segment p2q2
        if (d4 == 0 && onLine(p2, q1, q2)) return true;

        return false; // Doesn't fall in any of the above cases
    }



    public Point intersectionWith(Line other) {
        // Line AB represented as a1x + b1y = c1
        double a1 = this.end().getY() - this.start().getY();
        double b1 = this.start().getX() - this.end().getX();
        double c1 = a1 * (this.start().getX()) + b1 * (this.start().getY());

        // Line CD represented as a2x + b2y = c2
        double a2 = other.end().getY() - other.start().getY();
        double b2 = other.start().getX() - other.end().getX();
        double c2 = a2 * (other.start().getX())+ b2 * (other.start().getY());

        double determinant = (a1 * b2) - (a2 * b1);

        if (determinant == 0)
        {
            // The lines are parallel. This is simplified
            return null;
        } else {
            double x = ((b2 * c1) - (b1 * c2)) / determinant;
            double y = ((a1 * c2) - (a2 * c1)) / determinant;
            return new Point(x, y);
        }
    }

    public boolean equals(Line other) {
        boolean ans = false;
        if( this.start() == other.start() && this.end()==other.end()){
            ans = true;
        }
        return ans;
    }



}
