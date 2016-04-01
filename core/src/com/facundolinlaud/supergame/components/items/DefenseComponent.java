package com.facundolinlaud.supergame.components.items;

import com.badlogic.ashley.core.Component;

/**
 * Created by facundo on 3/31/16.
 */
public class DefenseComponent implements Component {
    private static final int DEFAULT_DEFENSE = 1;

    public int defense;

    public DefenseComponent() {
        this.defense = DEFAULT_DEFENSE;
    }

    public DefenseComponent(int defense) {
        this.defense = defense;
    }
}
