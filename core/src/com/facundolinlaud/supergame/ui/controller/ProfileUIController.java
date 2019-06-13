package com.facundolinlaud.supergame.ui.controller;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by facundo on 3/29/16.
 */
public interface ProfileUIController {
    void setHealth(float health);

    void setFPS(int fps);

    void setPosition(Vector2 position);

    void setBodyPosition(Vector2 position);
}
