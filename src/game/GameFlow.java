package game;

import animation.*;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;

import counter.Counter;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;
import menu.*;
import sprite.ScoreIndicator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    private ScoreIndicator scoreBoard;
    private HighScoresTable highScoresTable;


    /**
     * constructor.
     *
     * @param ar    .
     * @param ky    .
     * @param table .
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ky, HighScoresTable table) {
        this.animationRunner = ar;
        this.keyboardSensor = ky;
        this.score = new Counter(0);
        this.numberOfLives = new Counter(2);
        this.scoreBoard = new ScoreIndicator(new Rectangle(new Point(0, 0), 800, 20),
                this.numberOfLives);

        this.highScoresTable = table;
        //this.sleeper = new Sleeper();

    }

    public void startMenu(List<LevelInformation> levelList) {
        List<SelectOption<Task<Void>>> allSelections = new ArrayList<SelectOption<Task<Void>>>();
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("arkanoid", keyboardSensor, animationRunner);
        menu.addSelection("s", "Press (S) to Start Game", new StartTask(this, levelList));
        HighScoresAnimation scoreScr = new HighScoresAnimation(this.highScoresTable);
        menu.addSelection("h", "Press (H) for High scores", new ShowHiScoresTask(this.animationRunner,
                new HighScoresAnimation(this.highScoresTable),
                this.keyboardSensor));
        menu.addSelection("e", "Press (E) to Exit", new QuitTask(this.animationRunner));
        // menu.doOneFrame(this.animationRunner.getGui().getDrawSurface());
        while (true) {
            this.animationRunner.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
            ((MenuAnimation) menu).setStop(false);

        }

    }

    /**
     * a function that runs all levels.
     *
     * @param levels .
     */
    public void runLevels(List<LevelInformation> levels) {
        int i = 0;
        for (LevelInformation levelInfo : levels) {
            i++;
            Counter currScore = this.scoreBoard.getScoreCounter();
            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.keyboardSensor,
                    this.numberOfLives, this.scoreBoard);

            level.initialize();
            //level has more blocks and player has more lives
            while (level.getBlockCounter().getValue() > 0 && level.getLifeCounter().getValue() > 0) {
                level.playOneTurn();
            }

            //no more lives
            if (this.numberOfLives.getValue() == 0) {
                addToTable();
                this.highScoresTable.printTable();

                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                        new GameOverScreen(currScore)));


                break;
            }
            if (i == levels.size() && level.getBlockCounter().getValue() == 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                        new WinnerScreen(currScore)));

                addToTable();
                this.highScoresTable.printTable();
                break;
            }


        }

        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                new HighScoresAnimation(highScoresTable)));

    }

    /**
     * add the new information to the high score table if is good.
     */
    public void addToTable() {


        if (this.highScoresTable.getRank(this.scoreBoard.getScoreCounter().getValue()) <= this.highScoresTable.size()) {
            DialogManager dialog = this.animationRunner.getGui().getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");

            this.highScoresTable.add(new ScoreInfo(name, this.scoreBoard.getScoreCounter().getValue()));
            File highScoresFile = new File("highscores.txt");
            try {
                this.highScoresTable.save(highScoresFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


}
