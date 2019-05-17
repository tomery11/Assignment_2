
package sprite;

import biuoop.DrawSurface;
/**
 * interface that describes a Sprite object.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public interface Sprite {


    /**
     * draw the sprite to the screen.
     * @param d .
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

}
