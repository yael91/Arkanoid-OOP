// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

//package ac.biu.oop.game.arkanoid;

//import ac.biu.oop.game.animation.Animation;
//import ac.biu.oop.game.core.Counter;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * EndScreen: shows an endscreen according to the output.
 *
 */
public class EndScreen implements Animation {
    //declarations.
    private Counter score;
    private KeyboardSensor keyboardSensor;
    private boolean winning;
    private boolean stop;

    /**
     * EndScreen constructor : restores start and end for the Line.
     *
     * @param scoreCounter   Counter for score.
     * @param keyboardSensor KeyboardSensor.
     * @param won            boolean.
     */
    public EndScreen(Counter scoreCounter, KeyboardSensor keyboardSensor, boolean won) {
        this.score = scoreCounter;
        this.keyboardSensor = keyboardSensor;
        this.winning = won;
    }

    /**
     * shouldStop() : return boolean.
     *
     * @return stop : boolean.
     */
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * doOneFrame : the function make one frame of an endscreen.
     *
     * @param d DrawSurface.
     * @param dt double
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.YELLOW);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        //if won.
        String message = "You Win! Your score is: ";
        //if lost.
        if (!this.winning) {
            message = "Game Over. Your score is: ";
        }
        d.setColor(Color.BLACK);
        d.drawText(40, 150, message + String.valueOf(this.score.getValue()), 38);
        if (this.winning) {
            d.setColor(Color.YELLOW);
        } else {
            d.setColor(Color.RED);
        }

        String msg2 = "Press space to continue";
        d.setColor(Color.BLACK);
        d.drawText(200, 501, msg2, 27);
        //if the player want to exit.
        /*if (this.keyboardSensor.isPressed("space")) {
            this.stop = true;
        }*/
    }
}