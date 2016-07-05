package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.facundolinlaud.supergame.utils.AnimationType;

import java.util.Map;

/**
 * Created by facundo on 3/31/16.
 */
public class AnimationComponent implements Component {
    private Map<AnimationType, Animation> animations;
    private AnimationType currentAnimationType;
    private Animation currentAnimation;
    private float stateTime;

    public void animate(boolean state){
        if(state){
            this.currentAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        }else{
            this.currentAnimation.setPlayMode(Animation.PlayMode.NORMAL);
            showStandingTexture();
        }
    }

    private void showStandingTexture() {
        this.stateTime = 0;
    }

    public void setAnimations(Map<AnimationType, Animation> animations){
        this.animations = animations;
    }

    public void setCurrentType(AnimationType animationType){
        this.currentAnimationType = animationType;
        this.currentAnimation = animations.get(animationType);
    }

    public TextureRegion getCurrentTextureAndTick(float deltaTime){
        TextureRegion texture = this.currentAnimation.getKeyFrame(this.stateTime);
        this.stateTime += deltaTime;

        if(this.stateTime < 0.1f){
            this.stateTime = 0.1f;
        }else if(this.stateTime > 8*0.1f){
            this.stateTime = 0.1f;
        }

        return texture;
    }

    public AnimationType getCurrentAnimationTypeOrDefault() {
        return currentAnimationType == null ? AnimationType.WALKING_RIGHT : currentAnimationType;
    }
}
