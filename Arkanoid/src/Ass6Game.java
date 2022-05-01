
import biuoop.GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Ass5Game : the method runs ang initialize a game.
 */
public class Ass6Game {
    private static GUI gui;
    private static HighScoresTable scores;

    /**
     * Gets gui.
     *
     * @return the gui
     */
    public static GUI getGui() {
        return gui;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner runner = new AnimationRunner(60, gui);
        scores = new HighScoresTable(10);
        //create an array of levels.
        AnimationRunner animationRunner = new AnimationRunner(60, gui);
        while (true) {

            //run the game flow.
            Task<Void> runLev = new Task<Void>() {
                @Override
                public Void run() {
                    List<LevelInformation> levels = null;
                    try {
                        levels = getLevels(animationRunner);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (levels.isEmpty()) {
                        levels.add(new DirectHit(800, 600));
                        levels.add(new WideEasy(800, 600));
                        levels.add(new FinalFour(800, 600));
                    }
                    GameFlow flow = new GameFlow(runner, getGui().getKeyboardSensor(), scores);
                    flow.runLevels(levels);
                    animationRunner.run(new KeyPressStoppableAnimation(getGui().getKeyboardSensor(), "space",
                            new HighScoresAnimation(scores, getGui().getKeyboardSensor())));
                    return null;
                }
            };

            Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Main menu", getGui().getKeyboardSensor());
            HighScoresAnimation hsa = new HighScoresAnimation(scores, getGui().getKeyboardSensor());
            KeyPressStoppableAnimation ksa = new KeyPressStoppableAnimation(getGui().getKeyboardSensor(), "space", hsa);
            menu.addSelection("h", "High scores", new ShowHiScoresTask(animationRunner, ksa));
            menu.addSelection("s", "Start game", runLev);
            menu.addSelection("q", "Quit", new QuitTask());
            boolean b = true;

            animationRunner.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            task.run();
        }
    }

    /**
     * The entry point of application.
     *
     * @param animationRunner AnimationRunner
     * @return list
     * @throws IOException the io exception
     */
    private static List<LevelInformation> getLevels(AnimationRunner animationRunner) throws IOException {
        LevelSpecificationReader levelReader = new LevelSpecificationReader();
        java.io.Reader reader = null;
        try {
            //create a file;
            ClassLoader classLoader = animationRunner.getClass().getClassLoader();
            File file = new File(classLoader.getResource("level_sets.txt").getFile());
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        LevelSetsReader levelSetsReader = new LevelSetsReader();
        List<LevelSet> levelsSets = levelSetsReader.getLevelSets(reader);
        Menu<Task<Void>> levelsMenu = new MenuAnimation<Task<Void>>("Main menu", getGui().getKeyboardSensor());
        for (LevelSet levelSet : levelsSets) {
            levelsMenu.addSelection(levelSet.getSymbol(), levelSet.getName(), new LevelSetTask(levelSet.getFileName()));
        }
        animationRunner.run(levelsMenu);
        // wait for user selection
        LevelSetTask levelSetTask = (LevelSetTask) levelsMenu.getStatus();

        try {
            ClassLoader classLoader = animationRunner.getClass().getClassLoader();
            String filename = levelSetTask.getFileName();
            URL url = classLoader.getResource(filename);
            File file = new File(classLoader.getResource(filename).getFile());
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<LevelInformation> levels = levelReader.fromReader(reader);
        return levels;
    }
}

