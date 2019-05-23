package com.facundolinlaud.supergame.model.item;

import java.util.Map;

public class Items {
    private Map<Integer, Item> items;

    public Items() {
    }

    public Map<Integer, Item> getItems() {
        return items;
    }

    public void setItems(Map<Integer, Item> items) {
        this.items = items;
    }
}
