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
        return this.start.distance(end);
    }

    public Point middle(){
        double x = Math.abs(this.start.getX() - this.end.getX()) / 2;
        double y = Math.abs(this.start.getY() - this.end.getY()) / 2;
        Point middle = new Point(x,y);
        return middle;
    }

    public Point start(){
        return this.start;
    }


    public Point end(){
        return this.end;
    }


    public boolean isIntersecting(Line other){
        Point curr_start = this.start;
        Point curr_end = this.end;
        Point other_start = other.start;
        Point other_end = other.end;
        double o1 = orientation(curr_start, other_start, curr_end);
        double o2 = orientation(curr_start, other_start, other_end);
        double o3 = orientation(curr_end, other_end, curr_start);
        double o4 = orientation(curr_end, other_end, other_start);

        // General case
        if (o1 != o2 && o3 != o4)
            return true;

        // Special Cases
        // p1, q1 and p2 are colinear and p2 lies on segment p1q1
        if (o1 == 0 && onLine(curr_start, curr_end, other_start)) return true;

        // p1, q1 and q2 are colinear and q2 lies on segment p1q1
        if (o2 == 0 && onLine(curr_start, other_end, other_start)) return true;

        // p2, q2 and p1 are colinear and p1 lies on segment p2q2
        if (o3 == 0 && onLine(curr_end, curr_start, other_end)) return true;

        // p2, q2 and q1 are colinear and q1 lies on segment p2q2
        if (o4 == 0 && onLine(curr_end, other_start, other_end)) return true;

        return false;
    }

    private boolean onLine(Point p1, Point p2, Point p3){
        boolean ans =false;
        if((p2.getX() >= Math.min(p1.getX(),p3.getX()) && p2.getX() <= Math.max(p1.getX(),p3.getX())) &&
                (p2.getY() >= Math.min(p1.getY(),p3.getY())) && (p2.getY() <= Math.max(p1.getY(),p3.getY()))){
            ans =true;
        }
        return ans;
    }


    private double orientation(Point p1, Point p2, Point p3){
        double val = (p2.getY() - p1.getY()) * (p3.getX() - p2.getX()) -
                (p2.getX() - p1.getX()) * (p3.getY() - p2.getY());

        if(val == 0){
            return 0;
        }
        if (val > 0){
            return 1;
        }else {
            return 2;
        }
    }






}
