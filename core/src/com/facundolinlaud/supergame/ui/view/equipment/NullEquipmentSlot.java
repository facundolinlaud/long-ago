package com.facundolinlaud.supergame.ui.view.equipment;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.facundolinlaud.supergame.ui.view.utils.Themes;

/**
 * Created by facundo on 4/2/16.
 */
public class NullEquipmentSlot extends ImageButton {
    public NullEquipmentSlot(Skin skin) {
        super(skin, Themes.SLOT.toString());
    }
}
