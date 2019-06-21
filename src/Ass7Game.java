import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameFlow;
import game.HighScoresTable;
import io.LevelSetReader;
import io.LevelSpecificationReader;
import levels.Green3Level;
import levels.LevelInformation;

import menu.Menu;
import menu.MenuAnimation;
import menu.SelectOption;
import menu.Task;


public class Ass7Game {

    public static void main(String[] args) throws IOException {


        AnimationRunner ar = new AnimationRunner();
        KeyboardSensor ks = ar.getGui().getKeyboardSensor();


        HighScoresTable highScoreTable;
        File file = new File("highscores.txt");
        if (file.exists()) {
            highScoreTable = HighScoresTable.loadFromFile(file);
        } else {
            highScoreTable = new HighScoresTable(5);
        }

        /*
        List<LevelInformation> l = new ArrayList<>();
        List<LevelSpecificationReader> levelSpecificationReaderList = new ArrayList<>();
        LevelSetReader levelSetReader = new LevelSetReader(Paths.get("level_set.txt"));
        levelSetReader.fromReader(new FileReader("level_set.txt"));
        Map<String, List<String>> levelSetMap = levelSetReader.getMapToAdd();
        for (Map.Entry<String, List<String>> entry : levelSetMap.entrySet()) {
            levelSpecificationReaderList.add(new LevelSpecificationReader
                    (Paths.get(entry.getValue().get(1))));
        }
        //LevelSpecificationReader toAdd  = new LevelSetReader(Paths.get("level_set.txt"));
        //List<LevelSpecificationReader> levelSpecificationReaderList= toAdd.fromReader(new FileReader("level_set.txt"));


        LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader
                (Paths.get("level_definition.txt"));
        try {
            l = levelSpecificationReader.fromReader(new FileReader("level_definition.txt"));
        } catch (FileNotFoundException f) {
            System.out.println("file not found");
        } catch (IOException i) {
            System.out.println("IO exception");
        }
        */

        GameFlow gameFlow = new GameFlow(ar, ks, highScoreTable);
        gameFlow.startGameFlow(args[0]);
        //gameFlow.startMenu(l);

    }

}
