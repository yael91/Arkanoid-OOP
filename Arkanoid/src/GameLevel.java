import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * GameLevel : create Game which has collidable objects, moving balls and a paddle.
 */
public class GameLevel implements Animation {
    //declarations.
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int BLOCKWIDTH = WIDTH / 16;
    private static final int BLOCKHEIGHT = HEIGHT / 30;

    public static final int INDICATOR_SIZE = 15;
    public static final int BORDER_SIZE = 25;


    private LevelInformation info;
    //restart variables.
    private SpriteCollection sprites;
    private GameEnvironment environment;

    private KeyboardSensor keyboard;
    private AnimationRunner runner;
    private boolean running;

    private Paddle padd;

    private Counter counterBlocks;
    private Counter counterBalls;
    private Counter score;
    private Counter lives;


    /**
     * GameLevel constructor .
     *
     * @param info     LevelInformation.
     * @param runner   Counter.
     * @param keyboard AnimationRunner.
     * @param lives    Counter.
     * @param score    Counter.
     */
    public GameLevel(LevelInformation info, AnimationRunner runner, KeyboardSensor keyboard,
                     Counter lives, Counter score) {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();

        this.info = info;
        this.score = score;
        this.runner = runner;
        this.keyboard = keyboard;

        counterBlocks = new Counter(0);
        counterBalls = new Counter(0);
        this.lives = lives;
    }

    /**
     * addCollidable : add colidable to the game environment.
     *
     * @param c Collidable object.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * removeCollidable : remove Collidable to the game environment.
     *
     * @param c Collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidables(c);
    }

    /**
     * addSprite: add sprite to the game environment.
     *
     * @param s Sprite object.
     */
    public void addSprite(Sprite s) {
        if (s != null) {
            this.sprites.addSprite(s);
        }
    }

    /**
     * removeSprite: remove Sprite from the game environment.
     *
     * @param s Sprite object.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * getEnvironment: get the game environment.
     *
     * @return environment.
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * generateRandomBall: creats random balls and velocity.
     *
     * @return : ball object.
     */
    public Ball generateRandomBall() {
        Random rnd = new Random();
        int x = rnd.nextInt(WIDTH);
        Ball ball = new Ball(new Point(WIDTH / 2, HEIGHT - BORDER_SIZE - HEIGHT / 50 - 20),
                5, Color.WHITE);
        ball.setVelocity(Velocity.fromAngleAndSpeed(rnd.nextInt(360), rnd.nextInt(3) + 4));
        return ball;
    }

    /**
     * loadBorders: load the corders of the game.
     */
    private void loadBorders() {
        Block[] blocks = new Block[4];
        // up
        blocks[0] = new Block(new Rectangle(new Point(0, INDICATOR_SIZE), WIDTH, BORDER_SIZE), Color.BLACK, 0);
        // left
        blocks[1] = new Block(new Rectangle(new Point(0, INDICATOR_SIZE), BORDER_SIZE, HEIGHT), Color.BLACK, 0);
        // down
        blocks[2] = new Block(new Rectangle(new Point(0, HEIGHT), WIDTH, BORDER_SIZE), Color.BLACK, 0);
        blocks[3] = new Block(new Rectangle(new Point(WIDTH - BORDER_SIZE, INDICATOR_SIZE), BORDER_SIZE,
                HEIGHT), Color.BLACK, 0);

        blocks[2].addHitListener(new BallRemover(this, counterBalls));
        for (Block b : blocks) {
            b.addToGame(this);
        }
    }

    /**
     * loadIndicators: load the Indicators of the game.
     */
    private void loadIndicators() {
        int width = WIDTH / 2;
        addSprite(new ScoreIndicator(new Rectangle(new Point(width - (width / 4), 0), width / 2,
                INDICATOR_SIZE + 5), score, info.levelName()));
        addSprite(new LivesIndicator(new Rectangle(new Point(0, 0), width / 2, INDICATOR_SIZE + 5),
                lives, info.levelName()));


    }

