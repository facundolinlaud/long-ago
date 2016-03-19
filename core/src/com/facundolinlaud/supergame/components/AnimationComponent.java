package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.facundolinlaud.supergame.helper.AnimationType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by facundo on 3/19/16.
 */
public class AnimationComponent implements Component {
    private static final int FRAME_COLUMNS = 4;
    public static final int TILE_HEIGHT = 48;
    public static final int TILE_WIDTH = 32;
    public static final int STAND_STILL_KEY_FRAME = 3;
    public static final float FRAME_DURATION = 0.2f;

    private Map<AnimationType, Animation> animations;
    public Animation currentAnimation;
    private AnimationType type;
    private float stateTime;

    public AnimationComponent(List<AnimationType> animationTypes, Texture sheet) {
        initializeAnimations(animationTypes, sheet);
        setCurrentType(animationTypes.get(0));
    }

    private void initializeAnimations(List<AnimationType> animationTypes, Texture sheet) {
        animations = new HashMap<AnimationType, Animation>();
        TextureRegion[][] tmp = TextureRegion.split(sheet, TILE_WIDTH, TILE_HEIGHT);

        for(int i = 0; i < animationTypes.size(); i++){
            int framesInAnimationType = tmp[i].length;
            TextureRegion[] frames = new TextureRegion[framesInAnimationType];

            for(int e = 0; e < framesInAnimationType ; e++){
                frames[e] = tmp[i][e];
            }

            animations.put(animationTypes.get(i), new Animation(FRAME_DURATION, Array.with(frames), Animation.PlayMode.LOOP));
        }
    }

    public void setCurrentType(AnimationType type){
        if(!type.equals(this.type) && !type.equals(AnimationType.STAND)) {
            this.type = type;
            this.currentAnimation = animations.get(type);
        }
    }

    public void toggle(boolean state){
        if(state)
            currentAnimation.setPlayMode(Animation.PlayMode.LOOP);
        else
            currentAnimation.setPlayMode(Animation.PlayMode.NORMAL);
    }

    public TextureRegion getCurrentTextureAndTick(float deltaTime){
        TextureRegion texture = currentAnimation.getKeyFrame(stateTime);
        stateTime += deltaTime;

        return texture;
    }
}