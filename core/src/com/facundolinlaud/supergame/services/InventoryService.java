package com.facundolinlaud.supergame.services;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.builder.ItemBuilder;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.items.ItemComponent;
import com.facundolinlaud.supergame.components.player.BagComponent;
import com.facundolinlaud.supergame.utils.Mappers;

public class InventoryService extends Service {
    private ComponentMapper<BagComponent> bm = Mappers.bag;
    private ComponentMapper<ItemComponent> im = Mappers.item;
    private ComponentMapper<PositionComponent> pm = Mappers.position;

    private AgentService agentService;

    public InventoryService(Engine engine, AgentService agentService) {
        super(engine);
        this.agentService = agentService;
    }

    public void swap(int fromBagPosition, int toBagPosition) {
        BagComponent bag = getPlayerBag();

        Entity a = bag.get(fromBagPosition);
        Entity b = bag.get(toBagPosition);

        bag.set(toBagPosition, a);
        bag.set(fromBagPosition, b);
    }

    public void drop(int positionInBag) {
        Entity player = agentService.getPlayer();
        PositionComponent dropperPosition = pm.get(player);

        Entity item = bm.get(player).remove(positionInBag);
        ItemComponent itemComponent = im.get(item);

        new ItemBuilder(item)
                .dropped(dropperPosition.getPosition())
                .withRender(itemComponent.getPicture());
    }

    public Entity remove(int positionInBag){
        BagComponent bagComponent = getPlayerBag();
        return bagComponent.remove(positionInBag);
    }

    public void add(Entity equipment) {
        getPlayerBag().add(equipment);
    }

    public BagComponent getBag(Entity agent) {
        return bm.get(agent);
    }

    public BagComponent getPlayerBag() {
        return bm.get(agentService.getPlayer());
    }
}
