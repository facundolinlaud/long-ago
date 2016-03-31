package com.facundolinlaud.supergame.ui.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by facundo on 3/30/16.
 */
public class Item {
    private String name;
    private float weight;
    private int positionInBag;
    private TextureRegion textureRegion;

    public Item(String name, float weight, int positionInBag, TextureRegion textureRegion) {
        this.name = name;
        this.weight = weight;
        this.positionInBag = positionInBag;
        this.textureRegion = textureRegion;
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

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
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
                ", textureRegion=" + textureRegion +
                '}';
    }
}
