import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;
/**
 * Class MultipleBouncingBallAnimation draws a multiple bouncing ball on the gui surface.
 *
 * @author Tomer Yona
 * @version 1.2 15 Mar 2019
 */
public class MultipleBouncingBallsAnimation {
    /**
     * main function.
     * <p>
     * draws a few bouncing balls on the gui surface.
     * <p>
     *
     * @param args .
     */
    public static void main(String[] args) {

        Sleeper sleeper = new Sleeper();
        Ball[] ballArray = new Ball[args.length];
        //goes over the array of balls and in each cell there is created a new ball and velocity is set
        for (int i = 0; i < args.length; i++) {
            ballArray[i] = new Ball((int) (Math.random() * 200 + 1), (int) (Math.random() * 200 + 1),
                    Integer.parseInt(args[i]), Color.getHSBColor((float) (Math.random() * 256 + 1),
                    (float) (Math.random() * 256 + 1), (float) (Math.random() * 256 + 1)),new GameEnvironment());
            ballArray[i].setVelocity(20 / ballArray[i].getSize(), 20 / ballArray[i].getSize());
        }
        GUI gui = new GUI("MultipleBouncingBallsAnimation", 200, 200);
        //we want to keep updating the
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (int i = 0; i < args.length; i++) {
                ballArray[i].moveOneStep();
                ballArray[i].drawOn(d);
                sleeper.sleepFor(2);  // wait for 50 milliseconds.

            }


            gui.show(d);
        }


    }


}
