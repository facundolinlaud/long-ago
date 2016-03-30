package com.facundolinlaud.supergame.ui.services.impl;

import com.facundolinlaud.supergame.ui.services.ProfileUIService;
import com.facundolinlaud.supergame.ui.view.ProfileUI;

/**
 * Created by facundo on 3/29/16.
 */
public class ProfileUIServiceImpl implements ProfileUIService {

    private ProfileUI profileUI;

    public ProfileUIServiceImpl(ProfileUI profileUI) {
        this.profileUI = profileUI;
    }

    @Override
    public void setHealth(float health) {
        this.profileUI.setHealth(health);
    }

    @Override
    public void setFPS(int fps) {
        this.profileUI.setFPS(fps);
    }
}
