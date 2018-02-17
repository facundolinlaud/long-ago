package com.facundolinlaud.supergame.systems.sprite;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.facundolinlaud.supergame.components.RenderComponent;
import com.facundolinlaud.supergame.components.sprite.AnimableSpriteComponent;
import com.facundolinlaud.supergame.components.sprite.RefreshSpriteRequirementComponent;
import com.facundolinlaud.supergame.components.sprite.StackedSpritesComponent;
import com.facundolinlaud.supergame.model.sprite.RawAnimationModel;
import com.facundolinlaud.supergame.model.status.Status;
import com.facundolinlaud.supergame.model.sprite.SubAnimationModel;
import com.facundolinlaud.supergame.utils.Mappers;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by facundo on 26/7/16.
 */
public class StackedSpritesSystem extends IteratingSystem {
    private ComponentMapper<StackedSpritesComponent> stacked = Mappers.stackedSprites;
    private ComponentMapper<AnimableSpriteComponent> animable = Mappers.animableSprite;

    public StackedSpritesSystem() {
        super(Family.all(RefreshSpriteRequirementComponent.class, AnimableSpriteComponent.class, StackedSpritesComponent.class, RenderComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        // consigo el cropping strategies de la entity
        // construyo las animations y las pongo en el AnimableSpriteComponent

        StackedSpritesComponent stackedSpritesComponent = stacked.get(entity);
        Texture sprites = stackedSpritesComponent.stackedSprites;
        RawAnimationModel model = stackedSpritesComponent.rawAnimationModel;

        Map<Status, Animation> animations = createAnimations(sprites, model);

        AnimableSpriteComponent animableSpriteComponent = animable.get(entity);
        animableSpriteComponent.animations = animations;

        entity.remove(RefreshSpriteRequirementComponent.class);
    }

    private HashMap<Status, Animation> createAnimations(Texture sprites, RawAnimationModel model) {
        Map<Status, SubAnimationModel> animationsModels = model.getSubanimations();
        HashMap<Status, Animation> animations = new HashMap<>();

        int width = model.getWidth();
        int height = model.getHeight();
        float frameDuration = model.getFrameDuration();

        for(Status status : animationsModels.keySet()){
            Array<TextureRegion> segments = new Array<>();
            SubAnimationModel subAnimation = animationsModels.get(status);
            int x = subAnimation.getX() * width;
            int y = subAnimation.getY() * height;
            int length = subAnimation.getLength();

            for(int i = 0; i < length; i++){
                segments.add(new TextureRegion(sprites, x + i * width, y, width, height));
            }

            animations.put(status, new Animation(frameDuration, segments, subAnimation.getPlayMode()));
        }

        return animations;
    }
}
