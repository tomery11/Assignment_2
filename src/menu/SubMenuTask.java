package menu;

import animation.Animation;
import animation.AnimationRunner;
import biuoop.KeyboardSensor;
import game.GameFlow;
import levels.LevelInformation;

import java.util.List;

public class SubMenuTask implements Task<Void> {

    //private GameFlow game;
    //private List<LevelInformation> levels;
    private Animation subMenuAnimation;
    private KeyboardSensor keyboardSensors;
    private AnimationRunner runner;


    public SubMenuTask(Animation mySubMenuAnimation, AnimationRunner ar, KeyboardSensor ks){
        //this.game = myGame;
        //this.levels = list;
        this.subMenuAnimation = (SubMenuAnimation) mySubMenuAnimation;
        this.keyboardSensors = ks;
        this.runner = ar;

    }

    @Override
    public Void run() {
        this.runner.run(this.subMenuAnimation);
        return null;
    }
}
