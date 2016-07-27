package com.facundolinlaud.supergame.model.entity;

import com.facundolinlaud.supergame.model.WearType;

/**
 * Created by facundo on 27/7/16.
 */
public class ItemModel {
    private String name;
    private String texture;
    private String picture;
    private boolean equipable;
    private WearType wearType;
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
