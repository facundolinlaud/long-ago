package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by facundo on 3/26/16.
 */
public class GathererComponent implements Component {
    private final static float DEFAULT_BAG_SIZE = 10f;

    public float bagSize;
    public List<Entity> items;

    public GathererComponent() {
        bagSize = DEFAULT_BAG_SIZE;
        items = new ArrayList<>();
    }

    public void addItem(Entity entity){
        items.add(entity);
        System.out.println(entity + " added to the bag");
    }
}
