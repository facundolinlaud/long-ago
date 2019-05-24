package com.facundolinlaud.supergame.factory;

import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.builder.AgentBuilder;
import com.facundolinlaud.supergame.components.sprite.StackableSpriteComponent;
import com.facundolinlaud.supergame.model.agent.Agent;
import com.facundolinlaud.supergame.model.equip.EquipSlot;

import java.util.HashMap;
import java.util.Map;

public class AgentFactory {
    private Map<Integer, Agent> agents;
    private ItemFactory itemFactory;

    public AgentFactory(ItemFactory itemFactory) {
        this.agents = ModelFactory.getAgentsModel();
        this.itemFactory = itemFactory;
    }

    public AgentBuilder get(int id){
        Agent agent = agents.get(id);

        Map<EquipSlot, Entity> equipment = buildEquipment(agent.getBody(), agent.getEquipment());

        AgentBuilder builder = new AgentBuilder(agent.getVelocity())
                .withAI(agent.getViewDistance())
                .withHealth(agent.getHealth(), agent.getHealth())
                .withEquipment(equipment);

        return builder;
    }

    private Map<EquipSlot, Entity> buildEquipment(Map<EquipSlot, String> body, Map<EquipSlot, Integer> model) {
        Map<EquipSlot, Entity> equipment = new HashMap();

        for(Map.Entry<EquipSlot, String> entry : body.entrySet()){
            Entity overlay = new Entity()
                    .add(new StackableSpriteComponent(TextureFactory.get(entry.getValue())));

            equipment.put(entry.getKey(), overlay);
        }

        for(Map.Entry<EquipSlot, Integer> entry : model.entrySet()){
            Entity item = itemFactory.getItem(entry.getValue()).build();
            equipment.put(entry.getKey(), item);
        }

        return equipment;
    }
}
