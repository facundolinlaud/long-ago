package com.facundolinlaud.supergame.components.items;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by facundo on 3/26/16.
 */
public class ItemComponent implements Component {
    private static int id = 0;

    public String name;
    public Texture picture;

    public ItemComponent() {
        this.name = "Item #" + id;
        id ++;
    }

    public ItemComponent(Texture picture) {
        this.name = "Item #" + id;
        this.picture = picture;
        id ++;
    }

    public ItemComponent(String name, String picturePath) {
        this.name = name;
        this.picture = new Texture(picturePath);
    }
}
