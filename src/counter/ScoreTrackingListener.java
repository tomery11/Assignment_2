package counter;

import collision.HitListener;
import sprite.Ball;
import sprite.Block;

public class ScoreTrackingListener implements HitListener {
        private Counter currentScore;

    /**
     * Constructor
     * @param scoreCounter .
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {

        if (beingHit.getNumOfHits() > 0){
            this.currentScore.increase(5);
        }
        if (beingHit.getNumOfHits() == 0){
            this.currentScore.increase(10);
        }
    }


}
