/**
 * The type Score info.
 */
public class ScoreInfo implements Comparable<ScoreInfo> {
    private String stringName;
    private int score;

    /**
     * Instantiates a new Score info.
     *
     * @param name  the name
     * @param score the score
     */
    public ScoreInfo(String name, int score) {
        this.stringName = name;
        this.score = score;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.stringName;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return this.score;
    }

    @Override
    public int compareTo(ScoreInfo o) {
        if (getScore() < o.getScore()) {
            return 1;
        }
        if (getScore() > o.getScore()) {
            return -1;
        }
        return 0;
    }
}
