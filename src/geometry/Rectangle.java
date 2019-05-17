package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * this class describes a rectangle.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Point upperRight;
    private Point lowerRight;
    private Point lowerLeft;


    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft1 .
     * @param width1 .
     * @param height1 .
     */
    public Rectangle(Point upperLeft1, double width1, double height1) {
        this.upperLeft = upperLeft1;
        this.width = width1;
        this.height = height1;
        //init rest of the points
        this.upperRight = new Point(upperLeft1.getX() + width1, upperLeft1.getY());
        this.lowerLeft = new Point(upperLeft1.getX(), upperLeft1.getY() + height1);
        this.lowerRight = new Point(upperLeft1.getX() + width1, upperLeft1.getY() + height1);
    }


    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line .
     * @return List
     */
    public java.util.List intersectionPoints(Line line) {
        java.util.List<Line> lineList = new ArrayList<Line>();
        java.util.List<Point> tempIntersecList = new ArrayList<Point>();
        java.util.List<Point> intersecList = new ArrayList<Point>();
        // create all 4 lines of the rectangle and add them to a list of lines
        lineList.add(getUpperLine());
        lineList.add(getLeftSideLine());
        lineList.add(getRightSideLine());
        lineList.add(getLowerLine());
        //iterate over the list of lines of rectangles and if they intersect then we add the
        // intersection point to the list we will return
        for (Line other : lineList) {
            if (other.isIntersecting(line)) {
                tempIntersecList.add(other.intersectionWith(line));
            }
        }
        for (Point i : tempIntersecList) {
            if (i != null) {
                intersecList.add(i);
            }
        }
        return intersecList;
    }


    /**
     * Return the width and height of the rectangle.
     *
     * @return double
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Return the width and height of the rectangle.
     *
     * @return double
     */
    public double getHeight() {
        return this.height;
    }


    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return Point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * this is a getter for the lines of the rectangle.
     *
     * @return Line
     */
    public Line getUpperLine() {
        return new Line(this.upperLeft, new Point(this.upperLeft.getX() + width, this.upperLeft.getY()));
    }

    /**
     * this is a getter for the lines of the rectangle.
     *
     * @return Line
     */
    public Line getLowerLine() {
        Point p1 = new Point(this.upperLeft.getX(), this.upperLeft.getY() + height);
        Point p2 = new Point(this.upperLeft.getX() + width, this.upperLeft.getY() + height);
        return new Line(p1, p2);
    }

    /**
     * this is a getter for the lines of the rectangle.
     *
     * @return Line
     */
    public Line getLeftSideLine() {
        Point p2 = new Point(this.upperLeft.getX(), this.upperLeft.getY() + height);
        return new Line(this.upperLeft, p2);
    }

    /**
     * this is a getter for the lines of the rectangle.
     *
     * @return Line
     */
    public Line getRightSideLine() {
        Point p1 = new Point(this.upperLeft.getX() + width, this.upperLeft.getY());
        Point p2 = new Point(this.upperLeft.getX() + width, this.upperLeft.getY() + height);
        return new Line(p1, p2);
    }

    /**
     * Returns the point of the rectangle.
     *
     * @return Point
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * Returns the point of the rectangle.
     *
     * @return Point
     */
    public Point getLowerRight() {
        return this.lowerRight;
    }

    /**
     * Returns the point of the rectangle.
     *
     * @return Point
     */
    public Point getLowerLeft() {
        return this.lowerLeft;
    }

    /**
     * sets the upperLeft point of the rectangle.
     * @param point .
     */
    public void setUpperLeft(Point point) {
        this.upperRight = point;
    }

    /**
     * updates the location of the rectangle use for the paddle when it is moved.
     * @param update .
     */
    public void updateLocation(double update) {
        this.upperLeft = new Point(this.getUpperLeft().getX() + update, this.getUpperLeft().getY());
        this.lowerRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        this.lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
    }


}
