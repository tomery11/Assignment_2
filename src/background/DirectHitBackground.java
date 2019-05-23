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
        //d.setColor(Color.RED);
        //d.drawRectangle(290,190,310,210);
        d.setColor(Color.blue);
        d.drawCircle(300,200,50);
        d.drawCircle(300,200,75);
        d.drawCircle(300,200,100);
        d.drawLine(300,70,300,170);
        d.drawLine(300,230,300,330);
        d.drawLine(170,200,270,200);
        d.drawLine(330,200,430,200);
    }
}
