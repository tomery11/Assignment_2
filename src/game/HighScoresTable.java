package game;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


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
        //einat
        int rank = this.getRank(score.getScore());
        if (rank <= this.sizeOfTable) {
            this.tableList.add(rank - 1, score);
        }
        if (this.tableList.size() > this.sizeOfTable) {
            this.tableList.remove(this.tableList.size() - 1);
        }


        /* previous tomer
        this.tableList.add(score);
        this.added++;
        bubbleSort();
        */
    }

    /**
     * sort this list of score info
     */
    public void bubbleSort() {
        int n = this.sizeOfTable;
        if (this.added > 1) {
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (getHighScores().get(j).getScore() > getHighScores().get(j + 1).getScore()) {
                        // swap arr[j+1] and arr[i]
                        ScoreInfo temp = getHighScores().get(j);
                        getHighScores().set(j, getHighScores().get(j + 1));
                        getHighScores().set(j + 1, temp);

                    }
                }
            }
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

    // return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    //      be added to the list.
    public int getRank(int score) {
        //einat
        if(this.tableList.isEmpty()) {
            return 1;
        }
        int len = this.tableList.size();
        for(int i = 0; i < len; i++) {
            if(this.tableList.get(i).getScore() < score) {
                return i + 1;
            }
        }
        return len + 1;


        /* previous tomer
        int i;
        for (i = 0; i < this.tableList.size(); i++) {
            if (score > this.tableList.get(i).getScore()) {
                break;
            }
        }
        return i + 1;
        */
    }

    // Clears the table
    public void clear() {
        this.tableList.clear();
    }

    // Load table data from file.
    // Current table data is cleared.
    public void load(File filename) throws IOException {
        //einat
        HighScoresTable scoresTableFile = loadFromFile(filename);
        if(scoresTableFile != null) {
            this.tableList = scoresTableFile.getHighScores();
        } else {
            throw new IOException("Error: cannot read the file.");
        }
/*  previous tomer
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(filename.getName()));
            ScoreInfo scoreInfo = null;
            while ((scoreInfo = (ScoreInfo) objectInputStream.readObject()) != null) {
                this.add(scoreInfo);
            }

        } catch (FileNotFoundException e) {
            System.err.println("Unable to find file: " + filename.getName());

        } catch (ClassNotFoundException e) {
            System.err.println("Unable to find class for object in file: " + filename.getName());
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename.getName());
            }
        }
*/
        /*
        HighScoresTable scoresTableFile = loadFromFile(filename);
        if(scoresTableFile != null) {
            this.tableList = scoresTableFile.getHighScores();
        } else {
            throw new IOException("Error: cannot read the file.");
        }
        */
    }

    // Save table data to the specified file.
    public void save(File filename) throws IOException {
        //einat
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

        /* previous tomer
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename));
            for (ScoreInfo i : getHighScores()){
                objectOutputStream.writeObject(i);
            }
        } catch (EOFException e) {
            return;
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find file: " + filename.getName());
            return;
        } catch (IOException e) {
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
            return;
        }
        */


    }

    // Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty table is returned.
    public static HighScoresTable loadFromFile(File filename) {
        //einat
        ObjectInputStream objInputS = null;
        HighScoresTable tableFromFile;
        try {
            objInputS = new ObjectInputStream(new FileInputStream(filename));
            tableFromFile =(HighScoresTable) objInputS.readObject();

        } catch (ClassNotFoundException exception){
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



        /* previous tomer
        HighScoresTable emptyTable = new HighScoresTable(5);
        try {
            if (!filename.exists()) {
                return emptyTable;
            }
            emptyTable.load(filename);
        } catch (IOException e) {
            System.err.println("Failed closing file: " + filename.getName());
            return new HighScoresTable(5);
        }
        return emptyTable;
        */
        /*
        ObjectInputStream objInputS = null;
        HighScoresTable tableFromFile;
        try {
            objInputS = new ObjectInputStream(new FileInputStream(filename));
            tableFromFile =(HighScoresTable) objInputS.readObject();

        } catch (ClassNotFoundException exception){
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
        */
    }


    /**
     * print table.
     */
    public void printTable() {
        int count = 1;
        for (ScoreInfo score : this.getHighScores()) {
            System.out.println(count + ": " + "name: " + score.getName() + "\t" + "score: " + score.getScore());
            count++;
        }
    }
}
