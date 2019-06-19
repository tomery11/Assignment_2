package background;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * DirectHit Background.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class DirectHitBackground extends Background {
    /**
     * constructor.
     *
     * @param c .
     */
    public DirectHitBackground(Color c) {
        super(c);
    }

    @Override
    /**
     * draw on surface.
     */
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        d.setColor(Color.blue);
        d.drawCircle(400, 200, 50);
        d.drawCircle(400, 200, 75);
        d.drawCircle(400, 200, 100);
        d.drawLine(400, 70, 400, 170);
        d.drawLine(400, 230, 400, 330);
        d.drawLine(270, 200, 370, 200);
        d.drawLine(430, 200, 530, 200);
    }
}
