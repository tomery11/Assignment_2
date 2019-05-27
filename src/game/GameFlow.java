package game;

import animation.AnimationRunner;
import animation.GameOverScreen;
import animation.KeyPressStoppableAnimation;
import animation.WinnerScreen;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import counter.Counter;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;
import sprite.ScoreIndicator;

import java.util.List;
/**
 * Game Flow.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class GameFlow {

    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;

    private Counter score;
    private Counter numberOfLives;
    //private Counter numberOfLevels;
    private ScoreIndicator scoreBoard;
    private Sleeper sleeper;


    /**
     * constructor.
     * @param ar .
     * @param ky .
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ky){
        this.animationRunner = ar;
        this.keyboardSensor = ky;
        this.score = new Counter(0);
        this.numberOfLives = new Counter(2);
        //this.numberOfLevels = new Counter(4);
        this.scoreBoard = new ScoreIndicator(new Rectangle(new Point(0, 0), 800, 20),
                this.numberOfLives);
        this.sleeper = new Sleeper();

    }

    /**
     * a function that runs all levels.
     * @param levels .
     */
    public void runLevels(List<LevelInformation> levels) {
        int i = 0;
        for (LevelInformation levelInfo : levels) {
            i++;
            Counter currScore = this.scoreBoard.getScoreCounter();
            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.keyboardSensor,
                     this.numberOfLives,this.scoreBoard);

            level.initialize();
            //level has more blocks and player has more lives
            while (level.getBlockCounter().getValue() > 0 && level.getLifeCounter().getValue() > 0) {
                level.playOneTurn();
            }

            //no more lives
            if (this.numberOfLives.getValue() == 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                        new GameOverScreen(currScore)));
                this.animationRunner.getGui().close();
                break;
            }
            if (i == levels.size() && level.getBlockCounter().getValue() == 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                        new WinnerScreen(currScore)));
                this.animationRunner.getGui().close();
                break;
            }

        }
    }

}
