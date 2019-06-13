package com.facundolinlaud.supergame.ui.controller.impl;

import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.ui.controller.ProfileUIController;
import com.facundolinlaud.supergame.ui.view.OverlayUI;

/**
 * Created by facundo on 3/29/16.
 */
public class ProfileUIControllerImpl implements ProfileUIController {

    private OverlayUI overlayUI;

    public ProfileUIControllerImpl(OverlayUI overlayUI) {
        this.overlayUI = overlayUI;
    }

    @Override
    public void setHealth(float health) {
        overlayUI.setHealth(health);
    }

    @Override
    public void setFPS(int fps) {
        overlayUI.setFPS(fps);
    }

    @Override
    public void setPosition(Vector2 position) {
        overlayUI.setPosition(position);
    }

    @Override
    public void setBodyPosition(Vector2 position) {
        overlayUI.setBodyPosition(position);
    }
}
