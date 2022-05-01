import biuoop.DrawSurface;

import java.util.List;
import java.util.ArrayList;

/**
 * SpriteCollection : create ball with canter radios and color.
 */
public class SpriteCollection {
    //declare new list of sprites.
    private List<Sprite> listSpriteCollection = new ArrayList<>();

    /**
     * addSprite : add sprite to the list.
     *
     * @param s sprite.
     */
    public void addSprite(Sprite s) {
        listSpriteCollection.add(s);
    }
    /**
     * getList : add sprite to the list.
     *
     * @return  List<Sprite>.
     */
    public List<Sprite> getList() {
        return listSpriteCollection;
    }
    /**
     * removeSprite : remove Sprite from a list.
     *
     * @param s sprite.
     */
    public void removeSprite(Sprite s) {
        this.listSpriteCollection.remove(s);
    }

    /**
     * notifyAllTimePassed :  call timePassed() on all sprites.
     * @param dt double
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < this.listSpriteCollection.size(); i++) {
            this.listSpriteCollection.get(i).timePassed(dt);
        }
    }

    /**
     * drawAllOn : call drawOn(d) on all sprites..
     *
     * @param d DrawSurface.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.listSpriteCollection.size(); i++) {
            this.listSpriteCollection.get(i).drawOn(d);
        }
    }
}
