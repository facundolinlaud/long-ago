package com.facundolinlaud.supergame.components.items;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by facundo on 3/26/16.
 */
public class ItemComponent implements Component {
    public String name;
    public Texture picture;

    public ItemComponent(String name, String picturePath) {
        this.name = name;
        this.picture = new Texture(picturePath);
    }
}
