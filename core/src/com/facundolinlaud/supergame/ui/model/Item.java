package com.facundolinlaud.supergame.ui.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.facundolinlaud.supergame.ui.model.equipment.Equipable;
import com.facundolinlaud.supergame.ui.model.inventory.Invented;

/**
 * Created by facundo on 3/30/16.
 */
public class Item {
    private String name;
    private TextureRegion picture;
    private Equipable equipable;
    private Invented invented;

    public Item(String name, TextureRegion picture, Invented invented) {
        this.name = name;
        this.picture = picture;
        this.equipable = null;
        this.invented = invented;
    }

    public Item(String name, TextureRegion picture, Equipable equipable) {
        this.name = name;
        this.picture = picture;
        this.equipable = equipable;
        this.invented = null;
    }

    public Item(String name, TextureRegion picture, Equipable equipable, Invented invented) {
        this.name = name;
        this.picture = picture;
        this.equipable = equipable;
        this.invented = invented;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TextureRegion getPicture() {
        return picture;
    }

    public void setPicture(TextureRegion picture) {
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
}
