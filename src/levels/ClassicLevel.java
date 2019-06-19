package levels;


import background.ClassicBackground;
import collision.Velocity;
import geometry.Point;
import geometry.Rectangle;
import sprite.Block;
import sprite.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classic Level.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class ClassicLevel implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls() .
     *
     * @return List.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<Velocity>();
        list.add(new Velocity(2, 2));
        list.add(new Velocity(3, 3));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 300;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return String
     */
    @Override
    public String levelName() {
        return "Classic";
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return Sprite .
     */
    @Override
    public Sprite getBackground() {
        return new ClassicBackground(new Color(0, 0, 153));
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
        Color color;
        int counter = 0;
        int y = 140;
        for (int i = 0; i < 5; i++) {
            int x = 160;
            for (int j = 0; j < 10; j++) {
                color = getColorOfRow(i);
                Block block = new Block(new Rectangle(new Point(x, y), 40, 30), color, 2);
                //block.setPosition(counter);
                blocks.add(block);

                x += 50;
                counter++;
            }
            y += 40;
        }
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
    public List<Point> ballLocation() {
        List<Point> points = new ArrayList<Point>();
        points.add(new Point(400, 400));
        points.add(new Point(200, 400));
        return points;
    }

    /**
     * this function get a number that represents a row and returns a color.
     *
     * @param i .
     * @return ColorCreator
     */
    public Color getColorOfRow(int i) {
        Random rand = new Random();
        Color color;
        switch (i) {
            case 0:
                color = Color.GRAY;
                break;
            case 1:
                color = Color.RED;
                break;
            case 2:
                color = Color.YELLOW;
                break;
            case 3:
                color = Color.BLUE;
                break;
            case 4:
                color = Color.pink;
                break;
            case 5:
                color = Color.green;
                break;
            default:
                color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        }
        return color;
    }
}
