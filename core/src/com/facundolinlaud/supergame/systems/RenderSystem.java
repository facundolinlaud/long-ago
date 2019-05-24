package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ScaleInfluencer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.RenderComponent;
import com.facundolinlaud.supergame.components.player.KeyboardComponent;
import com.facundolinlaud.supergame.utils.Dimensions;
import com.facundolinlaud.supergame.utils.Mappers;

import java.util.Comparator;
import java.util.List;

/**
 * Created by facundo on 3/18/16.
 */
public class RenderSystem extends SortedIteratingSystem {
    private ComponentMapper<PositionComponent> pm = Mappers.position;
    private ComponentMapper<RenderComponent> rm = Mappers.render;

    private SpriteBatch spriteBatch;

    public RenderSystem(SpriteBatch spriteBatch) {
        super(Family.all(PositionComponent.class, RenderComponent.class).get(), new ZComparator());
        this.spriteBatch = spriteBatch;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent positionComponent = pm.get(entity);
        RenderComponent renderComponent = rm.get(entity);

        List<TextureRegion> regions = renderComponent.getRegions();

        if(regions.isEmpty()) return;

        for(TextureRegion region : regions){
            float width = Dimensions.toMeters(region.getRegionWidth());
            float height = Dimensions.toMeters(region.getRegionHeight());

            Vector2 pos = renderComponent.getRenderPositionStrategy().process(positionComponent.x, positionComponent.y);

            // TODO: generalize this. RenderSystem can only render textures, not particles
            spriteBatch.draw(region, pos.x, pos.y, width, height);
        }
    }

    private static class ZComparator implements Comparator<Entity> {
        @Override
        public int compare(Entity e1, Entity e2) {
            return (int) Math.signum(Mappers.render.get(e1).getPriority().getPriority() -
                    Mappers.render.get(e2).getPriority().getPriority());
        }
    }
}
