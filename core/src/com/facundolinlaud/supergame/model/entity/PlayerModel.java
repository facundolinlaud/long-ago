package com.facundolinlaud.supergame.model.entity;

import com.facundolinlaud.supergame.model.equip.EquipSlot;

import java.util.List;
import java.util.Map;

/**
 * Created by facundo on 27/7/16.
 */
public class PlayerModel {
    private int x;
    private int y;
    private float velocity;
    private Map<EquipSlot, String> body;
    private Map<EquipSlot, ItemModel> equipment;
    private List<ItemModel> inventory;

    public PlayerModel() {}

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public Map<EquipSlot, String> getBody() {
        return body;
    }

    public void setBody(Map<EquipSlot, String> body) {
        this.body = body;
    }

    public Map<EquipSlot, ItemModel> getEquipment() {
        return equipment;
    }

    public void setEquipment(Map<EquipSlot, ItemModel> equipment) {
        this.equipment = equipment;
    }

    public List<ItemModel> getInventory() {
        return inventory;
    }

    public void setInventory(List<ItemModel> inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "PlayerModel{" +
                "x=" + x +
                ", y=" + y +
                ", velocity=" + velocity +
                ", body=" + body +
                ", equipment=" + equipment +
                ", inventory=" + inventory +
                '}';
    }
}
