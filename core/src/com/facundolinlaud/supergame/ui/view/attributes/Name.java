package com.facundolinlaud.supergame.ui.view.attributes;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.facundolinlaud.supergame.ui.view.utils.Themes;

/**
 * Created by facundo on 3/31/16.
 */
public class Name extends Label {
    public Name(Skin skin, CharSequence attributeName) {
        super(attributeName, skin, Themes.DEFAULT.toString());
    }
}
