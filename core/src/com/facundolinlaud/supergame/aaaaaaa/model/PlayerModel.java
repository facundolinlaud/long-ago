package com.facundolinlaud.supergame.aaaaaaa.model;

import com.facundolinlaud.supergame.model.WearType;

import java.util.List;
import java.util.Map;

/**
 * Created by facundo on 27/7/16.
 */
public class PlayerModel {
    private int x;
    private int y;
    private float velocity;
    private Map<WearType, String> body;
    private Map<WearType, ItemModel> equipment;
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

    public Map<WearType, String> getBody() {
        return body;
    }

    public void setBody(Map<WearType, String> body) {
        this.body = body;
    }

    public Map<WearType, ItemModel> getEquipment() {
        return equipment;
    }

    public void setEquipment(Map<WearType, ItemModel> equipment) {
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
