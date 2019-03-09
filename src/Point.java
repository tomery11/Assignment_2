
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
     * @param double x,y  description
     * @return int[]
     */
    public Point( double x, double y){
        this.x = x;
        this.y = y;
    }
    /**
     * stringsToInts converts an array of strings to an array of ints.
     * @param numbers  description
     * @return int[]
     */
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
    /**
     * stringsToInts converts an array of strings to an array of ints.
     * @param numbers  description
     * @return int[]
     */
    public boolean equals (Point other){
        boolean ans = false;
        if(this.x == other.x && this.y == other.y){
            ans = true;
        }
        return ans;
    }

    /**
     * stringsToInts converts an array of strings to an array of ints.
     * @param numbers  description
     * @return int[]
     */
    public double getX(){
        return this.x;
    }
    /**
     * stringsToInts converts an array of strings to an array of ints.
     * @param numbers  description
     * @return int[]
     */
    public double getY(){
        return this.y;
    }

}
