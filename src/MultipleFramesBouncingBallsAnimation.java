import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;


import java.awt.Color;

/**
 * Class MultipleFramesBouncingBallsAnimation sorts n numbers in ascending and descending ways.
 *
 * @author Tomer Yona
 * @version 1.2 01 Mar 2019
 */
public class MultipleFramesBouncingBallsAnimation {
    /**
     * main function.
     * <p>
     * draws a few bouncing balls in two differnet frames one big gray frame
     * and one small yellow frame on the gui surface.
     * <p>
     *
     * @param args .
     */
    public static void main(String[] args) {
        GUI gui = new GUI("MultipleFramesBouncingBallsAnimation", 600, 600);
        Sleeper sleeper = new Sleeper();
        //init of two arrays of sizes of balls according to the given arguments
        int[] arrSize1 = new int[args.length / 2];
        int[] arrSize2 = new int[args.length / 2];
        for (int j = 0; j < args.length; j++) {
            if (j < args.length / 2) {
                arrSize1[j] = Integer.parseInt(args[j]);
            } else {
                arrSize2[j - arrSize2.length] = Integer.parseInt(args[j]);
            }
        }
        //init of two Ball arrays
        Ball[] ballArray1 = new Ball[arrSize1.length];
        Ball[] ballArray2 = new Ball[arrSize2.length];
        Frame frame1 = new Frame(50, 50, 450, 450);
        Frame frame2 = new Frame(450, 450, 150, 150);
        //go over half of the balls in the argument array
        for (int i = 0; i < args.length / 2; i++) {
            int x1 = (int) (Math.random() * (frame1.getWidth() - 2 * arrSize1[i])
                    + frame1.getStartingPoint().getX() + arrSize1[i]);
            int y1 = (int) (Math.random() * (frame1.getHeight() - 2 * arrSize1[i])
                    + frame1.getStartingPoint().getY() + arrSize1[i]);
            ballArray1[i] = new Ball(x1, y1, Integer.parseInt(args[i]),
                    Color.getHSBColor((float) (Math.random() * 255 + 1),
                    (float) (Math.random() * 255 + 1), (float) (Math.random() * 255 + 1)), frame1);
            if (ballArray1[i].getSize() >= 50) {
                ballArray1[i].setVelocity(1, 1);
            } else {
                ballArray1[i].setVelocity(20 / ballArray1[i].getSize(), 20 / ballArray1[i].getSize());
            }

        }
        //go over half of the balls in the argument array
        for (int i = 0; i < args.length / 2; i++) {
            int x2 = (int) (Math.random() * (frame2.getWidth() - 2 * arrSize2[i])
                    + frame2.getStartingPoint().getX() + arrSize2[i]);
            int y2 = (int) (Math.random() * (frame2.getHeight() - 2 * arrSize2[i])
                    + frame2.getStartingPoint().getY() + arrSize2[i]);
            ballArray2[i] = new Ball(x2, y2, Integer.parseInt(args[i + args.length / 2]),
                    Color.getHSBColor((float) (Math.random() * 255 + 1),
                    (float) (Math.random() * 255 + 1), (float) (Math.random() * 255 + 1)), frame2);
            if (ballArray2[i].getSize() >= 50) {
                ballArray2[i].setVelocity(1, 1);
            } else {
                ballArray2[i].setVelocity(20 / ballArray2[i].getSize(), 20 / ballArray2[i].getSize());
            }

        }
        //this is the part where we draw on the board.
        while (true) {
            DrawSurface rec = gui.getDrawSurface();
            rec.setColor(Color.gray);
            rec.fillRectangle(50, 50, 450, 450);
            rec.setColor(Color.yellow);
            rec.fillRectangle(450, 450, 150, 150);

            for (int i = 0; i < ballArray1.length; i++) {
                ballArray1[i].moveOneStep();
                ballArray1[i].drawOn(rec);
                sleeper.sleepFor(2);
                ballArray2[i].moveOneStep();
                ballArray2[i].drawOn(rec);
                sleeper.sleepFor(2);
            }


            gui.show(rec);
        }

    }


}
