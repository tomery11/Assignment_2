package levels;

import background.FinalFourBackground;
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
 * FinalFour Level.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class FinalFour implements LevelInformation{
    @Override
    public int numberOfBalls() {
        return 3;
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
        list.add(new Velocity(2, 2));
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
        return "Final Four";
    }

    /**
     * Returns a sprite with the background of the level
     *
     * @return Sprite
     */
    @Override
    public Sprite getBackground() {
        return new FinalFourBackground(new Color(0,191,255));
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
        double width = 49.4;
        double height = 30;
        double widthOfScreen = 800;
        Point upperLeft;
        double numOfBlocks = widthOfScreen / width;
        for (int i = 0; i < 7; i++) {
            Color c = getColorOfRow(i);
            upperLeft = new Point(30, height * i + 120);
            // this loop is creating the block for each row
            for (int j = 0; j < 15; j++) {
                Point blockUpperLeft = new Point(30 + j * width, upperLeft.getY());
                //maybe in the future will add here num of hits.
                Block tempBlock = new Block(new Rectangle(blockUpperLeft,width,height),c,1);
                blocks.add(tempBlock);
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
        points.add(new Point(500,370));
        points.add(new Point(400,350));
        points.add(new Point(300, 370));
        return points;
    }

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
                color = Color.GREEN;
                break;
            case 4:
                color = Color.WHITE;
                break;
            case 5:
                color = Color.PINK;
                break;
            case 6:
                color = Color.cyan;
                break;

            default:
                color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        }
        return color;
    }
}
