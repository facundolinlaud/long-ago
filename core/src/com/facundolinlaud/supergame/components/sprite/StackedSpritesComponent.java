package com.facundolinlaud.supergame.components.sprite;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.facundolinlaud.supergame.domain.Sprite;
import com.facundolinlaud.supergame.model.sprite.RawAnimationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by facundo on 26/7/16.
 */
public class StackedSpritesComponent implements Component {
    private List<Sprite> stackedSprites;
    private RawAnimationModel rawAnimationModel;

    public StackedSpritesComponent(RawAnimationModel rawAnimationModel) {
        this.rawAnimationModel = rawAnimationModel;
        this.stackedSprites = new ArrayList();
    }

    public List<Sprite> getStackedSprites() {
        return stackedSprites;
    }

    public void setStackedSprites(List<Sprite> stackedSprites) {
        this.stackedSprites = stackedSprites;
    }

    public RawAnimationModel getRawAnimationModel() {
        return rawAnimationModel;
    }
}
