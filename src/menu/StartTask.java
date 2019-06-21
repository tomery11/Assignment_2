package menu;


import counter.Counter;
import game.GameFlow;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;
import sprite.ScoreIndicator;

import java.util.List;

/**
 * StartTask.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class StartTask implements Task<Void> {
    private GameFlow game;
    private List<LevelInformation> levels;

    /**
     * Constructor.
     *
     * @param myGame .
     * @param list   .
     */
    public StartTask(GameFlow myGame, List<LevelInformation> list) {
        this.game = myGame;
        this.levels = list;
    }

    @Override
    public Void run() {
        this.game.runLevels(this.levels);
        this.game.setNumberOfLives(new Counter(3));
        this.game.setScoreBoard(new ScoreIndicator(new Rectangle(new Point(0, 0), 800, 20),
                this.game.getNumberOfLives()));

        return null;
    }
}
