package background;

import biuoop.DrawSurface;
import sprite.Sprite;

import java.awt.Color;

/**
 * Background class.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class Background implements Sprite {
    private Color color;

    /**
     * constructor.
     *
     * @param c .
     */
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
        //d.setColor(new ColorCreator(0, 0, 153));
        //d.fillRectangle(0, 0, 800, 600);
        if (this.color != null) {
            d.setColor(this.color);
            d.fillRectangle(0, 0, 800, 600);
        }
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * getter for color background.
     * @return Color.
     */
    public Color getColor() {
        return color;
    }
}
