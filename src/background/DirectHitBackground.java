package background;

import biuoop.DrawSurface;

import java.awt.*;

public class DirectHitBackground extends Background{
    public DirectHitBackground(Color c) {
        super(c);
    }

    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        //d.setColor(ColorCreator.RED);
        //d.drawRectangle(290,190,310,210);
        d.setColor(Color.blue);
        d.drawCircle(400,200,50);
        d.drawCircle(400,200,75);
        d.drawCircle(400,200,100);
        d.drawLine(400,70,400,170);
        d.drawLine(400,230,400,330);
        d.drawLine(270,200,370,200);
        d.drawLine(430,200,530,200);
    }
}
