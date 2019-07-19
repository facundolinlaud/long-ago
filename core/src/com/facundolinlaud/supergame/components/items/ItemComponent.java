package com.facundolinlaud.supergame.components.items;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.facundolinlaud.supergame.factory.TextureFactory;
import com.facundolinlaud.supergame.model.item.Item;
import com.facundolinlaud.supergame.model.item.Rarity;

/**
 * Created by facundo on 3/26/16.
 */
public class ItemComponent implements Component {
    private String name;
    private Sprite picture;
    private int marketValue;
    private float weight;
    private Rarity rarity;

    public ItemComponent(Item model) {
        this.name = model.getName();
        this.picture = TextureFactory.getSprite(model.getPicture());
        this.marketValue = model.getMarketValue();
        this.weight = model.getWeight();
        this.rarity = model.getRarity();
    }

    public String getName() {
        return name;
    }

    public Sprite getPicture() {
        return picture;
    }

    public int getMarketValue() {
        return marketValue;
    }

    public float getWeight() {
        return weight;
    }

    public Rarity getRarity() {
        return rarity;
    }
}
