package game;


import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collision.Collidable;
import counter.Counter;
import counter.ScoreTrackingListener;
import levels.LevelInformation;
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

import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

import java.util.List;


/**
 * A GameLevel class.
 * describes a GameLevel.
 *
 * @author Tomer Yona
 */

public class GameLevel implements Animation {


    private LevelInformation currentLevel;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter lifeCounter;
    private ScoreIndicator scoreBoard;


    private AnimationRunner runner;
    private boolean running;

    private KeyboardSensor keyboard;


    /**
     * Constructor of GameLevel.
     */
    public GameLevel(LevelInformation myLevel) {
        this.currentLevel = myLevel;
        this.sprites = new SpriteCollection();

        this.environment = new GameEnvironment();
        this.blockCounter = new Counter(0);
        this.ballCounter = new Counter(0);
        this.lifeCounter = new Counter(4);
        this.scoreBoard = new ScoreIndicator(new Rectangle(new Point(0, 0), 800, 20),
                lifeCounter.getValue(), this.currentLevel.levelName());


        this.runner = new AnimationRunner();
        this.running = true;
        this.keyboard = this.runner.getGui().getKeyboardSensor();


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
        this.sprites.addSprite(this.currentLevel.getBackground());
        //add frame blocks
        frameCreator();
        //addGameBlocksForAss3(6, 60, 30, ball1);
        addGameBlocks(6, 60, 30);
        //add paddle
        addPaddleToGame();
    }

    /**
     * add the paddle to the game.
     */
    public void addPaddleToGame() {
        Rectangle sizeOfPaddle = new Rectangle(new Point(400 - (this.currentLevel.paddleWidth() / 2), 545),
                this.currentLevel.paddleWidth(), 20);
        Paddle paddle = new Paddle(sizeOfPaddle, this.runner.getGui());
        paddle.addToGame(this);
    }


    /**
     * in this function we add blocks to the surface and to the list of sprites and collidables.
     *
     * @param numOfLines .
     * @param width      .
     * @param height     .
     */
    public void addGameBlocks(int numOfLines, double width, double height) {
        //DrawSurface drawSurface = this.runner.getGui().getDrawSurface();
        //double widthOfScreen = drawSurface.getWidth() - 110;
        //double numOfBlocks = widthOfScreen / width;

        //Point upperLeft;
        BlockRemover removeBlocks = new BlockRemover(this, this.blockCounter);

        //score board
        this.sprites.addSprite(scoreBoard);
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(scoreBoard.getScoreCounter());



/*
        for (int i = 0; i < numOfLines; i++) {
            ColorCreator c = getColorOfRow(i);
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

                //this.blockCounter.increase(1);
                tempBlock.addHitListener(scoreTracker);
            }

        }*/
        List<Block> blocks = this.currentLevel.blocks();
        for (Block b : blocks) {
            b.addHitListener(removeBlocks);
            b.addHitListener(scoreTracker);
            this.sprites.addSprite(b);
            this.environment.addCollidable(b);
        }
        this.blockCounter.setCount(this.currentLevel.numberOfBlocksToRemove());
    }

    /**
     * this function runs the game according to ass5.
     */
    public void run() {
        while (this.scoreBoard.getLivesCounter().getValue() > 0) {
            playOneTurn();
        }
        this.runner.getGui().close();
    }

    /**
     * this function plays one turn.
     */
    public void playOneTurn() {
        //adds the balls to surface
        this.createBallsOnTopOfPaddle();
        //doesn't work correctly
        this.runner.run(new CountdownAnimation(2000, 3, this.sprites));

        //this.running can start running back again.
        this.running = true;

        //runs animation of one frame
        this.runner.run(this);
    }

    /**
     * this function is called in playoneturn and and adds the balls over the paddle
     */
    public void createBallsOnTopOfPaddle() {

        Frame frame = new Frame(new Point(0, 0), 600, 800);
        int x = 120;
        Ball[] myBalls = new Ball[this.currentLevel.numberOfBalls()];

        for (int i = 0; i < myBalls.length; i++) {
            myBalls[i] = new Ball(this.currentLevel.LocaitonOfBall().get(i),5, Color.WHITE, frame, this.environment,
                    this.currentLevel.initialBallVelocities().get(i));
            //myBalls[i] = new Ball(x + i * 80, 350, 5, Color.WHITE, frame, this.environment,
                    //this.currentLevel.initialBallVelocities().get(i));
            this.sprites.addSprite(myBalls[i]);

        }
        this.ballCounter.increase(this.currentLevel.numberOfBalls());
    }

    /**
     * this function runs one frame each time it is called.
     *
     * @param d .
     */
    public void doOneFrame(DrawSurface d) {

        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }

        if (this.blockCounter.getValue() == 0) {
            this.running = false;
            this.scoreBoard.getScoreCounter().increase(100);
            this.runner.getGui().close();
        }
        if (this.ballCounter.getValue() == 0) {
            this.running = false;
            this.scoreBoard.getLivesCounter().decrease(1);
        }
    }

    /**
     * this is a method that tell when to get out of the while loop in the play one turn method .
     *
     * @return boolean .
     */
    public boolean shouldStop() {
        return !this.running;

    }

    /**
     * creation of a frame,basically is is 4 blocks around the frame of the surface.
     */
    public void frameCreator() {


        DrawSurface drawSurface = this.runner.getGui().getDrawSurface();
        double width = drawSurface.getWidth();

        double height = drawSurface.getHeight();
        //Ball tmpBall = new Ball();
        //upper frame
        FrameBoundary fB1 = new FrameBoundary(new Rectangle(new Point(0, 20), width, 30),
                false);
        //left frame
        FrameBoundary fB2 = new FrameBoundary(new Rectangle(new Point(0, 50), 30, height - 10),
                false);
        //right frame
        FrameBoundary fB3 = new FrameBoundary(new Rectangle(new Point(width - 30, 50), 30,
                height - 10), false);

        DeathRegion currDeathRegion = new DeathRegion(new Rectangle(new Point(0, height), width, 30),
                new Color(0, 0, 153), "X");
        this.sprites.addSprite(currDeathRegion);
        this.environment.addCollidable(currDeathRegion);

        //hitlistener for removeing balls

        BallRemover removeBalls = new BallRemover(this, this.ballCounter);
        currDeathRegion.addHitListener(removeBalls);


        //add the frames to sprite lists
        this.sprites.addSprite(fB2);
        this.environment.addCollidable(fB2);
        this.sprites.addSprite(fB3);
        this.environment.addCollidable(fB3);
        this.sprites.addSprite(fB1);
        this.environment.addCollidable(fB1);

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
