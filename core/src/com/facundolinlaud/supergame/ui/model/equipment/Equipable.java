package com.facundolinlaud.supergame.ui.model.equipment;

import com.facundolinlaud.supergame.utils.WearType;

/**
 * Created by facundo on 4/2/16.
 */
public class Equipable {
    private WearType wearType;
    private int attack;
    private int defense;

    public Equipable(WearType wearType, int attack, int defense) {
        this.wearType = wearType;
        this.attack = attack;
        this.defense = defense;
    }

    public WearType getWearType() {
        return wearType;
    }

    public void setWearType(WearType wearType) {
        this.wearType = wearType;
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