    /**
     * loadPaddle: load the paddle of the game.
     */
    private void loadPaddle() {
        int paddleHeight = HEIGHT / 50;
        Point point = new Point(WIDTH / 2 - info.paddleWidth() / 2, HEIGHT - BORDER_SIZE - paddleHeight);
        Rectangle rectangle = new Rectangle(point, info.paddleWidth(), paddleHeight);
        padd = new Paddle(rectangle, keyboard, info.paddleSpeed());
        padd.setBorders(BORDER_SIZE, WIDTH - BORDER_SIZE);
        padd.addToGame(this);
    }

    /**
     * loadBlocks: load the blocks of the game.
     */
    private void loadBlocks() {

        BlockRemover blockRemover = new BlockRemover(this, counterBlocks);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);

        //create blocks of the game.
        List<Block> blocks = info.blocks();
        for (Block b : blocks) {
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTrackingListener);
            b.addToGame(this);
        }
        counterBlocks.setCounter(blocks.size());

    }

    /**
     * initialize: initialize the game, reate the Blocks and Ball (and Paddle).
     * and add them to the game.
     */
    public void initialize() {
        addSprite(info.getBackground());
        loadBorders();
        loadBlocks();
        loadIndicators();

    }

    /**
     * generateBlocks: creates blocks of given order.
     *
     * @return : blocks: List<Block>.
     */
    public static List<Block> generateBlocks() {
        final int rows = 1;
        int cols = 12;
        //start X.
        final int startX = WIDTH - BORDER_SIZE;
        int startY = HEIGHT / 6;
        List<Block> blocks = new ArrayList<Block>();
        //array of colors.
        Color[] colors = new Color[]{Color.red, Color.magenta, Color.blue, Color.yellow, Color.green, Color.ORANGE};
        //reates blocks of given order. and set the numbers of hits.
        for (int i = 0; i < rows; ++i, --cols, startY += BLOCKHEIGHT) {
            for (int j = 0; j < cols; ++j) {
                Rectangle r = new Rectangle(new Point(startX - BLOCKWIDTH * (j + 1), startY),
                        BLOCKWIDTH, BLOCKHEIGHT);
                Block b = new Block(r, colors[i], 0);
                if (i == 0) {
                    b.setNumberHits(2);
                } else {
                    b.setNumberHits(1);
                }
                blocks.add(b);
            }
        }
        return blocks;
    }

    /**
     * getBlocksCount:  get count of blocks.
     *
     * @return int value of blocks remaining.
     */
    public int getBlocksCount() {
        return this.counterBlocks.getValue();
    }

    /**
     * shouldStop:  returns boolean.
     *
     * @return running.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * doOneFrame:  do one frame of the game level.
     *
     * @param d  DrawSurface
     * @param dt double
     */
    public void doOneFrame(DrawSurface d, double dt) {

        //draw the sprites.
        this.sprites.drawAllOn(d);
        //gui.show(d);
        this.sprites.notifyAllTimePassed(dt);

        if (keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }

        //creates the surface color.

        if (counterBlocks.getValue() <= 0) {
            score.increase(100);
            this.running = false;
        }
        if (counterBalls.getValue() <= 0) {
            lives.decrease(1);
            this.running = false;
        }
    }

    /**
     * createBallsOnTopOfPaddle: creates Balls On Top Of Paddle.
     */
    public void createBallsOnTopOfPaddle() {
        List<Velocity> vels = info.initialBallVelocities();
        for (Velocity v : vels) {
            Ball b = new Ball(new Point(WIDTH / 2, HEIGHT * 6 / 7 + 35), 5, Color.MAGENTA);
            b.setVelocity(v);
            b.addToGame(this);
        }
        counterBalls.setCounter(vels.size());
    }

    /**
     * playOnTurn:  Run the game -- start the animation loop..
     */
    public void playOneTurn() {
        loadPaddle();
        this.createBallsOnTopOfPaddle();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
        padd.removeFromGame(this);
    }

}
