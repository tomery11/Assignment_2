import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameFlow;
import game.HighScoresTable;
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
        //Menu<Task<Void>> mainMenu = new MenuAnimation<Task<Void>>("Araknoid", ks, ar);
        HighScoresTable highScoreTable;
        File file = new File("highscores.txt");
        if(file.exists()) {
            highScoreTable = HighScoresTable.loadFromFile(file);
        } else {
            highScoreTable = new HighScoresTable(5);
        }
        //File highScoreFile = new File("highscores.txt");
        //HighScoresTable highScoreTable = HighScoresTable.loadFromFile(highScoreFile);
        List<LevelInformation> l = new ArrayList<>();

        LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader
                (Paths.get("level_definition.txt"));
        try {
            l = levelSpecificationReader.fromReader(new FileReader("level_definition.txt"));
        } catch (FileNotFoundException f){
            System.out.println("file not found");
        } catch (IOException i){
            System.out.println("IO exception");
        }
        //l.add(new Green3Level());
        GameFlow gameFlow = new GameFlow(ar, ks ,highScoreTable);
        gameFlow.startMenu(l);

    }

}
