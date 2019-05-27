package levels;

import background.Green3Background;
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
 * Green3 Level.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class Green3Level implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
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
        return "Green 3";
    }

    /**
     * Returns a sprite with the background of the level
     *
     * @return Sprite
     */
    @Override
    public Sprite getBackground() {
        return new Green3Background(new Color(0, 127, 70));
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
        Random rand = new Random();
        Point upperLeft;
        double width = 40;
        double height = 20;
        double numberOfBlocks = 12; // number of block in each row;
        Color color;
        for (int i = 0; i < 5; i++) {
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
                    color = Color.white;
                    break;
                case 5:
                    color = Color.green;
                    break;
                default:
                    color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            }
            upperLeft = new Point(0, 20 * i + 150);
            for (int j = 0; j < numberOfBlocks - i; j++) {
                Point blockUpperLeft = new Point(800 - 90 - j * 40, upperLeft.getY());
                Block tempBlock;
                if (i == 0) {
                    tempBlock = new Block(new geometry.Rectangle(blockUpperLeft, width, height),color,2);
                    blocks.add(tempBlock);
                } else {
                    tempBlock = new Block(new Rectangle(blockUpperLeft, width, height), color, 1);
                    blocks.add(tempBlock);
                }

            }
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
        return blocks().size();
    }

    @Override
    public List<Point> LocaitonOfBall() {
        List<Point> points = new ArrayList<Point>();
        points.add(new Point(400,400));
        points.add(new Point(200,400));
        return points;
    }
}
