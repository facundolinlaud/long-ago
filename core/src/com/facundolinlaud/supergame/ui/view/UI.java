package com.facundolinlaud.supergame.ui.view;

import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Created by facundo on 3/25/16.
 */
public interface UI<T extends Group> {
    T get();
}
