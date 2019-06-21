package io;

import collision.Velocity;
import geometry.Point;
import levels.LevelInformation;
import sprite.Block;
import sprite.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * Level info for ass7.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class LevelInfo7 implements LevelInformation {
    private int numberOfBalls;
    private List<Velocity> initialBallVelocity;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite getBackground;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;
    private List<Point> ballLocation;


    /**
     * number of balls.
     *
     * @return number of balls.
     */
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * Setter.
     *
     * @param numberOfBalls .
     */
    public void setNumberOfBalls(int numberOfBalls) {
        this.numberOfBalls = numberOfBalls;
    }

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls().
     *
     * @return List.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocity;
    }

    /**
     * Setter.
     *
     * @param initialBallVelocity .
     */
    public void setInitialBallVelocity(List<Velocity> initialBallVelocity) {
        this.initialBallVelocity = initialBallVelocity;
    }

    /**
     * paddle speed.
     *
     * @return int .
     */
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * Setter.
     *
     * @param paddleSpeed .
     */
    public void setPaddleSpeed(int paddleSpeed) {
        this.paddleSpeed = paddleSpeed;
    }

    /**
     * paddle width.
     *
     * @return int .
     */
    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * Setter.
     *
     * @param paddleWidth .
     */
    public void setPaddleWidth(int paddleWidth) {
        this.paddleWidth = paddleWidth;
    }

    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return String
     */
    @Override
    public String levelName() {
        return this.levelName;
    }

    /**
     * Setter.
     *
     * @param levelName .
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return Sprite
     */
    @Override
    public Sprite getBackground() {
        return this.getBackground;
    }

    /**
     * Setter.
     *
     * @param getBackground .
     */
    public void setGetBackground(Sprite getBackground) {
        this.getBackground = getBackground;
    }

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return List.
     */
    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * Setter.
     *
     * @param blocks .
     */
    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
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
        return this.numberOfBlocksToRemove;
    }

    /**
     * Setter.
     *
     * @param numberOfBlocksToRemove .
     */
    public void setNumberOfBlocksToRemove(int numberOfBlocksToRemove) {
        this.numberOfBlocksToRemove = numberOfBlocksToRemove;
    }

    /**
     * returns the location of the ball.
     *
     * @return List.
     */
    @Override
    public List<Point> ballLocation() {
        List<Point> locationList = new ArrayList<>(this.numberOfBalls);
        int x = 400;
        int y = 400;
        int i = 0;
        for (int j = 0; j < this.numberOfBalls; j++) {
            Point p = new Point(x + i, y);
            locationList.add(p);
            i += 45;
        }
        return locationList;
    }

    /**
     * Setter.
     *
     * @param ballLocation .
     */
    public void setBallLocation(List<Point> ballLocation) {
        this.ballLocation = ballLocation;
    }
}
