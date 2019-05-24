package com.facundolinlaud.supergame.components.sprite;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.facundolinlaud.supergame.model.sprite.RawAnimationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by facundo on 26/7/16.
 */
public class StackedSpritesComponent implements Component {
    private List<Texture> stackedSprites;
    private RawAnimationModel rawAnimationModel;

    public StackedSpritesComponent(RawAnimationModel rawAnimationModel) {
        this.rawAnimationModel = rawAnimationModel;
        this.stackedSprites = new ArrayList();
    }

    public List<Texture> getStackedSprites() {
        return stackedSprites;
    }

    public void setStackedSprites(List<Texture> stackedSprites) {
        this.stackedSprites = stackedSprites;
    }

    public RawAnimationModel getRawAnimationModel() {
        return rawAnimationModel;
    }
}
