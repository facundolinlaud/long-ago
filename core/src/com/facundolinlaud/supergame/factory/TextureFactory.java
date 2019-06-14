package com.facundolinlaud.supergame.factory;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.facundolinlaud.supergame.utils.Dimensions;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by facundo on 7/26/16.
 */
public class TextureFactory implements Disposable {
    /** image cache */
    private static Map<String, SoftReference<Texture>> textures = new HashMap();

    public static Texture getTexture(String imageName) {
        if(!textures.containsKey(imageName)){
            // we've never stored this image
            Texture img = new Texture(imageName);
            textures.put(imageName, new SoftReference<>(img));
            return img;
        } else {
            SoftReference<Texture> ref = textures.get(imageName);
            // we've stored this image before...
            Texture img = ref.get();
            if(img != null){
                // .... return the previously cached image
                return img;
            } else {
                // ...however it's since been garbage collected.  Clear the record from the
                // cache and call this method again. It will attempt to load the image
                // when there is no entry for the image in the imageMap
                textures.remove(imageName);
                return getTexture(imageName);
            }
        }
    }

    public static Sprite getSprite(String imageName){
        Texture texture = getTexture(imageName);
        float width = Dimensions.toMeters(texture.getWidth());
        float height = Dimensions.toMeters(texture.getHeight());

        Sprite sprite = new Sprite(texture);
        sprite.setSize(width, height);
        sprite.setOriginCenter();

        return sprite;
    }

    @Override
    public void dump() {
        textures.clear();
    }
}
