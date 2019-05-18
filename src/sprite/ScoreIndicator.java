package sprite;

import biuoop.DrawSurface;
import collision.HitListener;
import counter.Counter;
import geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * a sprite that represents a score indicator bar .
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;
    private Rectangle rec;
    private List<HitListener> hitListeners;

    /**
     * constructor.
     *
     * @param frameRectangle .
     */
    public ScoreIndicator(Rectangle frameRectangle) {
        this.rec = frameRectangle;
        this.scoreCounter = new Counter(0);
        this.hitListeners = new ArrayList<HitListener>();

    }

    /**
     * draws the sprite on the surface.
     *
     * @param d .
     */
    public void drawOn(DrawSurface d) {
        // draw a frame in color white.


        d.drawRectangle((int) getRec().getUpperLeft().getX(),
                (int) getRec().getUpperLeft().getY(),
                (int) getRec().getWidth(), (int) getRec().getHeight());

        d.setColor(Color.white);
        d.fillRectangle((int) getRec().getUpperLeft().getX(),
                (int) getRec().getUpperLeft().getY(), (int) getRec().getWidth(),
                (int) getRec().getHeight());
        d.setColor(Color.BLACK);
        //placement of text in correct location
        d.drawText((int) getRec().getUpperLeft().getX()
                        + (int) (0.5 * getRec().getWidth()) - 20,
                (int) getRec().getUpperLeft().getY()
                        + (int) (0.5 * getRec().getHeight()) + 5,
                "Score: " + this.scoreCounter.getValue(), 12);


    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * getter for Rectangle.
     *
     * @return Rectangle .
     */
    public Rectangle getRec() {
        return rec;
    }


    /**
     * getter for Score counter.
     *
     * @return Counter.
     */
    public Counter getScoreCounter() {
        return scoreCounter;
    }
}
