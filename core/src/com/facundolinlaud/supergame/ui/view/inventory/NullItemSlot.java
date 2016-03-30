package com.facundolinlaud.supergame.ui.view.inventory;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by facundo on 3/30/16.
 */
public class NullItemSlot extends ImageButton {
    private final static String THEME = "slot";

    public NullItemSlot(Skin skin) {
        super(skin, THEME);
    }
}
