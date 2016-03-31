package com.facundolinlaud.supergame.ui.view.utils;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

/**
 * Created by facundo on 3/30/16.
 */
public class ToggleWindowListener extends InputListener {

    private Window window;
    private int keycode;

    public ToggleWindowListener(Window window, int keycode) {
        this.window = window;
        this.keycode = keycode;
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        if(this.keycode == keycode)
            window.setVisible(!window.isVisible());

        return super.keyDown(event, keycode);
    }
}
