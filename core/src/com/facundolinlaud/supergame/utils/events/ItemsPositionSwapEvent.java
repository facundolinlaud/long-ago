package com.facundolinlaud.supergame.utils.events;

import com.facundolinlaud.supergame.ui.model.Item;

/**
 * Created by facundo on 3/30/16.
 */
public class ItemsPositionSwapEvent extends Event {
    private Item firstItem;
    private Item secondItem;

    public ItemsPositionSwapEvent(Item firstItem, Item secondItem) {
        this.firstItem = firstItem;
        this.secondItem = secondItem;
    }

    public Item getFirstItem() {
        return firstItem;
    }

    public Item getSecondItem() {
        return secondItem;
    }
}
