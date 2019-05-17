package sprite;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * A Paddle class.
 * describes a Paddle.
 *
 * @author Tomer Yona
 */

public class SpriteCollection {

    private List<Sprite> spriteList;

    /**
     * Constructor.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<Sprite>();
    }

    /**
     * adds a sprite to the sprite list.
     *
     * @param s .
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }


    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < spriteList.size(); i++){
            spriteList.get(i).timePassed();
        }
        /*
        for (Sprite i : spriteList) {
            i.timePassed();
        }
        */
    }


    /**
     * call drawOn(d) on all sprites.
     *
     * @param d .
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite i : spriteList) {
            i.drawOn(d);
        }
    }

    /**
     * getter of list .
     *
     * @return List .
     */
    public List<Sprite> getSpriteList() {
        return spriteList;
    }
}
