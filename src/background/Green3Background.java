package background;

import biuoop.DrawSurface;

import java.awt.*;

public class Green3Background extends Background {
    public Green3Background(Color c) {
        super(c);
    }


    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        d.setColor(Color.black);
        d.fillRectangle(70, 400, 120, 200);
        d.setColor(Color.darkGray);
        d.fillRectangle(110, 350, 40,50);
        d.fillRectangle(125,150,10,200);
        d.setColor(Color.orange);
        d.fillCircle(130,150,10);
        d.setColor(Color.red);
        d.fillCircle(130,150,7);
        d.setColor(Color.white);
        d.fillCircle(130,150,3);
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 6; j++) {
                d.fillRectangle(80 + j * 18, 410 + i * 35,12,25);
            }

        }


    }
}
