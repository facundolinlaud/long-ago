package com.facundolinlaud.supergame.ui.model.equipment;

import com.facundolinlaud.supergame.model.EquipSlot;

/**
 * Created by facundo on 4/2/16.
 */
public class Equipable {
    private EquipSlot equipSlot;
    private int attack;
    private int defense;

    public Equipable(EquipSlot equipSlot, int attack, int defense) {
        this.equipSlot = equipSlot;
        this.attack = attack;
        this.defense = defense;
    }

    public EquipSlot getEquipSlot() {
        return equipSlot;
    }

    public void setEquipSlot(EquipSlot equipSlot) {
        this.equipSlot = equipSlot;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
