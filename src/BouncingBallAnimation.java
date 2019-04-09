import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;
import java.util.ArrayList;

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
        GUI gui = new GUI("BouncingBallAnimation", 600, 600);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(0, 0, 30, Color.BLUE,new GameEnvironment());
        ball.setVelocity(2, 2);

        Sprite block1 = new Block(new Point(70,100),200, 200);


        Block blockUp = new Block(new Point(1,1),200,1);
        Block blockDown = new Block(new Point(1,200),200,1);
        Block blockLeft = new Block(new Point(1,1),1,200);
        Block blockRight = new Block(new Point(200,1),1,200);



        while (true) {
            DrawSurface d = gui.getDrawSurface();
            block1.drawOn(d);
            ball.moveOneStep();

            //DrawSurface d = gui.getDrawSurface();
            //block1.drawOn(d);
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

}
