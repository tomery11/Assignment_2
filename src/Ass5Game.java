

import game.GameLevel;
import levels.ClassicLevel;
import levels.LevelInformation;

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
        GameLevel game = new GameLevel(classicLevel);
        //init game.
        game.initialize();
        //run game
        game.run();
    }
}
