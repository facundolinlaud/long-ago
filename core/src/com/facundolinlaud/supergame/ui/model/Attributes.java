package com.facundolinlaud.supergame.ui.model;

import com.facundolinlaud.supergame.components.AttributesComponent;

import java.util.Arrays;
import java.util.List;

/**
 * Created by facundo on 3/31/16.
 */
public class Attributes {
    public static final List<String> availableAttributes;

    private int agility;
    private int strength;
    private int intelligence;
    private int stamina;

    static {
        availableAttributes = Arrays.asList(
                AttributesComponent.AGILITY,
                AttributesComponent.STRENGTH,
                AttributesComponent.INTELLIGENCE,
                AttributesComponent.STAMINA);
    }

    public Attributes(int agility, int strength, int intelligence, int stamina) {
        this.agility = agility;
        this.strength = strength;
        this.intelligence = intelligence;
        this.stamina = stamina;

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

    @Override
    public String toString() {
        return "Attributes{" +
                "agility=" + agility +
                ", strength=" + strength +
                ", intelligence=" + intelligence +
                ", stamina=" + stamina +
                '}';
    }

    public List<Integer> getAsList(){
        return Arrays.asList(agility, strength, intelligence, stamina);
    }
}
