package background;

import biuoop.DrawSurface;

import java.awt.Color;

public class WideEasyBackground extends Background{
    public WideEasyBackground(Color c) {
        super(c);
    }

    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        d.setColor(Color.lightGray);
        d.fillCircle(150, 150, 80);
        d.setColor(Color.yellow);
        d.fillCircle(150, 150, 75);
        d.setColor(Color.orange);
        d.fillCircle(150, 150, 60);
        for (int i = 30; i <= 770; i = i + 10) {
            d.drawLine(150,150, i, 250);
        }



    }
}
