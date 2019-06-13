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
import com.facundolinlaud.supergame.domain.Sprite;
import com.facundolinlaud.supergame.model.sprite.RawAnimationModel;
import com.facundolinlaud.supergame.model.sprite.SubAnimationModel;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.model.status.Status;
import com.facundolinlaud.supergame.utils.Mappers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        List<Sprite> sprites = stackedSpritesComponent.getStackedSprites();
        RawAnimationModel model = stackedSpritesComponent.getRawAnimationModel();

        List<Map<Status, Animation>> texturesToAnimations = new ArrayList();

        for(Sprite sprite : sprites){
            Map<Status, Animation> animations = createAnimations(sprite, model);
            texturesToAnimations.add(animations);
        }

        AnimableSpriteComponent animableSpriteComponent = animable.get(entity);
        animableSpriteComponent.setTexturesToAnimations(texturesToAnimations);

        entity.remove(RefreshSpriteRequirementComponent.class);
    }

    private Map<Status, Animation> createAnimations(Sprite sprite, RawAnimationModel model) {
        Map<Status, SubAnimationModel> animationsModels = model.getSubAnimations();
        Map<Status, Animation> animations = new HashMap<>();

        Texture texture = sprite.getTexture();
        int width = sprite.getSize();
        int height = sprite.getSize();
        float frameDuration = model.getFrameDuration();

        for(Status status : animationsModels.keySet()){
            Array<TextureRegion> segments = new Array();
            SubAnimationModel subAnimation = animationsModels.get(status);
            int x = subAnimation.getX() * width;
            int y = (subAnimation.getY() - sprite.getStartingIndexAtSpriteSheet()) * height;
            int length = subAnimation.getLength();

            for(int i = 0; i < length; i++)
                segments.add(new TextureRegion(texture, x + i * width, y, width, height));

            animations.put(status, new Animation(frameDuration, segments, subAnimation.getPlayMode()));
        }

        return animations;
    }
}
