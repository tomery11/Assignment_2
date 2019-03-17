public class Velocity {
    private double dx;
    private double dy;

    // constructor
    public Velocity(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
    }

    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(angle) * speed;
        double dy = Math.sin(angle) * speed;
        return new Velocity(dx, dy);
    }
    public void setVelocity(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
    }

    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p){
        Point toReturn = new Point(p.getX() +  this.dx, p.getY() +  this.dy);
        return toReturn;
    }

    public double get_dx(){
        return this.dx;
    }

    public double get_dy(){
        return this.dy;
    }
}
