package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.RenderComponent;
import com.facundolinlaud.supergame.helper.Mappers;

/**
 * Created by facundo on 3/18/16.
 */
public class RenderSystem extends IteratingSystem {
    private ComponentMapper<PositionComponent> position = Mappers.position;
    private ComponentMapper<RenderComponent> render = Mappers.render;

    private SpriteBatch spriteBatch;

    public RenderSystem(SpriteBatch spriteBatch) {
        super(Family.all(PositionComponent.class, RenderComponent.class).get());
        this.spriteBatch = spriteBatch;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent positionComponent = position.get(entity);
        RenderComponent renderComponent = render.get(entity);

        spriteBatch.draw(renderComponent.texture, positionComponent.x, positionComponent.y);
    }
}
