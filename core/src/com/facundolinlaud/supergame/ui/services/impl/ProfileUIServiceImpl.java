package com.facundolinlaud.supergame.ui.services.impl;

import com.facundolinlaud.supergame.ui.services.ProfileUIService;
import com.facundolinlaud.supergame.ui.view.OverlayUI;

/**
 * Created by facundo on 3/29/16.
 */
public class ProfileUIServiceImpl implements ProfileUIService {

    private OverlayUI overlayUI;

    public ProfileUIServiceImpl(OverlayUI overlayUI) {
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
