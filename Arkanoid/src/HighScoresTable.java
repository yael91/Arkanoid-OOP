import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * The type High scores table.
 */
public class HighScoresTable {
    /**
     * The Size of table.
     */
    private int sizeOfTable;
    private List<ScoreInfo> table;
    private File file;

    /**
     * Instantiates a new High scores table.
     *
     * @param size the size
     */
    public HighScoresTable(int size) {
        table = new ArrayList<>(size);
        this.sizeOfTable = size;
        //create file.
        file = new File("highscores.txt");
        if (file.exists()) {
            try {
                load(file);
            } catch (Exception e) {
                int i;
                i = 1;
            }
        }
    }

    /**
     * Add.
     *
     * @param score the score
     */
    // Add a high-score.
    public void add(ScoreInfo score) {
        if (table.size() == sizeOfTable) {
            table.remove(sizeOfTable - 1);
        }
        table.add(score);
        Collections.sort(table);
        try {
            save(file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Size int.
     *
     * @return the int
     */
    public int size() {
        return table.size();
    }

    /**
     * Gets list table.
     *
     * @return the list table
     */
    public List<ScoreInfo> getListTable() {
        return table;
    }

    /**
     * Gets high scores.
     *
     * @return the high scores
     */
    public List<ScoreInfo> getHighScores() {
        return this.table;
    }

    /**
     * Gets rank.
     *
     * @param score the score
     * @return the rank
     */
    public int getRank(int score) {
        if (table.isEmpty()) {
            return 1;
        }
        for (int i = 0; i < table.size(); i++) {
            if (table.get(i).getScore() > score) {
                return i - 1;
            }
        }
        if (sizeOfTable < table.size()) {
            return -1;
        }
        return 0;
    }

    /**
     * Clear.
     */
    public void clear() {
        table.clear();
    } // Clears the table


    /**
     * Load.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Load table data from file.
    // Current table data is cleared.
    public void load(File filename) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        char[] cbuf = new char[10000];
        br.read(cbuf);
        String s = new String(cbuf);
        String[] lines = s.split(System.getProperty("line.separator"));
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].trim().isEmpty()) {
                continue;
            }
            String[] lineContent = lines[i].split(":");
            ScoreInfo si = new ScoreInfo(lineContent[0], Integer.valueOf(lineContent[1]));
            add(si);
        }
        br.close();
    }

    /**
     * Save.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Save table data to the specified file.
    public void save(File filename) throws IOException {
        FileOutputStream f = new FileOutputStream(filename);
        BufferedOutputStream b = new BufferedOutputStream(f);
        Iterator<ScoreInfo> iter = table.iterator();
        while (iter.hasNext()) {
            ScoreInfo si = iter.next();
            b.write(si.getName().getBytes());
            b.write(":".getBytes());
            b.write(Integer.valueOf(si.getScore()).toString().getBytes());
            b.write(System.getProperty("line.separator").getBytes());
        }
        b.close();
    }

}
