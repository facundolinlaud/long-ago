package com.facundolinlaud.supergame.factory;

import com.badlogic.gdx.graphics.Texture;
import com.facundolinlaud.supergame.domain.Sprite;
import com.facundolinlaud.supergame.model.sprite.SpriteModel;

import java.util.HashMap;
import java.util.Map;

public class SpriteFactory {
    private static final int DEFAULT_REGION_SIZE = 64;
    private static SpriteModel DEFAULT_SPRITE_MODEL = new SpriteModel(DEFAULT_REGION_SIZE);
    private static Map<String, Sprite> sprites;

    static {
        sprites = new HashMap();
        loadSpritesMap();
    }

    private static void loadSpritesMap() {
        Map<String, SpriteModel> models = ModelFactory.getSpriteModels();

        for(Map.Entry<String, SpriteModel> entry : models.entrySet()){
            String texturePath = entry.getKey();
            SpriteModel spriteModel = entry.getValue();

            Texture texture = TextureFactory.getTexture(texturePath);
            Sprite sprite = new Sprite(texture, spriteModel);

            sprites.put(entry.getKey(), sprite);
        }
    }

    public static Sprite get(String path){
        if(!sprites.containsKey(path)){
            Texture texture = TextureFactory.getTexture(path);
            sprites.put(path, new Sprite(texture, DEFAULT_SPRITE_MODEL));
        }

        return sprites.get(path);
    }
}
