package com.facundolinlaud.supergame.ui.view.cross;

import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.facundolinlaud.supergame.ui.model.Item;

/**
 * Created by facundo on 4/2/16.
 */
public abstract class Slot<T> extends Stack {
    public abstract T getContent();

    public abstract void setContent(T content);

    public abstract void clearContent();
}
