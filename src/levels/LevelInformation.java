package levels;

import collision.Velocity;
import geometry.Point;
import sprite.Block;
import sprite.Sprite;

import java.util.List;

/**
 * level info interface.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public interface LevelInformation {
    /**
     * number of balls.
     * @return number of balls.
     */
    int numberOfBalls();


    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls().
     *
     * @return List.
     */
    List<Velocity> initialBallVelocities();

    /**
     * paddle speed.
     *
     * @return int .
     */
    int paddleSpeed();

    /**
     * paddle width.
     *
     * @return int .
     */
    int paddleWidth();


    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return String
     */
    String levelName();


    /**
     * Returns a sprite with the background of the level.
     *
     * @return Sprite
     */
    Sprite getBackground();


    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return List.
     */
    List<Block> blocks();


    /**
     * Number of levels that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     *
     * @return int .
     */
    int numberOfBlocksToRemove();

    /**
     * returns the location of the ball.
     * @return List.
     */
    List<Point> ballLocation();
}
