package game;


import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import animation.GameOverScreen;
import animation.WinnerScreen;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;

import counter.Counter;
import geometry.Point;
import geometry.Rectangle;
import io.LevelSetReader;
import io.LevelSpecificationReader;
import levels.LevelInformation;
import menu.SubMenuTask;
import menu.Task;
import menu.SelectOption;
import menu.SubMenuAnimation;
import menu.MenuAnimation;
import menu.Menu;
import menu.ShowHiScoresTask;
import menu.StartTask;
import menu.QuitTask;


import sprite.ScoreIndicator;

import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        this.numberOfLives = new Counter(3);
        this.scoreBoard = new ScoreIndicator(new Rectangle(new Point(0, 0), 800, 20),
                this.numberOfLives);

        this.highScoresTable = table;
        //this.sleeper = new Sleeper();

    }

    /**
     * this function starts the menu.
     *
     * @param levelList .
     * @param subMenu   .
     */
    public void startMenu(List<LevelInformation> levelList, SubMenuAnimation<Task<Void>> subMenu) {
        List<SelectOption<Task<Void>>> allSelections = new ArrayList<SelectOption<Task<Void>>>();
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Arkanoid", keyboardSensor, animationRunner);
        menu.addSelection("s", "Press (S) to Start Game",
                new SubMenuTask(subMenu, animationRunner, keyboardSensor));
        //menu.addSelection("s", "Press (S) to Start Game", new StartTask(this, levelList));
        HighScoresAnimation scoreScr = new HighScoresAnimation(this.highScoresTable);
        menu.addSelection("h", "Press (H) for High scores", new ShowHiScoresTask(this.animationRunner,
                new HighScoresAnimation(this.highScoresTable),
                this.keyboardSensor));
        menu.addSelection("e", "Press (E) to Exit", new QuitTask(this.animationRunner));

        while (true) {
            this.animationRunner.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
            if (task instanceof SubMenuTask) {
                Task<Void> task1 = subMenu.getStatus();
                task1.run();
            }
            ((MenuAnimation) menu).setStop(false);

        }

    }

    /**
     * this is the first thing that starts the game flow.
     *
     * @param path .
     * @throws IOException .
     */
    public void startGameFlow(String path) throws IOException {

        List<LevelInformation> levelList = new ArrayList<>();

        LevelSetReader levelSetReader = new LevelSetReader(Paths.get(path));
        levelSetReader.fromReader(new FileReader(path));

        SubMenuAnimation<Task<Void>> subMenu =
                new SubMenuAnimation<Task<Void>>("Arkanoid", keyboardSensor, animationRunner);
        levelList = getLevelListToRun(path, subMenu);


        this.startMenu(levelList, subMenu);
    }

    /**
     * this function gets a path and and a submenu task, sends the file to LevelSetReader where there the file
     * is parsed and we then use the levelSpecificationReader in order to create a level list.
     *
     * @param path    .
     * @param subMenu .
     * @return List<LevelInformation>
     * @throws IOException .
     */
    private List<LevelInformation> getLevelListToRun(String path, SubMenuAnimation<Task<Void>> subMenu)
            throws IOException {
        List<LevelSpecificationReader> levelSpecificationReaderList = new ArrayList<>();
        LevelSetReader levelSetReader = new LevelSetReader(Paths.get(path));
        levelSetReader.fromReader(new FileReader(path));
        Map<String, List<String>> levelSetMap = levelSetReader.getMapToAdd();
        List<String> keyStringList = new ArrayList<>();
        List<String> messageStringList = new ArrayList<>();
        List<String> pathStringList = new ArrayList<>();

        for (Map.Entry<String, List<String>> entry : levelSetMap.entrySet()) {
            levelSpecificationReaderList.add(new LevelSpecificationReader(
                    Paths.get(entry.getValue().get(1))));

            keyStringList.add(entry.getKey() + "");
            messageStringList.add("Press (" + entry.getKey() + ") for " + entry.getValue().get(0) + " Level");
            pathStringList.add(entry.getValue().get(1));


        }
        int i = 0;
        for (LevelSpecificationReader lsr : levelSpecificationReaderList) {
            subMenu.addSelection(keyStringList.get(i),
                    messageStringList.get(i)
                    , new StartTask(this,
                            levelSpecificationReaderList.get(i).fromReader(new FileReader(pathStringList.get(i)))));
            i++;
        }

        return new ArrayList<>();
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
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                        new GameOverScreen(currScore)));

                break;
            }
            if (i == levels.size() && level.getBlockCounter().getValue() == 0) {
                addToTable();
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                        new WinnerScreen(currScore)));

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

    /**
     * setter .
     *
     * @param numberOfLives1 .
     */
    public void setNumberOfLives(Counter numberOfLives1) {
        this.numberOfLives = numberOfLives1;
    }

    /**
     * setter .
     *
     * @param scoreBoard1 .
     */
    public void setScoreBoard(ScoreIndicator scoreBoard1) {
        this.scoreBoard = scoreBoard1;
    }

    /**
     * getter .
     *
     * @return Counter .
     */
    public Counter getNumberOfLives() {
        return numberOfLives;
    }
}
