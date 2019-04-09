import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * A Game class.
 * describes a Game.
 *
 * @author Tomer Yona
 */

public class Game {

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;


    /**
     * Constructor of Game.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();

    }

    /**
     * adds colliadble to enviorment.
     *
     * @param c .
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * adds sprite to sprite collection.
     *
     * @param s .
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }



    /**
     *Initialize a new game: create the Blocks and Ball (and Paddle).
     *and add them to the game.
     */
    public void initialize() {
        this.gui = new GUI("araknoid", 1000, 600);
        //add frame blocks
        frameCreator();
        //add blocks that we will hit throughout the game
        addGameBlocksForAss3(6, 60, 30);
        //addGameBlocks(6, 60, 30);
        //add balls to game
        Frame frame = new Frame(new Point(0, 0), 600, 1000);
        Velocity velocity = new Velocity(2, 2);
        this.sprites.addSprite(new Ball(150, 350, 5, Color.WHITE, frame, this.environment, velocity));
        this.sprites.addSprite(new Ball(300, 350, 5, Color.WHITE, frame, this.environment, velocity));
        //add paddle
        Rectangle sizeOfPaddle = new Rectangle(new Point(100, 545), 250, 25);
        Paddle paddle = new Paddle(sizeOfPaddle, gui);
        paddle.addToGame(this);


    }

    /**
     * in this function we add blocks as requested in the ass3.
     * @param numberOfRows .
     * @param width .
     * @param height .
     */
    public void addGameBlocksForAss3(int numberOfRows, double width, double height) {
        DrawSurface d = this.gui.getDrawSurface();
        double screenWidth = d.getWidth();

        Point upperLeft;
        double numberOfBlocks = 12; // number of block in each row;
        Color color;
        for (int i = 0; i < numberOfRows; i++) {
            color = getColorOfRow(i);
            upperLeft = new Point(0, height * i + 150);
            for (int j = 0; j < numberOfBlocks - i; j++) {
                Point blockUpperLeft = new Point(screenWidth - 90 - j * width, upperLeft.getY());
                Block blockToAdd = new Block(new Rectangle(blockUpperLeft, width, height), color);
                this.sprites.addSprite(blockToAdd);
                this.environment.addCollidable(blockToAdd);
            }
        }
    }

    /**
     * in this function we add blocks to the surface and to the list of sprites and collidables.
     *
     * @param numOfLines .
     * @param width .
     * @param height .
     */
    public void addGameBlocks(int numOfLines, double width, double height) {
        DrawSurface drawSurface = this.gui.getDrawSurface();
        double widthOfScreen = drawSurface.getWidth() - 110;
        double numOfBlocks = widthOfScreen / width;
        Random random = new Random();
        Point upperLeft;

        for (int i = 0; i < numOfLines; i++) {
            Color c = getColorOfRow(i);
            upperLeft = new Point(30, height * i + 150);
            // this loop is creating the block for each row
            for (int j = 0; j < numOfBlocks; j++) {
                Point blockUpperLeft = new Point(widthOfScreen - j * width, upperLeft.getY());
                //maybe in the future will add here num of hits.
                Block tempBlock = new Block(new Rectangle(blockUpperLeft, width, height), c);
                this.sprites.addSprite(tempBlock);
                this.environment.addCollidable(tempBlock);
            }

        }

    }



    /**
     *Run the game -- start the animation loop.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        Sleeper sleeper = new Sleeper();
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            d.setColor(new Color(0, 0, 153));
            d.fillRectangle(0, 0, 1000, 600);
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * creation of a frame,basically is is 4 blocks around the frame of the surface.
     */
    public void frameCreator() {
        DrawSurface drawSurface = this.gui.getDrawSurface();
        double width = drawSurface.getWidth();
        double height = drawSurface.getHeight();
        FrameBoundary fB1 = new FrameBoundary(new Rectangle(new Point(0, 0), width, 30));
        FrameBoundary fB2 = new FrameBoundary(new Rectangle(new Point(0, 30), 30, height - 60));
        FrameBoundary fB3 = new FrameBoundary(new Rectangle(new Point(width - 30, 30), 30,
                height - 60));
        FrameBoundary fB4 = new FrameBoundary(new Rectangle(new Point(0, height - 30), width, 30));
        this.sprites.addSprite(fB1);
        this.environment.addCollidable(fB1);
        this.sprites.addSprite(fB2);
        this.environment.addCollidable(fB2);
        this.sprites.addSprite(fB3);
        this.environment.addCollidable(fB3);
        this.sprites.addSprite(fB4);
        this.environment.addCollidable(fB4);

    }

    /**
     * this function get a numbor that represents a row and returns a color.
     * @param i .
     * @return Color
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
