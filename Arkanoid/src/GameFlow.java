import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * GameFlow: makes the game runs influently.
 */
public class GameFlow {
    //declarations.
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter score;
    private HighScoresTable scores;

    /**
     * Constructor : reset the score and the game.
     *
     * @param ar : AnimationRunner.
     * @param ks : KeyboardSensor.
     * @param scores : KeyboardSensor.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, HighScoresTable scores) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = new Counter(0);
        this.scores = scores;
    }

    /**
     * runLevels : runs the levels of the game acording to input and result.
     *
     * @param levels : (List<LevelInformation>.
     * @return boolean
     */
    public boolean runLevels(List<LevelInformation> levels) {
        int flag = 0;
        GUI gui = Ass6Game.getGui();
        Counter lives = new Counter(7);
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.keyboardSensor, lives, this.score);

            level.initialize();
            //as long as the player do not fail.
            while (level.getBlocksCount() > 0 && lives.getValue() > 0) {
                level.playOneTurn();

            }
            //if the player failed.

            if (lives.getValue() <= 0) {
                flag = 1;
                // end screen.
                Animation lost = new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                        new EndScreen(this.score, this.keyboardSensor, false));
                while (!lost.shouldStop()) {
                    animationRunner.run(lost);
                }
                break;
            }
        }

        if (flag == 0) {
            //if the player won the game.
            animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space", new EndScreen(this.score,
                    this.keyboardSensor, true)));
        }
        DialogManager dialog = gui.getDialogManager();
        String name = dialog.showQuestionDialog("Name", "What is your name?", "");


        ScoreInfo si = new ScoreInfo(name, score.getValue());
        scores.add(si);
        return lives.getValue() > 0;
    }
}