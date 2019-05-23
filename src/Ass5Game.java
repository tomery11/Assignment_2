

import game.GameLevel;
import levels.*;

/**
 * runs a game as a final test for the assignment.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class Ass5Game {
    /**
     * main function.
     *
     * @param args .
     */
    public static void main(String[] args) {
        //create game.
        ClassicLevel classicLevel = new ClassicLevel();
        DirectHit directHitLevel = new DirectHit();
        WideEasyLevel wideEasyLevel = new WideEasyLevel();
        Green3Level green3Level = new Green3Level();
        FinalFour finalFourLevel = new FinalFour();

        GameLevel game = new GameLevel(finalFourLevel);
        //init game.
        game.initialize();
        //run game
        game.run();
    }
}
