package com.facundolinlaud.supergame.ui.controller.impl;

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
}
