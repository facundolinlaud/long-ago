package com.facundolinlaud.supergame.model.item;

public class Item {
    private String name;
    private String texture;
    private String picture;

    private float weight;
    private float marketValue;

    private EquipmentInformation equipmentInformation;

    public Item() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
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

    public float getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(float marketValue) {
        this.marketValue = marketValue;
    }

    public EquipmentInformation getEquipmentInformation() {
        return equipmentInformation;
    }

    public void setEquipmentInformation(EquipmentInformation equipmentInformation) {
        this.equipmentInformation = equipmentInformation;
    }

    public boolean isEquipable(){
        return this.equipmentInformation != null;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", texture='" + texture + '\'' +
                ", picture='" + picture + '\'' +
                ", weight=" + weight +
                ", marketValue=" + marketValue +
                ", equipmentInformation=" + equipmentInformation +
                '}';
    }
}
