package com.facundolinlaud.supergame.utils;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by facundo on 4/1/16.
 */
public class AnimationHelper {
    public static HashMap<AnimationType, Animation> texturesToFrames(List<Texture> textures, float frameDuration){
        return splitTextureIntoFrames(mixTextures(textures), frameDuration);
    }

    private static HashMap<AnimationType, Animation> splitTextureIntoFrames(Texture sheet, float frameDuration){
        System.out.println("splitTextureIntoFrames init: " + System.currentTimeMillis());

        int rows = AnimationType.values().length;
        final int frameSize = 64;

        HashMap<AnimationType, Animation> animations = new HashMap<>();

        for(int row = 0; row < rows; row++){
            AnimationType animationType = AnimationType.values()[row];
            int columns = animationType.getColumns();

            TextureRegion[] frames = new TextureRegion[columns];

            for(int col = 0; col < columns; col++){
                int x = col * frameSize;
                int y = row * frameSize;

                frames[col] = new TextureRegion(sheet, x, y, frameSize, frameSize);
            }

            animations.put(animationType, new Animation(frameDuration, Array.with(frames), Animation.PlayMode.NORMAL));
        }

        System.out.println("splitTextureIntoFrames end: " + System.currentTimeMillis());
        return animations;
    }

    private static Texture mixTextures(List<Texture> textures){
        System.out.println("mixTextures init: " + System.currentTimeMillis());

        ArrayList<Pixmap> pixmaps = new ArrayList<>();

        for(Texture texture : textures){
            if (!texture.getTextureData().isPrepared()) {
                texture.getTextureData().prepare();
            }

            pixmaps.add(texture.getTextureData().consumePixmap());
        }

        Pixmap accumulator = pixmaps.get(0);

        for(int i = 1; i < pixmaps.size(); i++){
            Pixmap other = pixmaps.get(i);
            accumulator.drawPixmap(other, 0, 0);
            other.dispose();
        }

        Texture texture = new Texture(accumulator, Pixmap.Format.RGBA8888, false);
        accumulator.dispose();

        System.out.println("mixTexturesend: " + System.currentTimeMillis());

        return texture;
    }
}


/*
    String aPath = "sprites/living/body/light.png";
    String bPath = "sprites/items/body/mail.png";

    Pixmap a = new Pixmap(Gdx.files.internal(aPath));
    Pixmap b = new Pixmap(Gdx.files.internal(bPath));

    a.drawPixmap(b, 0, 0);

    Texture pixmapTexture = new Texture(a, Pixmap.Format.RGBA8888, false);
    TextureRegion region = new TextureRegion(pixmapTexture, 0, 0, 64, 64);
//        Sprite sprite = new Sprite(pixmapTexture);
//        sprite.setSize(1f, 1f);

//        System.out.println(pixmapTexture.getWidth() + "x" + pixmapTexture.getHeight());

    a.dispose();
    b.dispose();

    return region;*/