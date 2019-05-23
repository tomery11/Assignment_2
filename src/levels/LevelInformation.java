package levels;

import collision.Velocity;
import geometry.Point;
import sprite.Block;
import sprite.Sprite;

import java.util.List;

public interface LevelInformation {

    public int numberOfBalls();


    /**
     * The initial velocity of each ball
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return List.
     */
    public List<Velocity> initialBallVelocities();

    public int paddleSpeed();

    public int paddleWidth();


    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return String
     */
    public String levelName();


    /**
     * Returns a sprite with the background of the level
     *
     * @return Sprite
     */
    public Sprite getBackground();


    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return List.
     */
    public List<Block> blocks();


    /**
     * Number of levels that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     *
     * @return int .
     */
    public int numberOfBlocksToRemove();


    public List<Point> LocaitonOfBall();
}
