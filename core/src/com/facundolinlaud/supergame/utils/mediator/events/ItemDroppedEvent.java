package com.facundolinlaud.supergame.utils.mediator.events;

/**
 * Created by facundo on 3/27/16.
 */
public class ItemDroppedEvent extends Event {
    private int itemIndex;

    public ItemDroppedEvent(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    public int getItemIndex() {
        return itemIndex;
    }
}
