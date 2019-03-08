public class Point {
    private double x;
    private double y;

    public Point( double x, double y){
        this.x = x;
        this.y = y;
    }

    public double distance(Point other){
        double curr_x = this.x;
        double curr_y= this.y;
        double other_x = other.x;
        double other_y = other.y;
        double before_sqrt = Math.pow(Math.abs(curr_x-other_x),2) +
                Math.pow(Math.abs(curr_y-other_y),2);
        double distance = Math.sqrt(before_sqrt);
        return distance;
    }

    public boolean equals (Point other){
        boolean ans = false;
        if(this.x == other.x && this.y == other.y){
            ans = true;
        }
        return ans;
    }


    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

}
