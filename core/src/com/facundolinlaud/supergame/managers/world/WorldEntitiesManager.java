package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.HealthComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.factory.Factories;
import com.facundolinlaud.supergame.factory.ItemFactory;
import com.facundolinlaud.supergame.utils.Mappers;

import java.util.LinkedList;
import java.util.List;

public class WorldEntitiesManager {
    private ComponentMapper<PositionComponent> pm = Mappers.position;

    private static final Integer MAIN_PLAYER_ID = 0;
    private static final Integer ORC = 2;

    private Factories factories;
    private Engine engine;
    private Entity player;
    private Entity talkable;

    public WorldEntitiesManager(Engine engine, Factories factories) {
        this.engine = engine;
        this.factories = factories;

        addPlayer();
        addCoins();
        addSword();
    }

    private void addPlayer(){
        player = factories.getAgentFactory()
                .create(MAIN_PLAYER_ID)
                .withKeyboardControl()
                .at(29, 35)
                .build();

        engine.addEntity(player);
    }

    public List<Entity> getAgentsAt(Shape2D shape){
        List<Entity> entities = new LinkedList();
        ImmutableArray<Entity> allEntities = engine.getEntitiesFor(Family.all(
                HealthComponent.class,
                PositionComponent.class).get());

        for(Entity e : allEntities){
            PositionComponent entityPosition = pm.get(e);

            if(shape.contains(entityPosition.getPosition()))
                entities.add(e);
        }

        return entities;
    }

    /* For testing purposes */
    private void addCoins(){
        Entity coins = factories.getItemFactory().getItem(ItemFactory.COINS)
                .dropped(21, 48)
                .build();

        engine.addEntity(coins);
    }

    /* For testing purposes */
    private void addSword(){
        Entity saber = factories.getItemFactory().getItem(ItemFactory.SABER)
                .dropped(20, 48)
                .build();

        engine.addEntity(saber);
    }

    public Entity getPlayer() {
        return player;
    }
}
