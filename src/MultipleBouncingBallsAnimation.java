import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;

public class MultipleBouncingBallsAnimation {

    public static void main(String[] args) {

        Sleeper sleeper = new Sleeper();
        Ball ballArray[] = new Ball[args.length];

        for (int i = 0; i < args.length; i++) {
            ballArray[i] = new Ball((int)(Math. random() * 200 + 1), (int)(Math. random() * 200 + 1),
                    Integer.parseInt(args[i]), java.awt.Color.BLACK);
            ballArray[i].setVelocity(20/ballArray[i].getSize(),20/ballArray[i].getSize());
        }
        GUI gui = new GUI("MultipleBouncingBallsAnimation", 200, 200);

        //MultipleBouncingBallsAnimation test = new MultipleBouncingBallsAnimation();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (int i = 0; i < 6; i++) {
                //ballArray[i].setVelocity(2 ,2);
                ballArray[i].moveOneStep();
                ballArray[i].drawOn(d);
                sleeper.sleepFor(2);  // wait for 50 milliseconds.

            }


            gui.show(d);
        }

        
    }


}
