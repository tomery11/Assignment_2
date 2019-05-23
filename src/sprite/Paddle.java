
package sprite;
import game.GameLevel;
import geometry.Line;
import geometry.Rectangle;
import geometry.Point;
import collision.Collidable;
import collision.HitListener;
import collision.Velocity;
import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A Paddle class.
 * describes a Paddle.
 *
 * @author Tomer Yona
 */

public class Paddle implements Sprite, Collidable {

    private biuoop.GUI gui;
    private Rectangle rectangle;
    private Color color;
    private static final int REGION1 = 0;
    private static final int REGION2 = 1;
    private static final int REGION3 = 2;
    private static final int REGION4 = 3;
    private static final int REGION5 = 4;
    private List<HitListener> hitListeners;

    /**
     * constructor.
     * @param rec .
     * @param gui1 .
     */
    public Paddle(Rectangle rec, GUI gui1) {
        this.rectangle = rec;
        this.color = Color.orange;
        this.gui = gui1;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * moves the paddle left.
     */
    public void moveLeft() {
        // set a new location of the paddle when it's x value is -10 than it was.
        if (this.rectangle.getUpperLeft().getX() - 10 >= 30) {
            this.rectangle.updateLocation(-10);
        }
    }

    /**
     * moves the paddle right.
     */
    public void moveRight() {
        DrawSurface d = this.gui.getDrawSurface();
        double width = d.getWidth() - 30;
        if (this.rectangle.getUpperRight().getX() + 10 <= width) {
            this.rectangle.updateLocation(10);
        }

    }

    /**
     * given a game adds the paddle to the game by adding to the list of collidables and sprites.
     * @param g .
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    @Override
    /**
     * returns the rectangle we collide with.
     * @return Rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param hitter .
     * @param collisionPoint  .
     * @param currentVelocity .
     * @return Velocity
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // the next line creates the top line of the paddle.
        Line upperLine = this.rectangle.getUpperLine();
        Line leftLine = this.rectangle.getLeftSideLine();
        Line rightLine = this.rectangle.getRightSideLine();
        // calling a function to calculate the collision spot one the paddle
        int colPoint = findColisionPoint(upperLine, collisionPoint);
        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        // for each collision region create a proper velocity as required.
        if (upperLine.onLine(collisionPoint)) {
            Velocity v = regionToVelocity(colPoint, speed, currentVelocity);
            return v;
        } else if ((leftLine.onLine(collisionPoint)) || (rightLine.onLine(collisionPoint))) {
            return new Velocity((-1) * currentVelocity.getDx(), currentVelocity.getDy());
        }
        return currentVelocity;
    }


    /**
     * add the list to the current list.
     * @param blr .
     */
    public void addHitListener(HitListener blr) {
        this.hitListeners.add(blr);
    }


    /**
     * in this function we compute where we hit(Region) the paddle.
     * @param paddleLine .
     * @param collisionPoint .
     * @return int .
     */
    private int findColisionPoint(Line paddleLine, Point collisionPoint) {
        double lineStartOfX = paddleLine.start().getX();
        double lineDistance = paddleLine.start().distance(paddleLine.end());
        double lengthOfRegions = lineDistance / 5;
        double colPointX = collisionPoint.getX();
        // loop over the region
        for (int i = 0; i < 5; i++) {
            // we find which region the location belongs to.
            if ((colPointX >= lineStartOfX + lengthOfRegions * i)
                    && (colPointX <= lineStartOfX + lengthOfRegions * (i + 1))) {

                return i;
            }
        }
        return 2;
    }

    /**
     * return the updated Velocity according to the region the ball hit.
     * @param colPoint .
     * @param speed .
     * @param currVelocity .
     * @return Velocity
     */
    private Velocity regionToVelocity(int colPoint, double speed, Velocity currVelocity) {
        switch (colPoint) {
            case REGION1:
                return Velocity.fromAngleAndSpeed(300, speed);
            case REGION2:
                Velocity v = Velocity.fromAngleAndSpeed(330, speed);
                return v;
            case REGION3:
                return new Velocity(currVelocity.getDx(), (-1) * currVelocity.getDy());
            case REGION4:
                Velocity v1 = Velocity.fromAngleAndSpeed(30, speed);
                return v1;
            case REGION5:
                return Velocity.fromAngleAndSpeed(60, speed);
            default:
                return new Velocity(currVelocity.getDx(), currVelocity.getDy());
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        // draw the block it self using the color received by input (or the default)
        d.setColor(this.color);
        // draw the paddle
        d.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    @Override
    public void timePassed() {
        //when right key is pressed.
        biuoop.KeyboardSensor keyboard = this.gui.getKeyboardSensor();
        if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            moveRight();
            //when left key is pressed.
        } else if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            moveLeft();
        } else {
            return;
        }
    }
}
