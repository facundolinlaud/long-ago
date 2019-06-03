package com.facundolinlaud.supergame.components.items;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by facundo on 3/26/16.
 */
public class ItemComponent implements Component {
    public String name;
    public TextureRegion picture;

    public ItemComponent(String name, TextureRegion picture) {
        this.name = name;
        this.picture = picture;
    }
}
