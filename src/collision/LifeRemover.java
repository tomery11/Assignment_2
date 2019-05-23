package collision;

import counter.Counter;
import game.GameLevel;
import sprite.Ball;
import sprite.Block;

/**
 * a LifeRemover is in charge of removing lives from the game, as well as keeping count
 * of the number of lives that remain.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class LifeRemover implements HitListener {
    private GameLevel game;
    private Counter livesLeft;

    /**
     * LifeRemover Constructor.
     *
     * @param myGame .
     */
    public LifeRemover(GameLevel myGame) {
        this.game = myGame;
        this.livesLeft = null;
    }

    /**
     * LifeRemover Constructor.
     *
     * @param myGame        .
     * @param lifeRemaining .
     */
    public LifeRemover(GameLevel myGame, Counter lifeRemaining) {
        this.game = myGame;
        this.livesLeft = lifeRemaining;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (this.livesLeft == null) {
            hitter.removeFromGame(game);
        } else {
            hitter.removeFromGame(game);
            this.livesLeft.decrease(1);
        }
    }
}
