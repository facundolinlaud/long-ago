package com.facundolinlaud.supergame.utils.events;

import com.facundolinlaud.supergame.ui.model.Item;

/**
 * Created by facundo on 4/2/16.
 */
public class UnequipItemEvent extends Event {
    private Item item;

    public UnequipItemEvent(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
