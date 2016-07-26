package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Rectangle;
import com.facundolinlaud.supergame.components.*;
import com.facundolinlaud.supergame.components.items.ItemComponent;
import com.facundolinlaud.supergame.components.items.PickupableComponent;
import com.facundolinlaud.supergame.components.player.BagComponent;
import com.facundolinlaud.supergame.refactorno.InputComponent;
import com.facundolinlaud.supergame.utils.Mappers;

/**
 * Created by facundo on 3/26/16.
 */
public class PickUpSystem extends EntitySystem {
    private ComponentMapper<PositionComponent> pm = Mappers.position;
    private ComponentMapper<BagComponent> bm = Mappers.bag;
    private ComponentMapper<InputComponent> im = Mappers.input;

    private ImmutableArray<Entity> items;
    private ImmutableArray<Entity> takers;

    public PickUpSystem() {}

    @Override
    public void addedToEngine(Engine engine) {
        items = engine.getEntitiesFor(Family.all(PickupableComponent.class, ItemComponent.class, PositionComponent.class).get());
        takers = engine.getEntitiesFor(Family.all(BagComponent.class, PositionComponent.class, InputComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        for (Entity taker : takers) {
            boolean isGathering = im.get(taker).gathering;

            if(isGathering) {
                PositionComponent takerPosition = pm.get(taker);
                Rectangle rectangle = new Rectangle(takerPosition.x - 1, takerPosition.y - 1, 2, 2);

                for (Entity item : items) {
                    PositionComponent itemPosition = pm.get(item);

                    if(rectangle.contains(itemPosition.x, itemPosition.y)){
                        BagComponent bag = bm.get(taker);
                        bag.addItem(item);
                        deleteItemFromWorld(item);
                    }
                }
            }
        }
    }

    private void deleteItemFromWorld(Entity item) {
        item.remove(PickupableComponent.class);
        item.remove(PositionComponent.class);
        item.remove(BodyComponent.class);
    }
}
