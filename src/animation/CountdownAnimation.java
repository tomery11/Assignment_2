package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprite.SpriteCollection;

import java.awt.*;

/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class CountdownAnimation implements Animation {
    private double seconds;
    private int startCount;
    private SpriteCollection gameSprites;
    private Sleeper sleeper;


    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen) {
        this.seconds = numOfSeconds;
        this.startCount = countFrom;
        this.gameSprites = gameScreen;
        this.sleeper = new Sleeper();
    }


    /**
     * this function runs one frame each time it is called.
     *
     * @param d
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(0, 0, 153));
        d.fillRectangle(0, 0, 800, 600);
        this.gameSprites.drawAllOn(d);


        d.setColor(Color.RED);
        d.drawText(400, 400, Integer.toString(startCount), 40);

        this.decreaseCount();
        if (this.startCount < 3) {
            this.sleeper.sleepFor(2000);
        }
        if (0 == this.startCount) {
            d.setColor(new Color(0, 0, 153));
            d.fillRectangle(0, 0, 800, 600);
            this.gameSprites.drawAllOn(d);
            d.setColor(Color.RED);
            this.sleeper.sleepFor(2000);
            d.drawText(300, 400, "Go!", 90);


        }

    }

    /**
     * this is a method that tell when to get out of the while loop in the play one turn method .
     *
     * @return boolean.
     */
    @Override
    public boolean shouldStop() {
        return (this.startCount == 0);
    }

    public void setSeconds(double seconds) {
        this.seconds = seconds;
    }


    public void setStartCount(int startCount) {
        this.startCount = startCount;
    }

    public void decreaseCount() {
        if (this.startCount == 0) {
            setStartCount(3);
        } else {
            this.startCount--;
        }
    }
}
