package sprite;


import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
/**
 * Class DeathReigon describes where ball hits it disapears.
 *
 * @author Tomer Yona
 * @version 1.2 3 Apr 2019
 */
public class DeathRegion extends Block {
    /**
     * constructor.
     *
     * @param block .
     */
    public DeathRegion(Rectangle block) {
        super(block);
    }

    /**
     * constructor.
     *
     * @param p .
     * @param w .
     * @param h .
     */
    public DeathRegion(Point p, int w, int h) {
        super(p, w, h);
    }

    /**
     * constructor.
     *
     * @param rec .
     * @param c   .
     */
    public DeathRegion(Rectangle rec, Color c) {
        super(rec, c,0);

    }

    /**
     * constructor.
     * @param rec .
     * @param c .
     * @param hits .
     */
    public DeathRegion(Rectangle rec, Color c, String hits) {
        super(rec, c, hits);

    }
}
