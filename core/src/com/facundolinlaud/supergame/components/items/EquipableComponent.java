package com.facundolinlaud.supergame.components.items;

import com.badlogic.ashley.core.Component;
import com.facundolinlaud.supergame.utils.WearType;

/**
 * Created by facundo on 3/31/16.
 */
public class EquipableComponent implements Component {
    private static final int DEFAULT_ATTACK = 1;
    private static final int DEFAULT_DEFENSE = 1;

    public int attack;
    public int defense;

    public EquipableComponent() {
        this.attack = DEFAULT_ATTACK;
        this.defense = DEFAULT_DEFENSE;
    }

    public EquipableComponent(int attack, int defense) {
        this.attack = attack;
        this.defense = defense;
    }
}
