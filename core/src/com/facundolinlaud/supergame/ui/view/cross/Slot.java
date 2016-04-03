package com.facundolinlaud.supergame.ui.view.cross;

import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.facundolinlaud.supergame.ui.model.Item;

/**
 * Created by facundo on 4/2/16.
 */
public abstract class Slot extends Stack {
    public abstract Item getItem();

    public abstract void setItem(Item item);

    public abstract void clearItem();
}
