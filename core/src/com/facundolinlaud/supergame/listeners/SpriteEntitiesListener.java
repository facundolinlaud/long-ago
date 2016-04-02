package com.facundolinlaud.supergame.listeners;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.graphics.Texture;
import com.facundolinlaud.supergame.components.AnimationComponent;
import com.facundolinlaud.supergame.components.SpriteComponent;
import com.facundolinlaud.supergame.components.player.WearComponent;
import com.facundolinlaud.supergame.utils.AnimationHelper;
import com.facundolinlaud.supergame.utils.AnimationType;
import com.facundolinlaud.supergame.utils.Mappers;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by facundo on 4/2/16.
 */
public class SpriteEntitiesListener implements EntityListener {
    private static final float FRAME_DURATION = 0.06f;

    private ComponentMapper<AnimationComponent> am = Mappers.animation;
    private ComponentMapper<WearComponent> wm = Mappers.wear;
    private ComponentMapper<SpriteComponent> sm = Mappers.sprite;

    @Override
    public void entityAdded(Entity entity) {
        initialize(entity);
    }

    @Override
    public void entityRemoved(Entity entity) {

    }

    private void initialize(Entity entity) {
        long start = System.currentTimeMillis();

        WearComponent wearComponent = wm.get(entity);
        AnimationComponent animationComponent = am.get(entity);

        List<Texture> wearablesTextures = wearComponent
                .asList()
                .stream()
                .map(e -> sm.get(e).texture)
                .collect(Collectors.toList());

        animationComponent.setAnimations(AnimationHelper.texturesToFrames(wearablesTextures, FRAME_DURATION));
        animationComponent.setCurrentType(AnimationType.WALKING_RIGHT);

        System.out.println("Sprite rendered in " + (System.currentTimeMillis() - start) + " ms");
    }
}
