package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.facundolinlaud.supergame.utils.AnimationType2;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by facundo on 3/31/16.
 */
public class SpriteComponent implements Component {
    private static final AnimationType2 DEFAULT_ANIMATION_TYPE = AnimationType2.WALKING_RIGHT;
    private static final float FRAME_DURATION = 0.1f;

    private Map<AnimationType2, Animation> animations;
    private AnimationType2 currentAnimationType;
    private Animation currentAnimation;
    private float stateTime;

    public SpriteComponent(Texture sheet) {
        this.animations = splitTextureIntoFrames(sheet);
        this.setCurrentType(DEFAULT_ANIMATION_TYPE);
    }

    private HashMap<AnimationType2, Animation> splitTextureIntoFrames(Texture sheet){
        TextureRegion region = new TextureRegion(sheet);

        final int frameHeight = 48, frameWidth = 32, offsetX = 16, offsetY = 15, jumpX = 32, jumpY = 15;

        int rows = AnimationType2.values().length;

        HashMap<AnimationType2, Animation> animations = new HashMap<>();

        for(int row = 0; row < rows; row++){
            AnimationType2 animationType = AnimationType2.values()[row];
            int columns = animationType.getColumns();

            TextureRegion[] frames = new TextureRegion[columns];

            for(int col = 0; col < columns; col++){
                int x = col * 64;//offsetX + (col * (jumpX + frameWidth));
                int y = row * 64; //offsetY + (row * (jumpY + frameHeight));

                frames[col] = new TextureRegion(sheet, x, y, 64, 64);
            }

            animations.put(animationType, new Animation(FRAME_DURATION, Array.with(frames), Animation.PlayMode.NORMAL));
        }

        return animations;
    }

    public void setCurrentType(AnimationType2 animationType){
        if (!animationType.equals(this.currentAnimationType) && !AnimationType2.NONE.equals(animationType)) {
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
}
