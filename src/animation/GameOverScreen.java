package animation;

import biuoop.DrawSurface;

import counter.Counter;

import java.awt.Color;
/**
 * Game over animation.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class GameOverScreen implements Animation {


    private boolean stop;
    private Counter score;

    /**
     * Constructor.
     * @param myScore .
     */
    public GameOverScreen(Counter myScore) {

        this.stop = false;
        this.score = myScore;
    }


    /**
     * this function runs one frame each time it is called.
     *
     * @param d .
     */
    @Override
    public void doOneFrame(DrawSurface d) {


        d.setColor(Color.black);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.RED);
        String toPrint = "Game Over. Your score is " + this.score.getValue();
        d.setColor(Color.RED);
        d.drawText(180, 400, toPrint, 30);

    }

    /**
     * this is a method that tell when to get out of the while loop in the play one turn method .
     *
     * @return boolean.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
