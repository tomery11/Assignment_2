package collision;

import counter.Counter;
import game.Game;
import sprite.Ball;
import sprite.Block;

/**
 * a BallRemover is in charge of removing balls from the game, as well as keeping count
 * of the number of balls that remain.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter ballsLeft;

    /**
     * constructor of BallRemover.
     * @param myGame .
     */
    public BallRemover(Game myGame) {
        this.game = myGame;
        this.ballsLeft = null;
    }

    /**
     * Constructor of BallRemover.
     * @param myGame .
     * @param ballsRemaining .
     */
    public BallRemover(Game myGame, Counter ballsRemaining) {
        this.game = myGame;
        this.ballsLeft = ballsRemaining;
    }



    @Override
    /**
     * hit event
     */
    public void hitEvent(Block beingHit, Ball hitter) {

        if (this.ballsLeft == null) {
            hitter.removeFromGame(game);
        } else {
            hitter.removeFromGame(game);
            this.ballsLeft.decrease(1);
        }
    }


}
