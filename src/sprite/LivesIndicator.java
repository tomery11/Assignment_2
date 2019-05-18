package sprite;

import biuoop.DrawSurface;
import collision.HitListener;
import counter.Counter;
import geometry.Rectangle;

import java.awt.Color;
import java.util.List;

/**
 * a sprite that represents a life indicator bar .
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class LivesIndicator implements Sprite {
    private Counter livesCounter;
    private Rectangle rec;
    private List<HitListener> hitListeners;

    /**
     * Constructor of Life indicator.
     *
     * @param frameRectangle .
     * @param k              .
     */
    public LivesIndicator(Rectangle frameRectangle, int k) {
        this.rec = frameRectangle;
        this.livesCounter = new Counter(k);
        //this.hitListeners = new ArrayList<HitListener>();

    }


    /**
     * draw the sprite to the screen.
     *
     * @param d .
     */
    @Override
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
                "Lives: " + this.livesCounter.getValue(), 12);


    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * getter for rectangle.
     *
     * @return Rectangle .
     */
    public Rectangle getRec() {
        return rec;
    }

    /**
     * getter of counter.
     *
     * @return Counter.
     */
    public Counter getLivesCounter() {
        return livesCounter;
    }
}
