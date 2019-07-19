package com.facundolinlaud.supergame.ui.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.facundolinlaud.supergame.components.items.ItemComponent;
import com.facundolinlaud.supergame.model.item.Rarity;
import com.facundolinlaud.supergame.ui.model.equipment.Equipable;
import com.facundolinlaud.supergame.ui.model.inventory.Invented;

/**
 * Created by facundo on 3/30/16.
 */
public class Item {
    private String name;
    private Sprite picture;
    private int marketValue;
    private Equipable equipable;
    private Invented invented;
    private Rarity rarity;

    public Item(ItemComponent component, Equipable equipable, Invented invented) {
        this.name = component.getName();
        this.picture = component.getPicture();
        this.marketValue = component.getMarketValue();
        this.equipable = equipable;
        this.invented = invented;
        this.rarity = component.getRarity();
    }

    public Item(ItemComponent component, Invented invented) {
        this(component, null, invented);
    }

    public Item(ItemComponent component, Equipable equipable) {
        this(component, equipable, null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sprite getPicture() {
        return picture;
    }

    public int getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(int marketValue) {
        this.marketValue = marketValue;
    }

    public void setPicture(Sprite picture) {
        this.picture = picture;
    }

    public Equipable getEquipable() {
        return equipable;
    }

    public void setEquipable(Equipable equipable) {
        this.equipable = equipable;
    }

    public Invented getInvented() {
        return invented;
    }

    public void setInvented(Invented invented) {
        this.invented = invented;
    }

    public boolean isEquipable(){
        return !(equipable == null);
    }

    public boolean isInvented(){
        return !(invented == null);
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }
}
