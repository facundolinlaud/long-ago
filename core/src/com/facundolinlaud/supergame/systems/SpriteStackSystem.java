package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Texture;
import com.facundolinlaud.supergame.components.AnimationComponent;
import com.facundolinlaud.supergame.components.SpriteComponent;
import com.facundolinlaud.supergame.components.SpriteStackableComponent;
import com.facundolinlaud.supergame.components.player.WearComponent;
import com.facundolinlaud.supergame.utils.SpriteStackHelper;
import com.facundolinlaud.supergame.utils.Mappers;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by facundo on 4/15/16.
 */
public class SpriteStackSystem extends IteratingSystem {
    private static final float FRAME_DURATION = 0.1f;

    private ComponentMapper<WearComponent> wm = Mappers.wear;
    private ComponentMapper<SpriteComponent> sm = Mappers.sprite;
    private ComponentMapper<SpriteStackableComponent> ssm = Mappers.spriteStack;
    private ComponentMapper<AnimationComponent> am = Mappers.animation;

    public SpriteStackSystem() {
        super(Family.all(SpriteStackableComponent.class).get());
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        getEntities().forEach(entity -> entity.add(new AnimationComponent()));
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        SpriteStackableComponent spriteStack = ssm.get(entity);

        if(spriteStack.shouldRefresh){
            updateAnimationComponent(entity);
            spriteStack.shouldRefresh = false;
        }
    }

    private void updateAnimationComponent(Entity entity) {
        long start = System.currentTimeMillis();

        WearComponent wearComponent = wm.get(entity);
        AnimationComponent animationComponent = am.get(entity);

        List<Texture> wearablesTextures = wearComponent
                .asList()
                .stream()
                .map(e -> sm.get(e).texture)
                .collect(Collectors.toList());

        animationComponent.setAnimations(SpriteStackHelper.texturesToFrames(wearablesTextures, FRAME_DURATION));
        animationComponent.setCurrentType(animationComponent.getCurrentAnimationTypeOrDefault());

        System.out.println("Sprite rendered in " + (System.currentTimeMillis() - start) + " ms");
    }
}
