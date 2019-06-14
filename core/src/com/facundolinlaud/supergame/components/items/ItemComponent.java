package com.facundolinlaud.supergame.components.items;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by facundo on 3/26/16.
 */
public class ItemComponent implements Component {
    public String name;
    public Sprite picture;

    public ItemComponent(String name, Sprite picture) {
        this.name = name;
        this.picture = picture;
    }
}
