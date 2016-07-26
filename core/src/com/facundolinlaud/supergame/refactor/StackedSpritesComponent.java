package com.facundolinlaud.supergame.refactor;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.facundolinlaud.supergame.refactor.model.AnimationModel;

/**
 * Created by facundo on 26/7/16.
 */
public class StackedSpritesComponent implements Component {
    public Texture stackedSprites;
    public AnimationModel animationModel;
}
