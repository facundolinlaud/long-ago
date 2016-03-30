package com.facundolinlaud.supergame.ui.view.inventory;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

/**
 * Created by facundo on 3/30/16.
 */
public class InventoryInputListener extends InputListener {

    private Window window;

    public InventoryInputListener(Window window) {
        this.window = window;
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        if(Input.Keys.I == keycode)
            window.setVisible(!window.isVisible());

        return super.keyDown(event, keycode);
    }
}
