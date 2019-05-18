package collision;

import counter.Counter;
import game.Game;
import sprite.Ball;
import sprite.Block;


/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class BlockRemover implements HitListener {

    private Game game;
    private Counter remainingBlocks;


    /**
     * BlockRemover Constructor.
     *
     * @param currGame .
     */
    public BlockRemover(Game currGame) {
        this.game = currGame;
    }


    /**
     * BlockRemover constructor.
     *
     * @param currGame      .
     * @param removedBlocks .
     */
    public BlockRemover(Game currGame, Counter removedBlocks) {
        this.game = currGame;
        this.remainingBlocks = removedBlocks;
    }


    @Override
    /**
     * Blocks that are hit and reach 0 hit-points should be removed
     * from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getNumOfHits() == 0) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(game);
            this.remainingBlocks.decrease(1);
        }
    }


}
