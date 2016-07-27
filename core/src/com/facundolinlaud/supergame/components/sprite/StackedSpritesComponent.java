package com.facundolinlaud.supergame.components.sprite;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.facundolinlaud.supergame.model.sprite.AnimationModel;

/**
 * Created by facundo on 26/7/16.
 */
public class StackedSpritesComponent implements Component {
    public Texture stackedSprites;
    public AnimationModel animationModel;

    public StackedSpritesComponent(AnimationModel animationModel) {
        this.animationModel = animationModel;
    }
}
