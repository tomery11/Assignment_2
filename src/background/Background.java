package background;

import biuoop.DrawSurface;
import sprite.Sprite;

import java.awt.Color;

public class Background implements Sprite{
    private Color color;


    public Background(Color c) {
        this.color = c;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d .
     */
    @Override
    public void drawOn(DrawSurface d) {
        //d.setColor(new Color(0, 0, 153));
        //d.fillRectangle(0, 0, 800, 600);
        if (this.color != null) {
            d.setColor(this.color);
            d.fillRectangle(0, 0, 800, 600);
        }
        /*
        d.setColor(Color.RED);
        //d.drawRectangle(290,190,310,210);
        d.setColor(Color.blue);
        d.drawCircle(50,300,200);
        d.drawCircle(100,300,200);
        d.drawCircle(150,300,200);
        d.drawLine(300,120,300,170);
        */
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }

    public Color getColor() {
        return color;
    }
}
