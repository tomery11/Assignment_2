package animation;

import biuoop.DrawSurface;

/**
 * Animation interface.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public interface Animation {

    /**
     * this function runs one frame each time it is called.
     *
     * @param d .
     */
    void doOneFrame(DrawSurface d);

    /**
     * this is a method that tell when to get out of the while loop in the play one turn method .
     *
     * @return boolean.
     */
    boolean shouldStop();
}
