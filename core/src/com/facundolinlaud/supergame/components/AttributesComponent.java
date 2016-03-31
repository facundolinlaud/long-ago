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
    private static final int INITIAL_VALUE = 1;

    public int agility;
    public int strength;
    public int intelligence;
    public int stamina;

    public AttributesComponent() {
        this.agility = INITIAL_VALUE;
        this.strength = INITIAL_VALUE;
        this.intelligence = INITIAL_VALUE;
        this.stamina = INITIAL_VALUE;
    }

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
}
