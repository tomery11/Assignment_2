package game;


import java.io.Serializable;

/**
 * A ScoreInfo class.
 * describes a GameLevel.
 *
 * @author Tomer Yona
 */

public class ScoreInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String scoreName;
    private int scoreNum;

    /**
     * constructor.
     * @param name .
     * @param score .
     */
    public ScoreInfo(String name, int score) {
        this.scoreName = name;
        this.scoreNum = score;
    }

    /**
     * getter.
     * @return .
     */
    public String getName() {
        return this.scoreName;
    }

    /**
     * getter.
     * @return .
     */
    public int getScore() {
        return this.scoreNum;
    }
}
