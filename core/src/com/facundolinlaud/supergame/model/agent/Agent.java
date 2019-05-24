package com.facundolinlaud.supergame.model.agent;

import com.facundolinlaud.supergame.model.equip.EquipSlot;

import java.util.List;
import java.util.Map;

public class Agent {
    private int attack;
    private int defense;
    private int health;
    private float velocity;
    private float viewDistance;
    private Attributes attributes;
    private Map<EquipSlot, String> body;
    private Map<EquipSlot, Integer> equipment;
    private List<Integer> bag;

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

    public List<Integer> getBag() {
        return bag;
    }

    public void setBag(List<Integer> bag) {
        this.bag = bag;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "attack=" + attack +
                ", defense=" + defense +
                ", health=" + health +
                ", velocity=" + velocity +
                ", viewDistance=" + viewDistance +
                ", attributes=" + attributes +
                ", body=" + body +
                ", equipment=" + equipment +
                ", bag=" + bag +
                '}';
    }
}
