package com.facundolinlaud.supergame.model.entity;

import com.facundolinlaud.supergame.model.equip.EquipSlot;
import com.facundolinlaud.supergame.model.equip.EquipType;

/**
 * Created by facundo on 27/7/16.
 */
public class ItemModel {
    private String name;
    private String texture;
    private String picture;
    private boolean equipable;
    private EquipSlot equipSlot;
    private EquipType equipType;
    private int attack;
    private int defense;

    public ItemModel() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isEquipable() {
        return equipable;
    }

    public void setEquipable(boolean equipable) {
        this.equipable = equipable;
    }

    public EquipSlot getEquipSlot() {
        return equipSlot;
    }

    public void setEquipSlot(EquipSlot equipSlot) {
        this.equipSlot = equipSlot;
    }

    public EquipType getEquipType() {
        return equipType;
    }

    public void setEquipType(EquipType equipType) {
        this.equipType = equipType;
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
