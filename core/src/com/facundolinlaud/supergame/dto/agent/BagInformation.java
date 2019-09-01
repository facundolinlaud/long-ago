package com.facundolinlaud.supergame.dto.agent;

import java.util.List;

public class BagInformation {
    private List<Integer> bag;
    private int gold;

    public List<Integer> getBag() {
        return bag;
    }

    public void setBag(List<Integer> bag) {
        this.bag = bag;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
