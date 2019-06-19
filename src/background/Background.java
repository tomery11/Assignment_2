package background;

import biuoop.DrawSurface;
import sprite.Sprite;

import java.awt.*;

/**
 * Background class.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class Background implements Sprite {
    private Color color;
    private Image image;

    /**
     * constructor.
     *
     * @param c .
     */
    public Background(Color c) {

        this.color = c;
    }

    /**
     * contructor for image.
     * @param myImage
     */
    public Background(Image myImage) {
        this.image = myImage;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d .
     */
    @Override
    public void drawOn(DrawSurface d) {

        if (this.color != null) {
            d.setColor(this.color);
            d.fillRectangle(0, 0, 800, 600);
        }
        if (this.image != null) {
            d.drawImage(0,0, this.image);
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
