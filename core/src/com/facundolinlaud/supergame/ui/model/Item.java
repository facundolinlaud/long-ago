package com.facundolinlaud.supergame.ui.model;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by facundo on 3/30/16.
 */
public class Item {
    public static final int NO_BAG_POSITION = -1;

    private String name;
    private float weight;
    private int positionInBag;
    private Texture picture;

    public Item(String name, float weight, int positionInBag, Texture picture) {
        this.name = name;
        this.weight = weight;
        this.positionInBag = positionInBag;
        this.picture = picture;
    }

    public Item(String name, float weight, Texture picture) {
        this.name = name;
        this.weight = weight;
        this.positionInBag = NO_BAG_POSITION;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Texture getPicture() {
        return picture;
    }

    public void setPicture(Texture picture) {
        this.picture = picture;
    }

    public int getPositionInBag() {
        return positionInBag;
    }

    public void setPositionInBag(int positionInBag) {
        this.positionInBag = positionInBag;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", positionInBag=" + positionInBag +
                ", picture=" + picture +
                '}';
    }
}
