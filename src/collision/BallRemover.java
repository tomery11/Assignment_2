package collision;

import counter.Counter;
import game.Game;
import sprite.Ball;
import sprite.Block;

public class BallRemover implements HitListener {
    private Game game;
    private Counter ballsLeft;


    public BallRemover(Game myGame){
        this.game = myGame;
        this.ballsLeft = null;
    }

    public BallRemover(Game myGame, Counter ballsRemaining){
        this.game = myGame;
        this.ballsLeft = ballsRemaining;
    }



    @Override
    public void hitEvent(Block beingHit, Ball hitter) {

        if (this.ballsLeft == null) {
            hitter.removeFromGame(game);
        } else {
            hitter.removeFromGame(game);
            this.ballsLeft.decrease(1);
        }
    }


}
