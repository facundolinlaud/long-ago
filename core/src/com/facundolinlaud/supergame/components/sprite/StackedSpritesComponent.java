package com.facundolinlaud.supergame.components.sprite;

import com.badlogic.ashley.core.Component;
import com.facundolinlaud.supergame.domain.ComplexSprite;
import com.facundolinlaud.supergame.model.sprite.RawAnimationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by facundo on 26/7/16.
 */
public class StackedSpritesComponent implements Component {
    private List<ComplexSprite> stackedComplexSprites;
    private RawAnimationModel rawAnimationModel;

    public StackedSpritesComponent(RawAnimationModel rawAnimationModel) {
        this.rawAnimationModel = rawAnimationModel;
        this.stackedComplexSprites = new ArrayList();
    }

    public List<ComplexSprite> getStackedComplexSprites() {
        return stackedComplexSprites;
    }

    public void setStackedComplexSprites(List<ComplexSprite> stackedComplexSprites) {
        this.stackedComplexSprites = stackedComplexSprites;
    }

    public RawAnimationModel getRawAnimationModel() {
        return rawAnimationModel;
    }
}
