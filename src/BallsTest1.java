
import biuoop.GUI;
import biuoop.DrawSurface;

import java.awt.*;

/**
 * Class BallsTest1 draws a gui 3 random balls on gui surface.
 *
 * @author Tomer Yona
 * @version 1.2 15 Mar 2019
 */

public class BallsTest1 {
    /**
     * main function.
     * <p>
     * draws random balls on the gui surface.
     * <p>
     *
     * @param args .
     */
    public static void main(String[] args) {

        GUI gui = new GUI("Balls Test 1", 400, 400);
        DrawSurface d = gui.getDrawSurface();

        Ball b1 = new Ball(100, 100, 30, java.awt.Color.RED,new GameEnvironment());
        Ball b2 = new Ball(100, 150, 10, java.awt.Color.BLUE, new GameEnvironment());
        Ball b3 = new Ball(80, 249, 50, java.awt.Color.GREEN, new GameEnvironment());
        Block block1 = new Block(new Point(50,50), 100 ,68);
        b1.drawOn(d);
        b2.drawOn(d);
        b3.drawOn(d);
        block1.drawOn(d);

        gui.show(d);
    }
}
