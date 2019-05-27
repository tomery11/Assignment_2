package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import counter.Counter;

import java.awt.*;

public class WinnerScreen implements Animation {


    private boolean stop;
    private Counter score;


    public WinnerScreen(Counter myScore) {

        this.stop = false;
        this.score = myScore;
    }



    /**
     * this function runs one frame each time it is called.
     *
     * @param d
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.YELLOW);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLUE);
        String toPrint = "You Win! Your score is " + this.score.getValue();
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
