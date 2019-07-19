package com.facundolinlaud.supergame.ui.model.equipment;

import com.facundolinlaud.supergame.components.items.EquipableComponent;
import com.facundolinlaud.supergame.model.equip.EquipSlot;
import com.facundolinlaud.supergame.model.equip.EquipType;

/**
 * Created by facundo on 4/2/16.
 */
public class Equipable {
    private EquipSlot equipSlot;
    private EquipType equipType;
    private int attack;
    private int defense;

    public Equipable(EquipableComponent component) {
        this.equipSlot = component.getEquipSlot();
        this.equipType = component.getEquipType();
        this.attack = component.getAttack();
        this.defense = component.getDefense();
    }

    public EquipSlot getEquipSlot() {
        return equipSlot;
    }

    public EquipType getEquipType() {
        return equipType;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }
}
