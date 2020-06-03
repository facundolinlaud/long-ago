package com.facundolinlaud.supergame.model.item;

public class Item {
    private String name;
    private String sprite;
    private String picture;
    private Rarity rarity;

    private float weight;
    private int marketValue;

    private EquipmentInformation equipmentInformation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(int marketValue) {
        this.marketValue = marketValue;
    }

    public EquipmentInformation getEquipmentInformation() {
        return equipmentInformation;
    }

    public void setEquipmentInformation(EquipmentInformation equipmentInformation) {
        this.equipmentInformation = equipmentInformation;
    }

    public boolean isEquipable() {
        return this.equipmentInformation != null;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }
}
