package com.facundolinlaud.supergame.components.items;

import com.badlogic.ashley.core.Component;
import com.facundolinlaud.supergame.model.EquipType;
import com.facundolinlaud.supergame.model.EquipSlot;

/**
 * Created by facundo on 3/31/16.
 */
public class EquipableComponent implements Component {
    private static final int DEFAULT_ATTACK = 1;
    private static final int DEFAULT_DEFENSE = 1;

    public EquipType equipType;
    public EquipSlot equipSlot;
    public int attack;
    public int defense;

    public EquipableComponent(EquipSlot equipSlot) {
        this.equipSlot = equipSlot;
        this.attack = DEFAULT_ATTACK;
        this.defense = DEFAULT_DEFENSE;
    }

    public EquipableComponent(EquipSlot equipSlot, int attack, int defense) {
        this.equipSlot = equipSlot;
        this.attack = attack;
        this.defense = defense;
    }

    public EquipableComponent(EquipSlot equipSlot, EquipType equipType, int attack, int defense) {
        this.equipSlot = equipSlot;
        this.equipType = equipType;
        this.attack = attack;
        this.defense = defense;
    }
}
