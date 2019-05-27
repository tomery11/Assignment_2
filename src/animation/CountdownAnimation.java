package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprite.SpriteCollection;

import java.awt.Color;

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
    private long time;


    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen) {
        this.seconds = numOfSeconds;
        this.startCount = countFrom;
        this.gameSprites = gameScreen;
        this.sleeper = new Sleeper();
        this.time = (long) this.seconds / this.startCount;
    }


    /**
     * this function runs one frame each time it is called.
     *
     * @param d
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        gameSprites.drawAllOn(d);
        d.setColor(Color.RED);

        if (this.startCount > 0) {
            d.drawText(400, 400, Integer.toString(startCount), 40);
        }
        this.startCount--;
        //this.decreaseCount();
        if (this.startCount < 2 && this.startCount > -1) {
            this.sleeper.sleepFor(this.time);
        }
        if (this.startCount <= -1) {
            d.drawText(300, 400, "Go!", 90);
            this.sleeper.sleepFor(this.time);

        }

    }

    /**
     * this is a method that tell when to get out of the while loop in the play one turn method .
     *
     * @return boolean.
     */
    @Override
    public boolean shouldStop() {
        return (this.startCount == -3);
    }


}
