package menu;

import animation.Animation;
import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;

public class ShowHiScoresTask implements Task<Void> {

    private AnimationRunner runner;
    private Animation highScoresAnimation;
    private KeyboardSensor ks;
    /**
     * constructor.
     * @param ar AnimationRunner
     * @param highScoresAnimation Animation
     * @param ks KeyboardSensor
     */
    public ShowHiScoresTask(AnimationRunner ar, Animation highScoresAnimation, KeyboardSensor ks) {
        this.runner = ar;
        this.highScoresAnimation = (HighScoresAnimation) highScoresAnimation;
        this.ks = ks;
    }
    /**
     * run the animation.
     * @return nothing
     */
    public Void run() {
        this.runner.run(new KeyPressStoppableAnimation(ks, KeyboardSensor.SPACE_KEY, this.highScoresAnimation));
        //System.out.println("out show");
        return null;
    }

}
