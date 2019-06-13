package com.facundolinlaud.supergame.components.sprite;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.facundolinlaud.supergame.domain.Sprite;

/**
 * Created by facundo on 26/7/16.
 */
public class StackableSpriteComponent implements Component {
    private Sprite sprite;

    public StackableSpriteComponent(Sprite sprite) {
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Texture getTexture() {
        return sprite.getTexture();
    }

    public int getSize() {
        return sprite.getSize();
    }

    public int getStartingIndexAtSpriteSheet() {
        return sprite.getStartingIndexAtSpriteSheet();
    }
}
