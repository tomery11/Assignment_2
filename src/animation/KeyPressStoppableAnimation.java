package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppableAnimation class.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class KeyPressStoppableAnimation implements Animation {

    private boolean stop;
    private KeyboardSensor keyboardSensor;
    private String keyPressed;
    private Animation animation;
    private boolean isAlreadyPressed;

    /**
     * constructor.
     *
     * @param ks          keyboardSensor.
     * @param key         String.
     * @param myAnimation Animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor ks, String key, Animation myAnimation) {
        this.keyboardSensor = ks;
        this.keyPressed = key;
        this.animation = myAnimation;
        this.stop = false;

        this.isAlreadyPressed = true;

    }

    /**
     * this function runs one frame each time it is called.
     *
     * @param d .
     */
    @Override
    public void doOneFrame(DrawSurface d) {

        this.animation.doOneFrame(d);
        if (this.keyboardSensor.isPressed(this.keyPressed) && !this.isAlreadyPressed) {
            this.stop = true;
        }
        if( !(this.keyboardSensor.isPressed(keyPressed))){
            this.isAlreadyPressed = false;
        }

    }

    /**
     * this is a method that tell when to get out of the while loop in the play one turn method .
     *
     * @return boolean.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
