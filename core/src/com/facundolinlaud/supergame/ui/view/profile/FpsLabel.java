package com.facundolinlaud.supergame.ui.view.profile;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.facundolinlaud.supergame.ui.view.utils.Themes;

/**
 * Created by facundo on 3/30/16.
 */
public class FpsLabel extends Label {
    private static final String TEXT_PREFIX = "FPS: ";

    public FpsLabel(Skin skin) {
        super(TEXT_PREFIX, skin, Themes.DEFAULT.toString());
    }

    public void setFPS(int fps){
        setText(TEXT_PREFIX + fps);
    }
}
