import animation.AnimationRunner;
import game.GameFlow;

import game.HighScoresTable;
import levels.LevelInformation;

import levels.FinalFour;
import levels.Green3Level;
import levels.WideEasyLevel;
import levels.DirectHit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Ass6 main.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class Ass6Game {

    /**
     * main function.
     *
     * @param args .
     */
    public static void main(String[] args) throws IOException {
        List<LevelInformation> levelList = new ArrayList<LevelInformation>();
        int length = args.length;
        for (int i = 0; i < length; i++) {
            if (args[i].charAt(0) >= '0' && args[i].charAt(0) <= '9') {
                int level = Integer.parseInt(args[i]);
                addToLevelList(levelList, level);
            }

        }
        if (length == 0) {
            for (int i = 1; i < 5; i++) {

                addToLevelList(levelList, i);
            }
        }
        AnimationRunner myAR = new AnimationRunner();
        /*
        File highScoreFile = new File("highscores.txt");
        HighScoresTable scoresTable = new HighScoresTable(5);
        scoresTable.save(highScoreFile);
        HighScoresTable highScoreTable = HighScoresTable.loadFromFile(highScoreFile);
        */
        HighScoresTable scoresTable;
        File file = new File("highscores.txt");
        if(file.exists()) {
            scoresTable = HighScoresTable.loadFromFile(file);
        } else {
            scoresTable = new HighScoresTable(5);
        }
        GameFlow myGameFlow = new GameFlow(myAR, myAR.getGui().getKeyboardSensor(),scoresTable);
        myGameFlow.runLevels(levelList);


        //create game.
        /*
        DirectHit directHitLevel = new DirectHit();
        levelList.add(directHitLevel);

        WideEasyLevel wideEasyLevel = new WideEasyLevel();
        levelList.add(wideEasyLevel);
        Green3Level green3Level = new Green3Level();
        levelList.add(green3Level);
        FinalFour finalFourLevel = new FinalFour();
        levelList.add(finalFourLevel);

       */

    }

    /**
     * converts level number and adds it to the levelList.
     *
     * @param levelList .
     * @param level     .
     */
    private static void addToLevelList(List<LevelInformation> levelList, int level) {
        switch (level) {

            case 1:
                DirectHit directHitLevel = new DirectHit();
                levelList.add(directHitLevel);
                break;
            case 2:
                WideEasyLevel wideEasyLevel = new WideEasyLevel();
                levelList.add(wideEasyLevel);
                break;
            case 3:
                Green3Level green3Level = new Green3Level();
                levelList.add(green3Level);
                break;
            case 4:
                FinalFour finalFourLevel1 = new FinalFour();
                levelList.add(finalFourLevel1);
                break;
            default:
                FinalFour finalFourLevel2 = new FinalFour();
                levelList.add(finalFourLevel2);
                break;
        }
    }
}
