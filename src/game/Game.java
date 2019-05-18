package game;


import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import collision.Collidable;
import counter.Counter;
import counter.ScoreTrackingListener;
import sprite.LivesIndicator;
import sprite.Paddle;
import sprite.ScoreIndicator;
import sprite.Block;
import sprite.SpriteCollection;
import sprite.Sprite;
import sprite.Frame;
import sprite.Ball;
import sprite.FrameBoundary;
import sprite.DeathRegion;

import collision.BallRemover;
import collision.BlockRemover;
import collision.Velocity;
import collision.PrintingHitListener;
import geometry.Point;
import geometry.Rectangle;

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
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter lifeCounter;
    private ScoreIndicator scoreBoard;
    private LivesIndicator livesBoard;

    /**
     * Constructor of Game.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockCounter = new Counter(0);
        this.ballCounter = new Counter(0);
        this.lifeCounter = new Counter(4);
        this.scoreBoard = new ScoreIndicator(new Rectangle(new Point(0, 0), 800, 20));
        this.livesBoard = new LivesIndicator(new Rectangle(new Point(0, 20), 800, 20),
                lifeCounter.getValue());


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
     * Initialize a new game: create the Blocks and Ball (and Paddle).
     * and add them to the game.
     */
    public void initialize() {
        BallRemover ballRemover = new BallRemover(this, this.ballCounter);
        this.gui = new GUI("araknoid", 800, 600);
        //add frame blocks
        frameCreator();
        //add score board to gui
        //scoreBoardCreator();
        //add blocks that we will hit throughout the game


        //ball1.addHitListener(ballRemover);
        //this.ballCounter.increase(3);

        //addGameBlocksForAss3(6, 60, 30, ball1);

        addGameBlocks(6, 60, 30);
        //add balls to game


        //add paddle
        Rectangle sizeOfPaddle = new Rectangle(new Point(300, 545), 200, 20);
        Paddle paddle = new Paddle(sizeOfPaddle, gui);
        paddle.addToGame(this);


    }

    /**
     * in this function we add blocks as requested in the ass3.
     *
     * @param numberOfRows .
     * @param width        .
     * @param height       .
     * @param ball .
     */
    public void addGameBlocksForAss3(int numberOfRows, double width, double height, Ball ball) {
        DrawSurface d = this.gui.getDrawSurface();
        double screenWidth = d.getWidth();
        BlockRemover removeBlocks = new BlockRemover(this, this.blockCounter);
        PrintingHitListener printTest = new PrintingHitListener();

        //update height for creating scoreboard for gui
        height = height - 590;

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
                //add block to listener
                blockToAdd.addHitListener(removeBlocks);
                this.blockCounter.increase(1);
            }
        }
    }

    /**
     * in this function we add blocks to the surface and to the list of sprites and collidables.
     *
     * @param numOfLines .
     * @param width      .
     * @param height     .
     *
     */
    public void addGameBlocks(int numOfLines, double width, double height) {
        DrawSurface drawSurface = this.gui.getDrawSurface();
        double widthOfScreen = drawSurface.getWidth() - 110;
        double numOfBlocks = widthOfScreen / width;
        //Random random = new Random();
        Point upperLeft;
        BlockRemover removeBlocks = new BlockRemover(this, this.blockCounter);

        //score board
        this.sprites.addSprite(scoreBoard);
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(scoreBoard.getScoreCounter());

        //lives board
        this.sprites.addSprite(livesBoard);


        for (int i = 0; i < numOfLines; i++) {
            Color c = getColorOfRow(i);
            upperLeft = new Point(30, height * i + 150);
            // this loop is creating the block for each row
            for (int j = 0; j < numOfBlocks; j++) {
                Point blockUpperLeft = new Point((widthOfScreen - j * width) + 10, upperLeft.getY());
                //maybe in the future will add here num of hits.
                Block tempBlock = new Block(new Rectangle(blockUpperLeft, width, height), c);
                this.sprites.addSprite(tempBlock);
                this.environment.addCollidable(tempBlock);
                //add block to listener
                tempBlock.addHitListener(removeBlocks);
                this.blockCounter.increase(1);
                tempBlock.addHitListener(scoreTracker);
            }

        }

    }

    /**
     * this function runs the game according to ass5.
     */
    public void run() {
        while (this.livesBoard.getLivesCounter().getValue() > 0) {
            playOneTurn();
        }
        gui.close();
        return;
    }

    /**
     * this function plays one turn.
     */
    public void playOneTurn() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        Sleeper sleeper = new Sleeper();
        //creating balls
        Frame frame = new Frame(new Point(0, 0), 600, 800);
        Velocity velocity = new Velocity(2, 2);
        Ball ball1 = new Ball(150, 350, 5, Color.WHITE, frame, this.environment, velocity);
        this.sprites.addSprite(ball1);
        Ball ball2 = new Ball(300, 350, 5, Color.WHITE, frame, this.environment, velocity);
        this.sprites.addSprite(ball2);
        this.ballCounter.increase(2);

        while (this.ballCounter.getValue() > 0) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            d.setColor(new Color(0, 0, 153));
            d.fillRectangle(0, 0, 800, 600);
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            if (this.blockCounter.getValue() == 0) {
                this.scoreBoard.getScoreCounter().increase(100);
                gui.close();
                return;
            }
            if (this.ballCounter.getValue() == 0) {
                this.livesBoard.getLivesCounter().decrease(1);
                break;
            }

        }
        //gui.close();
    }

    /**
     * creation of a frame,basically is is 4 blocks around the frame of the surface.
     */
    public void frameCreator() {


        DrawSurface drawSurface = this.gui.getDrawSurface();
        double width = drawSurface.getWidth();

        double height = drawSurface.getHeight();
        //Ball tmpBall = new Ball();
        //upper frame
        FrameBoundary fB1 = new FrameBoundary(new Rectangle(new Point(0, 40), width, 30),
                false);
        //left frame
        FrameBoundary fB2 = new FrameBoundary(new Rectangle(new Point(0, 70), 30, height - 60),
                false);
        //right frame
        FrameBoundary fB3 = new FrameBoundary(new Rectangle(new Point(width - 30, 70), 30,
                height - 60), false);

        DeathRegion currDeathRegion = new DeathRegion(new Rectangle(new Point(0, height), width, 30),
                new Color(0, 0, 153), "X");
        this.sprites.addSprite(currDeathRegion);
        this.environment.addCollidable(currDeathRegion);

        //hitlistener for removeing balls

        BallRemover removeBalls = new BallRemover(this, this.ballCounter);
        currDeathRegion.addHitListener(removeBalls);


        //hitlistener for liveRemover
        //LifeRemover removeLife = new LifeRemover(this, this.lifeCounter);
        //currDeathRegion.addHitListener(removeLife);


        //add the frames to sprite lists
        this.sprites.addSprite(fB2);
        this.environment.addCollidable(fB2);
        this.sprites.addSprite(fB3);
        this.environment.addCollidable(fB3);
        this.sprites.addSprite(fB1);
        this.environment.addCollidable(fB1);

    }

    /**
     * this function get a number that represents a row and returns a color.
     *
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

    /**
     * removes a collidable object that was received.
     *
     * @param c .
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidablelist().remove(c);
    }

    /**
     * removes a sprite that was received.
     *
     * @param s .
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSpriteList().remove(s);
    }


}
