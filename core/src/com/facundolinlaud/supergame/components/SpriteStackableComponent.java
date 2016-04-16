package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by facundo on 4/15/16.
 */
public class SpriteStackableComponent implements Component {
    public boolean shouldRefresh;

    public SpriteStackableComponent() {
        this.shouldRefresh = true;
    }

    public void requestRefresh() {
        this.shouldRefresh = true;
    }
}
