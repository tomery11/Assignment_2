package menu;


import animation.AnimationRunner;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * MenuAnimation.
 *
 * @param <T> .
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class MenuAnimation<T> implements Menu<T> {


    private String menuTitle;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private boolean stop;
    private T status;
    private List<SelectOption<T>> option;


    /**
     * Constructor.
     *
     * @param menuTitle1 .
     * @param ks         .
     * @param ar         .
     */
    public MenuAnimation(String menuTitle1, KeyboardSensor ks, AnimationRunner ar) {
        this.menuTitle = menuTitle1;
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.option = new ArrayList<>();
        this.stop = false;

    }

    /**
     * @param key       String.
     * @param message   String.
     * @param returnVal T .
     */
    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.option.add(new SelectOption<T>(key, message, returnVal));
    }

    /**
     * @return status
     */
    @Override
    public T getStatus() {
        this.stop = false;
        return this.status;
    }

    /**
     * Creates a sub-menu.
     *
     * @param key     .
     * @param message .
     * @param subMenu .
     */
    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {

    }

    /**
     * this function runs one frame each time it is called.
     *
     * @param d .
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(58, 205, 255));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(24, 118, 150));
        d.fillRectangle(150, 120, 500, 400);
        d.setColor(Color.white);
        d.drawText(300, 100, this.menuTitle, 40);
        int j = 0;
        for (SelectOption<T> optionString : option) {
            d.drawText(180, 200 + j, optionString.getMessage(), 30);
            j += 120;
        }
        for (SelectOption<T> op : option) {
            if (this.keyboardSensor.isPressed(op.getKey())) {
                this.status = op.getReturnVal();
                this.stop = true;
            }
        }

    }

    /**
     * this is a method that tell when to get out of the while loop in the play one turn method .
     *
     * @return boolean.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * setter.
     *
     * @param stop1 .
     */
    public void setStop(boolean stop1) {
        this.stop = stop1;
    }
}
