import java.io.File;
import java.io.IOException;

import animation.AnimationRunner;
import biuoop.KeyboardSensor;
import game.GameFlow;
import game.HighScoresTable;


/**
 * Main method that runs the game.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 * @throws 'IOException'
 */
public class Ass7Game {
    /**
     * main function.
     *
     * @param args string level_set.txt
     * @throws IOException .
     */
    public static void main(String[] args) throws IOException {


        AnimationRunner ar = new AnimationRunner();
        KeyboardSensor ks = ar.getGui().getKeyboardSensor();


        HighScoresTable highScoreTable;
        File file = new File("highscores.txt");
        if (file.exists()) {
            highScoreTable = HighScoresTable.loadFromFile(file);
        } else {
            highScoreTable = new HighScoresTable(5);
        }


        GameFlow gameFlow = new GameFlow(ar, ks, highScoreTable);
        String defaultPath = "level_set_default.txt";
        //String path = args[0];
        if (args.length == 0) {
            gameFlow.startGameFlow(defaultPath);
        } else {
            gameFlow.startGameFlow(args[0]);
        }

    }

}
