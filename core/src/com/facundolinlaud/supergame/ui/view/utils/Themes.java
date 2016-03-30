package com.facundolinlaud.supergame.ui.view.utils;

/**
 * Created by facundo on 3/30/16.
 */
public enum Themes {
    DEFAULT("default"),
    CLASSIC("rpg"),
    SLOT("slot");

    private final String theme;

    Themes(final String theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return this.theme;
    }
}
