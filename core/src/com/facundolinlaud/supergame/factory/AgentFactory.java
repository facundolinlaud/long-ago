package com.facundolinlaud.supergame.factory;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.facundolinlaud.supergame.builder.AgentBuilder;
import com.facundolinlaud.supergame.components.sprite.StackableSpriteComponent;
import com.facundolinlaud.supergame.model.agent.Agent;
import com.facundolinlaud.supergame.model.agent.SkillsInformation;
import com.facundolinlaud.supergame.model.equip.EquipSlot;
import com.facundolinlaud.supergame.model.particle.ParticleType;
import com.facundolinlaud.supergame.utils.events.Messages;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AgentFactory {
    private static final Integer MAIN_PLAYER_ID = 0;

    private Engine engine;
    private ItemFactory itemFactory;
    private ParticleFactory particleFactory;
    private SkillsFactory skillsFactory;
    private Map<Integer, Agent> agents;
    private MessageDispatcher messageDispatcher;

    public AgentFactory(Engine engine, ItemFactory itemFactory, ParticleFactory particleFactory, SkillsFactory skillsFactory) {
        this.engine = engine;
        this.agents = ModelFactory.getAgentsModel();
        this.itemFactory = itemFactory;
        this.particleFactory = particleFactory;
        this.skillsFactory = skillsFactory;
        this.messageDispatcher = MessageManager.getInstance();
    }

    private AgentBuilder getDummyAgent(Agent agent, int id){
        AgentBuilder builder = new AgentBuilder(agent.getVelocity(), id)
                .withAttributes(agent.getAttributes());

        return builder;
    }

    public AgentBuilder getAI(int id){
        Agent agent = agents.get(id);
        Map<EquipSlot, Entity> equipment = buildEquipment(agent.getBody(), agent.getEquipment());
        SkillsInformation skillsInfo = agent.getSkillsInformation();

        return getDummyAgent(agent, id)
                .withAI(agent.getNpcInformation())
                .withEquipment(equipment)
                .withParticles(particleFactory.getEffect(ParticleType.BLACK_SMOKE))
                .withSkills(skillsFactory.get(skillsInfo.getSkills()), skillsInfo.getAssignablePoints());
    }

    public AgentBuilder getPlayer(){
        Agent agent = agents.get(MAIN_PLAYER_ID);
        Map<EquipSlot, Entity> equipment = buildEquipment(agent.getBody(), agent.getEquipment());
        SkillsInformation skillsInfo = agent.getSkillsInformation();

        return getDummyAgent(agent, MAIN_PLAYER_ID)
                .withBag(buildBag(agent.getBag()), agent.getGold(), Messages.INVENTORY_CHANGED)
                .withEquipment(equipment, Messages.EQUIPMENT_CHANGED)
                .withKeyboardControl()
                .withSkills(skillsFactory.get(skillsInfo.getSkills()), skillsInfo.getAssignablePoints(),
                        Messages.SKILLS_CHANGED);
    }

    private Map<EquipSlot, Entity> buildEquipment(Map<EquipSlot, String> body, Map<EquipSlot, Integer> model) {
        Map<EquipSlot, Entity> equipment = new HashMap();

        for(Map.Entry<EquipSlot, String> entry : body.entrySet()){
            Entity overlay = new Entity()
                    .add(new StackableSpriteComponent(SpriteFactory.get(entry.getValue())));

            equipment.put(entry.getKey(), overlay);
        }

        for(Map.Entry<EquipSlot, Integer> entry : model.entrySet()){
            Entity item = itemFactory.getItem(entry.getValue()).build();
            equipment.put(entry.getKey(), item);
            engine.addEntity(item);
        }

        return equipment;
    }

    private List<Entity> buildBag(List<Integer> model) {
        List<Entity> bag = new LinkedList();

        for(Integer id : model) {
            Entity item = itemFactory.getItem(id).build();
            engine.addEntity(item);
            bag.add(item);
        }

        return bag;
    }
}
