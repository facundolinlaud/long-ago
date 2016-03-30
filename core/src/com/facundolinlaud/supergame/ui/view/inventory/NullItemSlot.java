package com.facundolinlaud.supergame.ui.view.inventory;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.facundolinlaud.supergame.ui.view.utils.Themes;

/**
 * Created by facundo on 3/30/16.
 */
public class NullItemSlot extends ImageButton {
    public NullItemSlot(Skin skin) {
        super(skin, Themes.SLOT.toString());
    }
}
