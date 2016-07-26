package com.facundolinlaud.supergame.refactor;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.facundolinlaud.supergame.components.player.WearComponent;
import com.facundolinlaud.supergame.utils.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by facundo on 26/7/16.
 */
public class StackableSpriteSystem extends IteratingSystem {
    private ComponentMapper<StackableSpriteComponent> stackable = Mappers.stackableSprite;
    private ComponentMapper<StackedSpritesComponent> stacked = Mappers.stackedSprites;
    private ComponentMapper<WearComponent> wm = Mappers.wear;

    public StackableSpriteSystem() {
        super(Family.all(StackedSpritesComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        // get a todos los StackableSpriteComponent en el bag de la entity
        // los mergeo en el StackedSpriteComponent de la entity

        long start = System.currentTimeMillis();
        List<Texture> wearablesTextures = getTexturesToMerge(entity);
        Texture mixedTextures = mixTextures(wearablesTextures);

        StackedSpritesComponent stackedSpritesComponent = stacked.get(entity);
        stackedSpritesComponent.stackedSprites = mixedTextures;

        System.out.println("Sprite saved to stacked in " + (System.currentTimeMillis() - start) + " ms");
    }

    private List<Texture> getTexturesToMerge(Entity entity){
        WearComponent wearComponent = wm.get(entity);

        return wearComponent
                .asList()
                .stream()
                .map(e -> stackable.get(e).texture)
                .collect(Collectors.toList());
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
