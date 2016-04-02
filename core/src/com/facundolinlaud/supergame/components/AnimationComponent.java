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
    private static final AnimationType DEFAULT_ANIMATION_TYPE = AnimationType.WALKING_RIGHT;

    private Map<AnimationType, Animation> animations;
    private AnimationType currentAnimationType;
    private Animation currentAnimation;
    private float stateTime;

    public void setCurrentType(AnimationType animationType){
        if (!animationType.equals(this.currentAnimationType) && !AnimationType.NONE.equals(animationType)) {
            this.currentAnimationType = animationType;
            this.currentAnimation = animations.get(animationType);
        }
    }

    public void animate(boolean state){
        if(state){
            this.currentAnimation.setPlayMode(Animation.PlayMode.LOOP);
        }else{
            this.currentAnimation.setPlayMode(Animation.PlayMode.NORMAL);
            showStandingTexture();
        }
    }

    private void showStandingTexture() {
        this.stateTime = 0;
    }

    public TextureRegion getCurrentTextureAndTick(float deltaTime){
        TextureRegion texture = this.currentAnimation.getKeyFrame(this.stateTime);
        this.stateTime += deltaTime;

        return texture;
    }

    public void setAnimations(Map<AnimationType, Animation> animations){
        this.animations = animations;
    }

    public boolean isInitialized(){
        return !(this.animations == null);
    }
}
