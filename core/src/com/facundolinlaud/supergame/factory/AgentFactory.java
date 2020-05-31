package com.facundolinlaud.supergame.factory;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.builder.AgentBuilder;
import com.facundolinlaud.supergame.components.sprite.StackableSpriteComponent;
import com.facundolinlaud.supergame.dto.agent.AIInformation;
import com.facundolinlaud.supergame.dto.agent.Agent;
import com.facundolinlaud.supergame.dto.agent.BagInformation;
import com.facundolinlaud.supergame.dto.agent.CombatInformation;
import com.facundolinlaud.supergame.model.equip.EquipSlot;
import com.facundolinlaud.supergame.model.particle.ParticleType;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.model.sprite.RawAnimationModel;
import com.facundolinlaud.supergame.utils.events.Messages;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.facundolinlaud.supergame.utils.events.Messages.INVENTORY_CHANGED;
import static com.facundolinlaud.supergame.utils.events.Messages.SKILLS_CHANGED;

public class AgentFactory {
    private Engine engine;
    private ItemFactory itemFactory;
    private SkillsFactory skillsFactory;
    private ParticleFactory particleFactory;
    private AnimationsFactory animationsFactory;
    private Map<Integer, Agent> agents;

    public AgentFactory(Engine engine, Factories factories) {
        this.engine = engine;
        this.agents = ModelFactory.getAgentsModel();
        this.itemFactory = factories.getItemFactory();
        this.skillsFactory = factories.getSkillsFactory();
        this.particleFactory = factories.getParticleFactory();
        this.animationsFactory = factories.getAnimationsFactory();
    }

    public AgentBuilder create(int id) {
        Agent agent = agents.get(id);

        RawAnimationModel rawAnimationModel = animationsFactory.get(agent.getAnimationModel());
        Map<EquipSlot, Entity> equipment = buildEquipment(agent.getBody(), agent.getEquipment());

        AgentBuilder builder = new AgentBuilder(id)
                .withVelocity(agent.getVelocity())
                .withAnimations(rawAnimationModel)
                .withEquipment(equipment, Messages.EQUIPMENT_CHANGED)
                .withParticles(particleFactory.getEffect(ParticleType.BLACK_SMOKE))
                .withBody();

        if (agent.hasAI()) {
            AIInformation ai = agent.getAiInformation();
            builder.withAI(ai.getBehaviorType(), ai.getViewDistance());
        }

        if (agent.hasBag()) {
            BagInformation bagInformation = agent.getBagInformation();
            List<Entity> bag = buildBag(bagInformation.getBag());
            builder.withBag(bag, bagInformation.getGold(), INVENTORY_CHANGED);
        }

        if (agent.hasCombat()) {
            CombatInformation ci = agent.getCombatInformation();
            List<Skill> skills = skillsFactory.get(ci.getSkills());
            builder.withSkills(skills, ci.getAssignablePoints(), SKILLS_CHANGED)
                    .withAttributes(ci.getAttributes())
                    .shootable();
        }

        if (agent.isTalkable())
            builder.talkable();

        return builder;
    }

    private Map<EquipSlot, Entity> buildEquipment(Map<EquipSlot, String> body, Map<EquipSlot, Integer> model) {
        Map<EquipSlot, Entity> equipment = new HashMap();

        for (Map.Entry<EquipSlot, String> entry : body.entrySet()) {
            Entity overlay = new Entity()
                    .add(new StackableSpriteComponent(SpriteFactory.get(entry.getValue())));

            equipment.put(entry.getKey(), overlay);
        }

        for (Map.Entry<EquipSlot, Integer> entry : model.entrySet()) {
            Entity item = itemFactory.getItem(entry.getValue()).build();
            equipment.put(entry.getKey(), item);
            engine.addEntity(item);
        }

        return equipment;
    }

    private List<Entity> buildBag(List<Integer> model) {
        List<Entity> bag = new LinkedList();

        for (Integer id : model) {
            Entity item = itemFactory.getItem(id).build();
            engine.addEntity(item);
            bag.add(item);
        }

        return bag;
    }
}
