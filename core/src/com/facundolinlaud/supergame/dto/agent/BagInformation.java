package com.facundolinlaud.supergame.dto.agent;

import java.util.List;

public class BagInformation {
    private List<String> bag;
    private int gold;

    public List<String> getBag() {
        return bag;
    }

    public void setBag(List<String> bag) {
        this.bag = bag;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
