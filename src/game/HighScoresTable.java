package game;

import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * A HighScoreTable class.
 * describes a GameLevel.
 *
 * @author Tomer Yona
 */

public class HighScoresTable implements Serializable {
    private static final long serialVersionUID = 1L;
    private int sizeOfTable;
    private List<ScoreInfo> tableList;
    private int added;


    /**
     * Create an empty high-scores table with the specified size.
     * The size means that the table holds up to size top scores.
     *
     * @param size .
     */
    public HighScoresTable(int size) {
        this.sizeOfTable = size;
        this.tableList = new ArrayList<>(size);
        this.added = 0;
    }


    /**
     * Add a high-score. I want to use insertion sort algorithm.
     *
     * @param score .
     */
    public void add(ScoreInfo score) {

        int rank = this.getRank(score.getScore());
        if (rank <= this.sizeOfTable) {
            this.tableList.add(rank - 1, score);
        }
        if (this.tableList.size() > this.sizeOfTable) {
            this.tableList.remove(this.tableList.size() - 1);
        }

    }


    /**
     * Return table size.
     *
     * @return int .
     */
    public int size() {
        return this.sizeOfTable;
    }

    //

    /**
     * Return the current high scores.
     * The list is sorted such that the highest
     * scores come first.
     *
     * @return List.
     */
    public List<ScoreInfo> getHighScores() {
        return this.tableList;
    }


    /**
     * gets the rank of a certain score.
     *
     * @param score .
     * @return int .
     */
    public int getRank(int score) {
        //einat
        if (this.tableList.isEmpty()) {
            return 1;
        }
        int len = this.tableList.size();
        for (int i = 0; i < len; i++) {
            if (this.tableList.get(i).getScore() < score) {
                return i + 1;
            }
        }
        return len + 1;

    }


    /**
     * Clears the table.
     */
    public void clear() {
        this.tableList.clear();
    }


    /**
     * Load table data from file.
     * Current table data is cleared.
     *
     * @param filename .
     * @throws IOException .
     */
    public void load(File filename) throws IOException {

        HighScoresTable scoresTableFile = loadFromFile(filename);
        if (scoresTableFile != null) {
            this.tableList = scoresTableFile.getHighScores();
        } else {
            throw new IOException("Error: cannot read the file.");
        }

    }


    /**
     * Save table data to the specified file.
     *
     * @param filename .
     * @throws IOException .
     */
    public void save(File filename) throws IOException {

        FileOutputStream fileOutputS = null;
        ObjectOutputStream outputS = null;
        try {
            fileOutputS = new FileOutputStream(filename);
            outputS = new ObjectOutputStream(fileOutputS);
            outputS.writeObject(this);
        } catch (IOException exception) {
            System.err.println("Error: cannot save the object.");
        } finally {
            try {
                if (outputS != null) {
                    outputS.close();
                }
            } catch (IOException exception) {
                System.err.println("Error: cannot save the file: " + filename);
            }
        }
    }


    /**
     * Read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     *
     * @param filename .
     * @return HighScoresTable.
     */
    public static HighScoresTable loadFromFile(File filename) {
        ObjectInputStream objInputS = null;
        HighScoresTable tableFromFile;
        try {
            objInputS = new ObjectInputStream(new FileInputStream(filename));
            tableFromFile = (HighScoresTable) objInputS.readObject();

        } catch (ClassNotFoundException exception) {
            // cannot find the class
            System.err.println("Error: cannot find class.");
            return null;
        } catch (FileNotFoundException exception) {
            System.err.println("Error: cannot find the file: " + filename);
            return null;
        } catch (IOException exception) {
            System.err.println("Error: a problem has occured");
            exception.printStackTrace(System.err);
            return null;
        } finally {
            try {
                if (objInputS != null) {
                    objInputS.close();
                }
            } catch (IOException exception) {
                System.err.println("Error: failed closing file: " + filename);
            }
        }
        return tableFromFile;
    }
}
