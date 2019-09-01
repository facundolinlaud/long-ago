package com.facundolinlaud.supergame.systems.sprite;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.facundolinlaud.supergame.components.RenderComponent;
import com.facundolinlaud.supergame.components.player.WearComponent;
import com.facundolinlaud.supergame.components.sprite.RefreshSpriteRequirementComponent;
import com.facundolinlaud.supergame.components.sprite.StackableSpriteComponent;
import com.facundolinlaud.supergame.components.sprite.StackedSpritesComponent;
import com.facundolinlaud.supergame.domain.ComplexSprite;
import com.facundolinlaud.supergame.utils.Mappers;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by facundo on 26/7/16.
 */
public class StackableSpriteSystem extends IteratingSystem {
    private ComponentMapper<StackableSpriteComponent> stackable = Mappers.stackableSprite;
    private ComponentMapper<StackedSpritesComponent> stacked = Mappers.stackedSprites;
    private ComponentMapper<WearComponent> wm = Mappers.wear;

    public StackableSpriteSystem() {
        super(Family.all(
                RefreshSpriteRequirementComponent.class,
                StackedSpritesComponent.class,
                RenderComponent.class,
                WearComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        List<ComplexSprite> wearablesComplexSprites = getTexturesToMerge(entity);
        StackedSpritesComponent stackedSpritesComponent = stacked.get(entity);
        stackedSpritesComponent.setStackedComplexSprites(wearablesComplexSprites);
    }

    private List<ComplexSprite> getTexturesToMerge(Entity entity){
        WearComponent wearComponent = wm.get(entity);

        return wearComponent
                .asList()
                .stream()
                .map(e -> stackable.get(e).getComplexSprite())
                .collect(Collectors.toList());
    }
}
