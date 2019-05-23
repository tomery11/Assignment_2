package levels;

import background.Background;
import background.DirectHitBackground;
import collision.Velocity;
import geometry.Point;
import geometry.Rectangle;
import sprite.Block;
import sprite.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    /**
     * The initial velocity of each ball
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return List.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<Velocity>();
        list.add(new Velocity(0, 2));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 300;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return String
     */
    @Override
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * Returns a sprite with the background of the level
     *
     * @return Sprite
     */
    @Override
    public Sprite getBackground() {
        return new DirectHitBackground(Color.black);
    }

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return List.
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();

        Block toAdd = new Block(new Rectangle(new Point(390, 190), 20, 20),
                Color.RED, 1);
        toAdd.setNumOfHits(1);
        blocks.add(toAdd);
        return blocks;
    }

    /**
     * Number of levels that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     *
     * @return int .
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }

    @Override
    public List<Point> LocaitonOfBall() {
        List<Point> points = new ArrayList<Point>();
        points.add(new Point(400,400));
        return points;
    }
}
