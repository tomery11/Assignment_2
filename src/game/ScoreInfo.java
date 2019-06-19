package game;


import java.io.Serializable;

public class ScoreInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String scoreName;
    private int scoreNum;

    public ScoreInfo(String name, int score) {
        this.scoreName = name;
        this.scoreNum = score;
    }
    public String getName() {
        return this.scoreName;
    }
    public int getScore() {
        return this.scoreNum;
    }
}
