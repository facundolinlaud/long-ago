package com.facundolinlaud.supergame.model.sprite;

public class SpriteModel {
    private int regionSize;
    private int startingIndexAtSpriteSheet;

    public SpriteModel() {}

    public SpriteModel(int regionSize) {
        this.regionSize = regionSize;
    }

    public int getRegionSize() {
        return regionSize;
    }

    public void setRegionSize(int regionSize) {
        this.regionSize = regionSize;
    }

    public int getStartingIndexAtSpriteSheet() {
        return startingIndexAtSpriteSheet;
    }

    public void setStartingIndexAtSpriteSheet(int startingIndexAtSpriteSheet) {
        this.startingIndexAtSpriteSheet = startingIndexAtSpriteSheet;
    }
}
