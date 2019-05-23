package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

/**
 * AnimationRunner class, implements Animation interface
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;


    /**
     * constructor of AnimationRunner.
     */
    public AnimationRunner() {
        this.gui = new GUI("araknoid", 800, 600);
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();

    }

    public GUI getGui() {
        return this.gui;
    }

    /**
     * Run method runs the game basically.
     *
     * @param animation .
     */
    public void run(Animation animation) {

        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}


