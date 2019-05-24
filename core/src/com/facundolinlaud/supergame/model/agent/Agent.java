package com.facundolinlaud.supergame.model.agent;

import com.facundolinlaud.supergame.model.equip.EquipSlot;

import java.util.Map;

public class Agent {
    private int attack;
    private int defense;
    private int health;
    private float velocity;
    private float viewDistance;
    private Map<EquipSlot, String> body;
    private Map<EquipSlot, Integer> equipment;

    public Agent() {
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public float getViewDistance() {
        return viewDistance;
    }

    public void setViewDistance(float viewDistance) {
        this.viewDistance = viewDistance;
    }

    public Map<EquipSlot, String> getBody() {
        return body;
    }

    public void setBody(Map<EquipSlot, String> body) {
        this.body = body;
    }

    public Map<EquipSlot, Integer> getEquipment() {
        return equipment;
    }

    public void setEquipment(Map<EquipSlot, Integer> equipment) {
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "attack=" + attack +
                ", defense=" + defense +
                ", health=" + health +
                ", velocity=" + velocity +
                ", body=" + body +
                ", equipment=" + equipment +
                '}';
    }
}
