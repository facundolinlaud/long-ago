package com.facundolinlaud.supergame.service.impl;

import com.facundolinlaud.supergame.service.ProfileUIService;
import com.facundolinlaud.supergame.ui.ProfileUI;

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
