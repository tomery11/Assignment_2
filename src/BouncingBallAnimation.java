import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * Class BouncingBallAnimation draws a bouncing ball on the gui surface.
 *
 * @author Tomer Yona
 * @version 1.2 15 Mar 2019
 */
public class BouncingBallAnimation {
    /**
     * main function.
     * <p>
     * draws a bouncing ball on the gui surface.
     * <p>
     *
     * @param args .
     */
    public static void main(String[] args) {
        GUI gui = new GUI("BouncingBallAnimation", 200, 200);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(0, 0, 30, Color.BLUE);
        ball.setVelocity(2, 2);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

}
