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
public class SpriteStackHelper {
    private final static int FRAME_SIZE = 64;

    public static HashMap<AnimationType, Animation> texturesToFrames(List<Texture> textures, float frameDuration){
        return splitTextureIntoFrames(mixTextures(textures), frameDuration);
    }

    private static HashMap<AnimationType, Animation> splitTextureIntoFrames(Texture sheet, float frameDuration){
        int rows = AnimationType.values().length;

        HashMap<AnimationType, Animation> animations = new HashMap<>();

        for(int row = 0; row < rows; row++){
            AnimationType animationType = AnimationType.values()[row];
            int columns = animationType.getColumns();

            TextureRegion[] frames = new TextureRegion[columns];

            for(int col = 0; col < columns; col++){
                int x = col * FRAME_SIZE;
                int y = row * FRAME_SIZE;

                frames[col] = new TextureRegion(sheet, x, y, FRAME_SIZE, FRAME_SIZE);
            }

            animations.put(animationType, new Animation(frameDuration, Array.with(frames), Animation.PlayMode.NORMAL));
        }

        return animations;
    }

    private static Texture mixTextures(List<Texture> textures){
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

        return texture;
    }
}