import animation.AnimationRunner;
import game.GameFlow;
import game.GameLevel;
import geometry.Point;
import levels.*;

import java.util.ArrayList;
import java.util.List;

public class Ass6Game {


    public static void main(String[] args) {
        List<LevelInformation> levelList = new ArrayList<LevelInformation>();
        //create game.
        DirectHit directHitLevel = new DirectHit();
        levelList.add(directHitLevel);
        WideEasyLevel wideEasyLevel = new WideEasyLevel();
        levelList.add(wideEasyLevel);
        Green3Level green3Level = new Green3Level();
        levelList.add(green3Level);
        FinalFour finalFourLevel = new FinalFour();
        levelList.add(finalFourLevel);

        AnimationRunner myAR = new AnimationRunner();




        GameFlow myGameFlow = new GameFlow(myAR, myAR.getGui().getKeyboardSensor());
        myGameFlow.runLevels(levelList);

    }
}
