package sprite;

import collision.HitNotifier;
import geometry.Point;
import geometry.Rectangle;

import java.awt.*;

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
        super(rec,  c);

    }
    public DeathRegion(Rectangle rec, Color c, String hits) {
        super(rec,  c, hits);

    }
}
