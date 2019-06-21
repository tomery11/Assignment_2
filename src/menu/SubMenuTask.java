package menu;

import animation.Animation;
import animation.AnimationRunner;
import biuoop.KeyboardSensor;

/**
 * Sub menu Task.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class SubMenuTask implements Task<Void> {

    private Animation subMenuAnimation;
    private KeyboardSensor keyboardSensors;
    private AnimationRunner runner;

    /**
     * Constructor.
     *
     * @param mySubMenuAnimation .
     * @param ar                 .
     * @param ks                 .
     */
    public SubMenuTask(Animation mySubMenuAnimation, AnimationRunner ar, KeyboardSensor ks) {

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
