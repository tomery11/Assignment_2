

import biuoop.GUI;
import biuoop.DrawSurface;
import java.awt.*;
import java.util.Random;

public class AbstractArtDrawing {

    public void AbstractArtDrawing() {
        Random rand = new Random(); // create a random-number generator
        // Create a window with the title "Random Circles Example"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random Lines", 400, 300);
        DrawSurface l = gui.getDrawSurface();
        DrawSurface b_circle = gui.getDrawSurface();
        DrawSurface r_circle = gui.getDrawSurface();
        Line arr_line[] = new Line[10];
        for (int i = 0; i < 10; ++i) {
            int x1 = rand.nextInt(400) + 1; // get integer in range 1-400
            int y1 = rand.nextInt(300) + 1; // get integer in range 1-300
            int x2 = rand.nextInt(400) + 1; // get integer in range 1-400
            int y2 = rand.nextInt(300) + 1; // get integer in range 1-300
            Point start = new Point(x1, y1);
            Point end = new Point(x2, y2);
            Line curr_line = new Line(start, end);
            arr_line[i] = curr_line;
            l.setColor(Color.black);
            l.drawLine(x1, y1, x2, y2);
            l.setColor(Color.blue);
            l.fillCircle((int)curr_line.middle().getX(),(int)curr_line.middle().getY(),3);


        }


        l.setColor(Color.red);
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                Point intersec = arr_line[i].intersectionWith(arr_line[j]);
                if (arr_line[i].isIntersecting(arr_line[j])){
                    if(intersec != null){
                        l.fillCircle((int)intersec.getX(), (int)intersec.getY(), 3);

                    }

                }
            }
        }

        gui.show(l);

    }

    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.AbstractArtDrawing();
    }

}
