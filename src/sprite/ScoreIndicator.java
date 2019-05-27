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
    private Counter livesCounter;
    private List<HitListener> hitListeners;
    private String level;

    /**
     * constructor.
     *
     * @param frameRectangle .
     */
    public ScoreIndicator(Rectangle frameRectangle, Counter lives, String levelName) {
        this.rec = frameRectangle;
        this.scoreCounter = new Counter(0);
        this.hitListeners = new ArrayList<HitListener>();
        this.livesCounter = lives;
        this.level = levelName;

    }


    public ScoreIndicator(Rectangle frameRectangle, Counter lives) {
        this.rec = frameRectangle;
        this.scoreCounter = new Counter(0);
        this.hitListeners = new ArrayList<HitListener>();
        this.livesCounter = lives;

    }



    public ScoreIndicator(Rectangle frameRectangle, int lives) {
        this.rec = frameRectangle;
        this.scoreCounter = new Counter(0);
        this.hitListeners = new ArrayList<HitListener>();
        this.livesCounter = new Counter(lives);


    }

    /**
     * set level.
     * @param level .
     */
    public void setLevel(String level) {
        this.level = level;
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
        d.drawText((int) getRec().getUpperLeft().getX()
                        + (int) (0.5 * getRec().getWidth()) - 160,
                (int) getRec().getUpperLeft().getY()
                        + (int) (0.5 * getRec().getHeight()) + 5,
                "Lives: " + this.livesCounter.getValue(), 12);
        d.drawText((int) getRec().getUpperLeft().getX()
                        + (int) (0.5 * getRec().getWidth()) + 120,
                (int) getRec().getUpperLeft().getY()
                        + (int) (0.5 * getRec().getHeight()) + 5,
                "Level: " + this.getLevel(), 12);


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

    /**
     * getter.
     * @return Counter .
     */
    public Counter getLivesCounter() {
        return livesCounter;
    }

    /**
     * getter.
     * @return String.
     */
    public String getLevel() {
        return level;
    }
}
