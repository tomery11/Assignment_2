package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class KeyPressStoppableAnimation implements Animation{

    private boolean stop;
    private KeyboardSensor keyboardSensor;
    String keyPressed;
    Animation animation;

    public KeyPressStoppableAnimation(KeyboardSensor ks, String key,Animation myAnimation) {
        this.keyboardSensor = ks;
        this.keyPressed = key;
        this.animation = myAnimation;
        this.stop = false;
    }
    /**
     * this function runs one frame each time it is called.
     *
     * @param d
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.keyboardSensor.isPressed(keyPressed)) {

            this.stop = true;


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
