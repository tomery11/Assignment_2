package sprite;

import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import collision.Collidable;
import collision.Velocity;
import collision.HitListener;
import collision.HitNotifier;
import biuoop.DrawSurface;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * this class describes a block it implements the Collidable and Sprite classes.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle rectangle;
    private Color color;
    private String hits;
    private int numOfHits;
    private List<HitListener> hitListeners;
    private List<Color> colorList;
    private List<Image> imageList;
    private Color stroke;
    private int colorCount;

    /**
     * constructor.
     *
     * @param block .
     */
    public Block(Rectangle block) {
        this.rectangle = block;
        this.color = Color.black;
        hits = "2";
        this.hitListeners = new ArrayList<HitListener>();
        this.numOfHits = 2;
        this.colorCount = 0;
    }

    /**
     * constructor.
     *
     * @param p .
     * @param w .
     * @param h .
     */
    public Block(Point p, int w, int h) {
        this.rectangle = new Rectangle(p, w, h);
        this.color = Color.black;
        hits = "2";
        this.hitListeners = new ArrayList<HitListener>();
        this.numOfHits = 2;
        this.colorCount = 0;
    }

    /**
     * constructor.
     *
     * @param rec          .
     * @param c            .
     * @param numberOfHits .
     */
    public Block(Rectangle rec, Color c, int numberOfHits) {
        this.rectangle = rec;
        this.color = c;
        hits = Integer.toString(numberOfHits);
        this.hitListeners = new ArrayList<HitListener>();
        this.numOfHits = numberOfHits;
        this.colorCount = 0;
    }

    /**
     * Constructor.
     *
     * @param rec          .
     * @param numberOfHits .
     * @param listOfColor  .
     * @param listOfImages .
     * @param strokeColor  .
     */
    public Block(Rectangle rec, int numberOfHits, List<Color> listOfColor, List<Image> listOfImages,
                 Color strokeColor) {
        this.rectangle = rec;
        this.numOfHits = numberOfHits;
        this.colorList = listOfColor;
        this.imageList = listOfImages;
        this.stroke = strokeColor;
        this.colorCount = 0;
        this.hitListeners = new ArrayList<HitListener>();
    }


    /**
     * constructor for death region .
     *
     * @param rec   .
     * @param c     .
     * @param hits1 .
     */
    public Block(Rectangle rec, Color c, String hits1) {
        this.rectangle = rec;
        this.color = c;
        this.hits = hits1;
        this.hitListeners = new ArrayList<HitListener>();
        this.numOfHits = 2;
    }


    @Override
    /**
     *Return the "collision shape" of the object.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }


    @Override
    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @param Point .
     * @param Velocity .
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double cX = collisionPoint.getX();
        double cY = collisionPoint.getY();
        setNumOfHits((this.numOfHits) - 1);
        this.notifyHit(hitter);
        //setHits(this.getHits());
        changeColor();
        if ((Math.round(cX) == Math.round(this.rectangle.getLowerLeft().getX())
                && Math.round(cY) == Math.round(this.rectangle.getLowerLeft().getY()))
                || (Math.round(cX) == Math.round(this.rectangle.getLowerRight().getX())
                && Math.round(cY) == Math.round(this.rectangle.getLowerRight().getY()))
                || (Math.round(cX) == this.rectangle.getUpperLeft().getX()
                && Math.round(cY) == Math.round(this.rectangle.getUpperLeft().getY()))
                || ((Math.round(cX) == Math.round(this.rectangle.getUpperRight().getX()))
                && Math.round(cY) == Math.round(this.rectangle.getUpperRight().getY()))) {

            return new Velocity(-dx, -dy);

        } else if (this.rectangle.getUpperLine().onLine(collisionPoint)
                || this.rectangle.getLowerLine().onLine(collisionPoint)) {

            return new Velocity(dx, -dy);
            //if ball collides with corners of block
        } else if (this.rectangle.getLeftSideLine().onLine(collisionPoint)
                || this.rectangle.getRightSideLine().onLine(collisionPoint)) {
            return new Velocity(-dx, dy);
        } else {
            return new Velocity(dx, dy);
        }

    }

    /**
     * setter for the number of hits, decreases 1 of the current num of hits unless is already 0/X.
     *
     * @param currentNumofHits .
     */
    private void setHits(String currentNumofHits) {
        if (currentNumofHits.equals("X") || currentNumofHits.equals("1")) {
            this.hits = "X";
        } else {
            this.hits = "1";
        }

    }

    /**
     * setter.
     *
     * @param num .
     */
    public void setNumOfHits(int num) {
        this.numOfHits = num;
    }

    /**
     * Draws the block, frame and number of hits of the block/rectangle.
     *
     * @param surface .
     */
    public void drawOn(DrawSurface surface) {
        if (this.colorList != null) {
            surface.setColor(this.colorList.get(colorCount));
            surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                    (int) this.rectangle.getUpperLeft().getY(),
                    (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        } else if (this.imageList != null) {
            surface.drawImage((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                    this.imageList.get(colorCount));
        } else {
            surface.setColor(this.color);
            surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                    (int) this.rectangle.getUpperLeft().getY(),
                    (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        }


        if (stroke != null) {
            surface.setColor(this.stroke);
        } else {
            surface.setColor(Color.black);
        }

        surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        surface.setColor(Color.WHITE);

    }

    @Override
    /**
     * currently this function isn't used
     */
    public void timePassed() {

    }

    /**
     * return the number in String value of current hits.
     *
     * @return String
     */
    public String getHits() {
        return this.hits;
    }

    /**
     * setter for color.
     *
     * @param c .
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * removes the block from a certain game.
     *
     * @param game .
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);

    }

    /**
     * hit notifier.
     *
     * @param hitter .
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).hitEvent(this, hitter);
        }

    }

    /**
     * getter.
     *
     * @return int .
     */
    public int getNumOfHits() {
        return this.numOfHits;
    }

    /**
     * getter.
     *
     * @return int/
     */
    public int getWidth() {
        return (int) this.rectangle.getWidth();
    }

    /**
     * getter.
     *
     * @return int.
     */
    public int getHeight() {
        return (int) this.rectangle.getHeight();
    }

    /**
     * updates color by updating counter of color count list.
     */
    public void changeColor() {
        if (this.imageList != null) {
            if (this.colorCount + 1 < imageList.size()) {
                this.colorCount++;
            }
        } else if (colorList != null) {
            if (this.colorCount + 1 < colorList.size()) {
                this.colorCount++;
            }

        }
    }
}
