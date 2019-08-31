package com.facundolinlaud.supergame.dto.agent;

public class Attributes {
    private int strength;
    private int agility;
    private int stamina;
    private int intelligence;

    public Attributes() {
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    @Override
    public String toString() {
        return "Attributes{" +
                "strength=" + strength +
                ", agility=" + agility +
                ", stamina=" + stamina +
                ", intelligence=" + intelligence +
                '}';
    }
}
