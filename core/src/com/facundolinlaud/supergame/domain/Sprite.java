package com.facundolinlaud.supergame.domain;

import com.badlogic.gdx.graphics.Texture;
import com.facundolinlaud.supergame.model.sprite.SpriteModel;

public class Sprite {
    private Texture texture;
    private SpriteModel model;

    public Sprite(Texture texture, SpriteModel model) {
        this.texture = texture;
        this.model = model;
    }

    public Texture getTexture() {
        return texture;
    }

    public int getSize() {
        return model.getRegionSize();
    }

    public int getStartingIndexAtSpriteSheet() {
        return model.getStartingIndexAtSpriteSheet();
    }
}
