package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.factory.Factories;
import com.facundolinlaud.supergame.factory.ItemFactory;

public class WorldEntitiesManager {
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
        addTalkable();
    }

    private void addTalkable(){
        talkable = factories.getAgentFactory().getDummyAgent(2)
                .talkable()
                .at(30, 35)
                .build();

        engine.addEntity(talkable);
    }

    private void addPlayer(){
        player = factories.getAgentFactory()
                .getPlayer()
                .at(29, 35)
                .build();

        engine.addEntity(player);
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
