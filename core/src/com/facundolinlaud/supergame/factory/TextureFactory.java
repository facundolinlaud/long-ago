package com.facundolinlaud.supergame.factory;

import com.badlogic.gdx.graphics.Texture;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * Created by facundo on 7/26/16.
 */
public class TextureFactory implements Disposable {
    /** image cache */
    private static HashMap<String, SoftReference<Texture>> textures = new HashMap<>();

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

    @Override
    public void dump() {
        textures.clear();
    }
}
