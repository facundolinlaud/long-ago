package com.facundolinlaud.supergame.components.items;

import com.badlogic.ashley.core.Component;
import com.facundolinlaud.supergame.model.equip.EquipType;
import com.facundolinlaud.supergame.model.equip.EquipSlot;
import com.facundolinlaud.supergame.model.item.EquipmentInformation;

/**
 * Created by facundo on 3/31/16.
 */
public class EquipableComponent implements Component {
    private EquipType equipType;
    private EquipSlot equipSlot;
    private int attack;
    private int defense;

    public EquipableComponent(EquipmentInformation eq) {
        this.equipSlot = eq.getEquipSlot();
        this.equipType = eq.getEquipType();
        this.attack = eq.getAttack();
        this.defense = eq.getDefense();
    }

    public EquipType getEquipType() {
        return equipType;
    }

    public void setEquipType(EquipType equipType) {
        this.equipType = equipType;
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
