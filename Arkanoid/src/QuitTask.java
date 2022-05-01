/**
 * The type Quit task.
 */
public class QuitTask implements Task<Void> {
    /**
     * run - The type Quit task.
     * @return null
     */
    @Override
    public Void run() {
        Ass6Game.getGui().close();
        System.exit(0);
        return null;
    }

}
