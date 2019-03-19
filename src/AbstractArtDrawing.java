

import biuoop.GUI;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * Class AbstractArtDrawing draws a gui with lines, when lines
 * intersect there is a red dot on the intersection point,
 * in the middle of each line there is a blue dot.
 *
 * @author Tomer Yona
 * @version 1.2 15 Mar 2019
 */
public class AbstractArtDrawing {
    /**
     * AbstractArtDrawing Class AbstractArtDrawing draws a gui with lines, when lines
     * intersect there is a red dot on the intersection point,
     * in the middle of each line there is a blue dot.
     */
    public void abstractArtDrawing1() {
        Random rand = new Random(); // create a random-number generator
        // Create a window with the title "Random Circles Example"
        GUI gui = new GUI("Random Lines", 400, 300);
        DrawSurface l = gui.getDrawSurface();
        Line [] arrLine = new Line[10];
        for (int i = 0; i < 10; ++i) {
            int x1 = rand.nextInt(400) + 1; // get integer in range 1-400
            int y1 = rand.nextInt(300) + 1; // get integer in range 1-300
            int x2 = rand.nextInt(400) + 1; // get integer in range 1-400
            int y2 = rand.nextInt(300) + 1; // get integer in range 1-300
            Point start = new Point(x1, y1);
            Point end = new Point(x2, y2);
            Line currLine = new Line(start, end);
            arrLine[i] = currLine;
            l.setColor(Color.black);
            l.drawLine(x1, y1, x2, y2);
            l.setColor(Color.blue);
            l.fillCircle((int) currLine.middle().getX(), (int) currLine.middle().getY(), 3);


        }


        l.setColor(Color.red);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Point intersec = arrLine[i].intersectionWith(arrLine[j]);
                if (arrLine[i].isIntersecting(arrLine[j])) {
                    if (intersec != null) {
                        l.fillCircle((int) intersec.getX(), (int) intersec.getY(), 3);

                    }

                }
            }
        }

        gui.show(l);

    }

    /**
     * main function.
     * <p>
     * draws a gui with lines, when lines
     * intersect there is a red dot on the intersection point,
     * in the middle of each line there is a blue dot.
     * <p>
     *
     * @param args .
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.abstractArtDrawing1();
    }

}
