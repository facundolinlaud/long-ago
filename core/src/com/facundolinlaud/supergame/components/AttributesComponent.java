package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by facundo on 3/30/16.
 */
public class AttributesComponent implements Component {
    public static final String AGILITY = "Agility";
    public static final String STRENGTH = "Strength";
    public static final String INTELLIGENCE = "Intelligence";
    public static final String STAMINA = "Stamina";

    private int agility;
    private int strength;
    private int intelligence;
    private int stamina;

    public AttributesComponent(int agility, int strength, int intelligence, int stamina) {
        this.agility = agility;
        this.strength = strength;
        this.intelligence = intelligence;
        this.stamina = stamina;
    }

    public void add(String attributeName, int value){
        switch(attributeName){
            case AGILITY:
                agility += value;
                break;
            case STRENGTH:
                strength += value;
                break;
            case INTELLIGENCE:
                intelligence += value;
                break;
            case STAMINA:
                stamina += value;
                break;
        }
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }
}
