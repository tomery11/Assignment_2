package collision;

import sprite.Ball;
import sprite.Block;


/**
 * HitListener Interface.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public interface HitListener {


    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit .
     * @param hitter   .
     */
    void hitEvent(Block beingHit, Ball hitter);


}
