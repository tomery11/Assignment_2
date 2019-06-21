package background;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Menu background.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class MenuBackground extends Background {
    /**
     * constructor.
     *
     * @param c .
     */
    public MenuBackground(Color c) {
        super(c);
    }

    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);

        d.setColor(Color.white);
        d.drawText(350, 80, "Araknoid", 40);
        d.drawText(380, 150, "Options", 30);
        d.drawText(350, 200, "Press \"s\" to start a new game", 30);
        d.drawText(350, 220, "Press \"h\" to see the high scores", 30);
        d.drawText(350, 240, "Press \"q\" to quit", 30);


        //first cloud
        d.fillCircle(160, 400, 30);
        d.fillCircle(135, 400, 30);
        d.fillCircle(100, 400, 25);

        //second cloud
        d.fillCircle(660, 500, 30);
        d.fillCircle(635, 500, 30);
        d.fillCircle(600, 500, 25);

    }
}
