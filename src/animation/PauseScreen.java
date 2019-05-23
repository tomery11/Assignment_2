package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

/**
 * PauseScreen class.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class PauseScreen implements Animation {

    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * constructor of PauseScreen.
     *
     * @param k
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * this function runs one frame each time it is called.
     *
     * @param d
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        //d.setColor(Color.cyan);
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
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
