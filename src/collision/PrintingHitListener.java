package collision;

import sprite.Ball;
import sprite.Block;


/**
 * Printing Hit Listener test.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class PrintingHitListener implements HitListener {
    //figure out what needs to be implemented here.


    /**
     * prints each time the block is hit.
     *
     * @param beingHit .
     * @param hitter   .
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block with " + beingHit.getNumOfHits() + " points was hit.");
    }
}
