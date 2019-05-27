package levels;

import background.WideEasyBackground;
import collision.Velocity;
import sprite.Block;
import sprite.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import geometry.Point;

/**
 * Wide easy Level.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class WideEasyLevel implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
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
        for (int i = 0; i < numberOfBalls(); i++) {
            list.add(new Velocity(2, 2));
        }

        return list;

    }

    @Override
    public int paddleSpeed() {
        return 300;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return String
     */
    @Override
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * Returns a sprite with the background of the level
     *
     * @return Sprite
     */
    @Override
    public Sprite getBackground() {
        return new WideEasyBackground(Color.WHITE);
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
        Point upperLeft1;
        Point upperLeft2;
        int i = 0;
        while (i < 6) {
            Color c = getColorOfRow(i);
            upperLeft1 = new Point((int) 30 + i * width, (int) 250);
            Block toAdd1 = new Block(new geometry.Rectangle(upperLeft1, width, height), c, 1);
            blocks.add(toAdd1);
            i++;

            upperLeft2 = new Point((int) 30 + ((i) * width), (int) 250);
            Block toAdd2 = new Block(new geometry.Rectangle(upperLeft2, width, height), c, 1);
            blocks.add(toAdd2);
            i++;
        }

        while (i < 9) {
            Color c = Color.green;
            upperLeft1 = new Point((int) 30 + (i * width), (int) 250);
            Block toAddGreen1 = new Block(new geometry.Rectangle(upperLeft1, width, height), c, 1);
            blocks.add(toAddGreen1);
            i++;


            upperLeft2 = new Point((int) 30 + ((i) * width), (int) 250);
            Block toAddGreen2 = new Block(new geometry.Rectangle(upperLeft2, width, height), c, 1);
            blocks.add(toAddGreen2);
            i++;

            Point upperLeft3 = new Point((int) 30 + ((i) * width), (int) 250);
            Block toAddGreen3 = new Block(new geometry.Rectangle(upperLeft3, width, height), c, 1);
            blocks.add(toAddGreen3);
            i++;
        }

        while (i < 15) {
            Color c = getColorOfRow(i);
            upperLeft1 = new Point((int) 30 + i * width, (int) 250);
            Block toAdd1 = new Block(new geometry.Rectangle(upperLeft1, width, height), c, 1);
            blocks.add(toAdd1);
            i++;
            if (i < 15) {
                upperLeft2 = new Point((int) 30 + (i) * width, (int) 250);
                //Point blockUpperLeft = new Point((int)(widthOfScreen - i / 2 * width) + 10, (int)upperLeft.getY());
                Block toAdd2 = new Block(new geometry.Rectangle(upperLeft2, width, height), c, 1);
                blocks.add(toAdd2);
                i++;
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
        return this.blocks().size();
    }

    @Override
    public List<Point> LocaitonOfBall() {
        List<Point> points = new ArrayList<Point>();
        for (int i = 0; i < this.numberOfBalls() / 2; i++){
            points.add(new Point(100 + 60 * i,400 - 20 * i));
            points.add(new Point(700 - 60 * i,400 - 20 * i));
        }


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
                color = Color.RED;
                break;
            case 1:
                color = Color.RED;
                break;
            case 2:
                color = Color.ORANGE;
                break;
            case 3:
                color = Color.ORANGE;
                break;
            case 4:
                color = Color.YELLOW;
                break;
            case 5:
                color = Color.YELLOW;
                break;
            case 9:
                color = Color.BLUE;
                break;
            case 10:
                color = Color.BLUE;
                break;
            case 11:
                color = Color.PINK;
                break;
            case 12:
                color = Color.PINK;
                break;
            case 13:
                color = Color.cyan;
                break;
            case 14:
                color = Color.cyan;
                break;
            default:
                color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        }
        return color;
    }
}
