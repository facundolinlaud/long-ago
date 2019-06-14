package com.facundolinlaud.supergame.components.sprite;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.facundolinlaud.supergame.domain.ComplexSprite;

/**
 * Created by facundo on 26/7/16.
 */
public class StackableSpriteComponent implements Component {
    private ComplexSprite complexSprite;

    public StackableSpriteComponent(ComplexSprite complexSprite) {
        this.complexSprite = complexSprite;
    }

    public ComplexSprite getComplexSprite() {
        return complexSprite;
    }

    public Texture getTexture() {
        return complexSprite.getTexture();
    }

    public int getSize() {
        return complexSprite.getSize();
    }

    public int getStartingIndexAtSpriteSheet() {
        return complexSprite.getStartingIndexAtSpriteSheet();
    }
}
