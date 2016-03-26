package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.facundolinlaud.supergame.components.*;
import com.facundolinlaud.supergame.helper.Mappers;

/**
 * Created by facundo on 3/26/16.
 */
public class PickUpSystem extends EntitySystem {
    private ComponentMapper<PositionComponent> pm = Mappers.position;
    private ComponentMapper<GathererComponent> gm = Mappers.gatherer;

    private ImmutableArray<Entity> items;
    private ImmutableArray<Entity> takers;

    public PickUpSystem() {}

    @Override
    public void addedToEngine(Engine engine) {
        items = engine.getEntitiesFor(Family.all(PickupableComponent.class, ItemComponent.class, PositionComponent.class).get());
        takers = engine.getEntitiesFor(Family.all(GathererComponent.class, PositionComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            for (Entity taker : takers) {
                PositionComponent takerPosition = pm.get(taker);
                Rectangle rectangle = new Rectangle(takerPosition.x - 1, takerPosition.y - 1, 2, 2);

                for (Entity item : items) {
                    PositionComponent itemPosition = pm.get(item);

                    if(rectangle.contains(itemPosition.x, itemPosition.y)){
                        GathererComponent gatherer = gm.get(taker);
                        gatherer.addItem(item);
                        deleteItemFromWorld(item);
                    }
                }
            }
        }
    }

    private void deleteItemFromWorld(Entity item) {
        item.remove(PickupableComponent.class);
        item.remove(PositionComponent.class);
        item.remove(RenderComponent.class);
        item.remove(BodyComponent.class);
    }
}
