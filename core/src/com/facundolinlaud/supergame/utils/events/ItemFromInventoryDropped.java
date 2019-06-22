package com.facundolinlaud.supergame.utils.events;

import com.facundolinlaud.supergame.ui.model.Item;

/**
 * Created by facundo on 3/27/16.
 */
public class ItemFromInventoryDropped extends Event {
    private Item item;

    public ItemFromInventoryDropped(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
