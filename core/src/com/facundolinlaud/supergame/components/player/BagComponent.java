package com.facundolinlaud.supergame.components.player;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by facundo on 3/26/16.
 */
public class BagComponent implements Component {
    public List<Entity> items;

    public BagComponent() {
        items = new ArrayList<>();
    }

    public BagComponent(List<Entity> items) {
        this.items = items;
    }

    public void addItem(Entity entity){
        items.add(entity);
        System.out.println(entity + " added to the bag");
    }
}
