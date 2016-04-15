package com.facundolinlaud.supergame.utils.events;

import com.facundolinlaud.supergame.ui.model.Item;

/**
 * Created by facundo on 4/3/16.
 */
public class EquipItemEvent extends Event {
    private Item item;

    public EquipItemEvent(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
