package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.RenderComponent;
import com.facundolinlaud.supergame.components.player.KeyboardComponent;
import com.facundolinlaud.supergame.utils.Mappers;

import java.util.Comparator;

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

        TextureRegion texture = renderComponent.texture;
        float width = texture.getRegionWidth() / 32f;
        float height = texture.getRegionHeight() / 32f;

        Vector2 pos = renderComponent.renderPositionStrategy.process(positionComponent.x, positionComponent.y);

        spriteBatch.draw(texture, pos.x, pos.y, width, height);
    }

    private static class ZComparator implements Comparator<Entity> {
        @Override
        public int compare(Entity e1, Entity e2) {
            return (int) Math.signum(Mappers.render.get(e1).priority.getPriority() - Mappers.render.get(e2).priority.getPriority());
        }
    }
}
