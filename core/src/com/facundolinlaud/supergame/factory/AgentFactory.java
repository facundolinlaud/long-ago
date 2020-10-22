package com.facundolinlaud.supergame.factory;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.ai.behavior.BehaviorTask;
import com.facundolinlaud.supergame.builder.AgentBuilder;
import com.facundolinlaud.supergame.components.sprite.StackableSpriteComponent;
import com.facundolinlaud.supergame.dto.agent.Agent;
import com.facundolinlaud.supergame.dto.agent.BagInformation;
import com.facundolinlaud.supergame.dto.agent.BehaviorInformation;
import com.facundolinlaud.supergame.dto.agent.CombatInformation;
import com.facundolinlaud.supergame.dto.behaviors.BehaviorTaskDto;
import com.facundolinlaud.supergame.model.equip.EquipSlot;
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
    private static final String BLACK_SMOKE_PARTICLE_ID = "black_smoke";

    private Engine engine;
    private ItemFactory itemFactory;
    private SkillsFactory skillsFactory;
    private ParticleFactory particleFactory;
    private AnimationsFactory animationsFactory;
    private BehaviorFactory behaviorFactory;
    private Map<String, Agent> agents;

    public AgentFactory(Engine engine, Factories factories) {
        this.engine = engine;
        this.agents = ModelFactory.getAgentsModel();
        this.itemFactory = factories.getItemFactory();
        this.skillsFactory = factories.getSkillsFactory();
        this.particleFactory = factories.getParticleFactory();
        this.animationsFactory = factories.getAnimationsFactory();
        this.behaviorFactory = factories.getBehaviorFactory();
    }

    public AgentBuilder create(String id) {
        Agent agent = agents.get(id);

        RawAnimationModel rawAnimationModel = animationsFactory.get(agent.getAnimationModel());
        Map<EquipSlot, Entity> equipment = buildEquipment(agent.getBody(), agent.getEquipment());

        AgentBuilder builder = new AgentBuilder(id, agent.getFactionId())
                .withVelocity(agent.getVelocity())
                .withAnimations(rawAnimationModel)
                .withEquipment(equipment, Messages.EQUIPMENT_CHANGED)
                .withParticles(particleFactory.getEffect(BLACK_SMOKE_PARTICLE_ID))
                .withBody();

        if (agent.hasBehavior()) {
            BehaviorInformation behaviorInformation = agent.getBehaviorInformation();
            String behaviorId = behaviorInformation.getId();
            BehaviorTaskDto behaviorTaskDto = behaviorFactory.get(behaviorId);
            BehaviorTask behaviorTask = behaviorTaskDto.build();
            // hay que setearle el blackboard desde el manager o algo asi...
            builder.withAI(behaviorTask);
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

    private Map<EquipSlot, Entity> buildEquipment(Map<EquipSlot, String> body, Map<EquipSlot, String> model) {
        Map<EquipSlot, Entity> equipment = new HashMap();

        for (Map.Entry<EquipSlot, String> entry : body.entrySet()) {
            Entity overlay = new Entity()
                    .add(new StackableSpriteComponent(SpriteFactory.get(entry.getValue())));

            equipment.put(entry.getKey(), overlay);
        }

        for (Map.Entry<EquipSlot, String> entry : model.entrySet()) {
            Entity item = itemFactory.getItem(entry.getValue()).build();
            equipment.put(entry.getKey(), item);
            engine.addEntity(item);
        }

        return equipment;
    }

    private List<Entity> buildBag(List<String> model) {
        List<Entity> bag = new LinkedList();

        for (String id : model) {
            Entity item = itemFactory.getItem(id).build();
            engine.addEntity(item);
            bag.add(item);
        }

        return bag;
    }
}
