package animation;

import biuoop.DrawSurface;




/**
 * PauseScreen class.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class PauseScreen implements Animation {


    private boolean stop;


    /**
     * constructor of PauseScreen.
     */
    public PauseScreen() {
        this.stop = false;
    }

    /**
     * this function runs one frame each time it is called.
     *
     * @param d .
     */
    @Override
    public void doOneFrame(DrawSurface d) {




        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);

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
