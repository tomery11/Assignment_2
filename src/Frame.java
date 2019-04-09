/**
 * Class Frame describes a frame a ball can bounce in.
 *
 * @author Tomer Yona
 * @version 1.2 3 Apr 2019
 */
public class Frame {

    private Point startingPoint;
    private int height;
    private int width;

    /**
     * Frame constructor.
     */
    public Frame() {
        this.startingPoint = new Point(0, 0);
        this.height = 200;
        this.width = 200;
    }

    /**
     * Frame constructor.
     *
     * @param x1     .
     * @param y1     .
     * @param height .
     * @param width  .
     */
    public Frame(int x1, int y1, int height, int width) {
        this.startingPoint = new Point(x1, y1);
        this.height = height;
        this.width = width;
    }

    /**
     * a constructor of a frame.
     *
     * @param p .
     * @param height .
     * @param width .
     */
    public Frame(Point p, int height, int width) {
        this.startingPoint = p;
        this.height = height;
        this.width = width;
    }

    /**
     * getter of starting point.
     *
     * @return int
     */
    public Point getStartingPoint() {
        return this.startingPoint;
    }

    /**
     * getter of height.
     *
     * @return int
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * getter of width.
     *
     * @return int
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * setter of starting point.
     *
     * @param other .
     */
    public void setStartingPoint(Point other) {
        this.startingPoint = other;
    }


}
