package com.facundolinlaud.supergame.ui.controller;

import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.ui.view.OverlayUI;

/**
 * Created by facundo on 3/29/16.
 */
public class ProfileUIController {

    private OverlayUI overlayUI;

    public ProfileUIController(OverlayUI overlayUI) {
        this.overlayUI = overlayUI;
    }

    public void setHealth(float health) {
        overlayUI.setHealth(health);
    }

    public void setFPS(int fps) {
        overlayUI.setFPS(fps);
    }

    public void setPosition(Vector2 position) {
        overlayUI.setPosition(position);
    }

    public void setBodyPosition(Vector2 position) {
        overlayUI.setBodyPosition(position);
    }
}
