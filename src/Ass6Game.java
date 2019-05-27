import animation.AnimationRunner;
import game.GameFlow;
import game.GameLevel;
import geometry.Point;
import levels.*;

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
    public static void main(String[] args) {
        List<LevelInformation> levelList = new ArrayList<LevelInformation>();
        int length = args.length;
        for (int i = 0; i < length; i++) {
            if (args[i].charAt(0) >= '0' && args[i].charAt(0) <= '9') {
                int level = Integer.parseInt(args[i]);
                addToLevelList(levelList, level);
            }

        }
        AnimationRunner myAR = new AnimationRunner();
        GameFlow myGameFlow = new GameFlow(myAR, myAR.getGui().getKeyboardSensor());
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
