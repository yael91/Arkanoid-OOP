import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Menu animation.
 *
 * @param <T> the type parameter
 */
public class MenuAnimation<T> implements Menu<T> {
    private ArrayList<Selection<Menu<T>>> subMenus;
    private List<Selection<T>> selectList;
    private String title;
    private T status;
    private boolean shouldStop;
    private KeyboardSensor k;


    /**
     * Instantiates a new Menu animation.
     *
     * @param title          the title
     * @param keyboardSensor the keyboard sensor
     */
    public MenuAnimation(String title, KeyboardSensor keyboardSensor) {
        selectList = new ArrayList<>();
        this.title = title;
        this.status = null;
        this.shouldStop = false;
        this.k = keyboardSensor;
        this.subMenus = new ArrayList<Selection<Menu<T>>>();
    }

    /**
     * addSelection  - do one frame.
     *
     * @param key       DrawSurface.
     * @param message string
     * @param returnVal T
     */
    public void addSelection(String key, String message, T returnVal) {
        Selection<T> select = new Selection<>(key, message, returnVal);
        selectList.add(select);
    }

    /**
     * getStatus  - do one frame.
     *
     * @return T
     */
    @Override
    public T getStatus() {
        return status;
    }

    /**
     * doOneFrame  - do one frame.
     *
     * @param d  DrawSurface.
     * @param dt double
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.lightGray);
        d.fillRectangle(0, 0, 800, 800);
        int i = 100;
        d.setColor(Color.BLACK);
        d.drawText(50, 50, "Arkanoid", 45);
        for (Selection<T> select : selectList) {
            d.setColor(Color.BLACK);
            d.drawText(160, i, "(" + select.getKey() + ")", 30);
            d.drawText(200, i, select.getMessage(), 30);
            i += 50;
            if ((k.isPressed(select.getKey()))) {
                this.status = select.getReturnVal();
            }
        }

    }

    /**
     * shouldStop returns boolean.
     *
     * @return as above.
     */
    @Override
    public boolean shouldStop() {
        for (Selection<T> select : selectList) {
            if (this.k.isPressed(select.getKey())) {
                status = select.getReturnVal();
                return true;
            }
        }
        return shouldStop;
    }
}





