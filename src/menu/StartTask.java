package menu;

import animation.AnimationRunner;
import game.GameFlow;
import levels.LevelInformation;

import java.util.List;

public class StartTask implements Task<Void>{
    private GameFlow game;
    private List<LevelInformation> levels;


    public StartTask(GameFlow myGame, List<LevelInformation> list){
        this.game = myGame;
        this.levels = list;
    }

    @Override
    public Void run() {
        this.game.runLevels(this.levels);
        return null;
    }
}
