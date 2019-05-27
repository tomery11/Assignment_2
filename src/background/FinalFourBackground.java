package background;

import biuoop.DrawSurface;

import java.awt.Color;
/**
 * Final Four Background.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class FinalFourBackground extends Background{
    /**
     * constructor.
     * @param c .
     */
    public FinalFourBackground(Color c) {
        super(c);
    }

    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        //rain
        d.setColor(Color.white);
        for (int i = 0; i < 18; i++) {
            d.drawLine(90 + i * 5, 400, 80 + i * 5, 600);
            d.drawLine(590 + i * 5, 500, 580 + i * 5, 600);
        }
        d.setColor(Color.gray);
        //first cloud
        d.fillCircle(160,400,30);
        d.fillCircle(135,400,30);
        d.fillCircle(100,400,25);

        //second cloud
        d.fillCircle(660,500,30);
        d.fillCircle(635,500,30);
        d.fillCircle(600,500,25);

    }
}
