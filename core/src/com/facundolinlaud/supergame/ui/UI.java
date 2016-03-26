package com.facundolinlaud.supergame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by facundo on 3/25/16.
 */
public abstract class UI {
    protected Table root;
    protected Skin skin;
    protected static boolean isDebug;

    static {
        isDebug = true;
    }

    public UI(Table root, Skin skin) {
        this.root = root;
        this.skin = skin;
    }
}
