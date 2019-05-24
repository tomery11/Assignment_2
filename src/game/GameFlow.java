package game;

import animation.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import counter.Counter;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;
import sprite.ScoreIndicator;

import java.util.List;

public class GameFlow {

    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    //private GUI gui;
    private Counter score;
    private Counter numberOfLives;
    private Counter numberOfLevels;
    private ScoreIndicator scoreBoard;

    //private List<LevelInformation> levelInformationList;



    public GameFlow(AnimationRunner ar, KeyboardSensor ky){
        this.animationRunner = ar;
        this.keyboardSensor = ky;
        //this.gui = myGui;
        this.score = new Counter(0);
        this.numberOfLives = new Counter(4);
        this.numberOfLevels = new Counter(4);
        this.scoreBoard = new ScoreIndicator(new Rectangle(new Point(0, 0), 800, 20),
                this.numberOfLives);




    }


    public void runLevels(List<LevelInformation> levels) {
        // ...
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.keyboardSensor,
                     this.numberOfLives,this.scoreBoard);

            level.initialize();
            //level has more blocks and player has more lives
            while (level.getBlockCounter().getValue() > 0 && level.getLifeCounter().getValue() > 0) {
                level.playOneTurn();
            }
            //no more lives
            if (this.numberOfLives.getValue() == 0) {
                this.animationRunner.getGui().close();
                break;

            }
            //this.score.increase(100);

        }
    }

}
