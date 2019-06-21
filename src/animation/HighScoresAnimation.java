package animation;

import biuoop.DrawSurface;
import game.HighScoresTable;
import game.ScoreInfo;

import java.awt.Color;
import java.util.List;

/**
 * High Score Animation.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class HighScoresAnimation implements Animation {

    private boolean stop;
    private HighScoresTable highScoresTable;

    /**
     * Constructor.
     *
     * @param table .
     */
    public HighScoresAnimation(HighScoresTable table) {
        this.stop = false;
        this.highScoresTable = table;
    }

    /**
     * this function runs one frame each time it is called.
     *
     * @param d .
     */
    @Override
    public void doOneFrame(DrawSurface d) {

        d.setColor(Color.cyan);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        int count = 1;
        String toPrint;
        List<ScoreInfo> list = this.highScoresTable.getHighScores();
        d.drawText(260, 100, "High Scores:", 50);
        for (ScoreInfo score : list) {
            toPrint = count + ": " + "name: " + score.getName() + "\t" + "score: " + score.getScore();
            count++;
            d.drawText(250, 240 + (30 * count), toPrint, 25);
        }


    }

    /**
     * this is a method that tell when to get out of the while loop in the play one turn method .
     *
     * @return boolean.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
