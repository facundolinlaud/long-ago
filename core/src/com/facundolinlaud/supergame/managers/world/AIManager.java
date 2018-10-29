package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.facundolinlaud.supergame.ai.AttackTask;
import com.facundolinlaud.supergame.ai.Blackboard;
import com.facundolinlaud.supergame.ai.PlayerSeenTask;
import com.facundolinlaud.supergame.components.ai.AIComponent;
import com.facundolinlaud.supergame.factory.AvailableSkillsFactory;
import com.facundolinlaud.supergame.model.ai.BehaviourType;
import com.facundolinlaud.supergame.utils.Mappers;

import java.util.HashMap;
import java.util.Map;

public class AIManager implements EntityListener {
    private ComponentMapper<AIComponent> aim = Mappers.ai;

    private Map<Entity, BehaviorTree<?>> entitiesBehaviours;
    private AvailableSkillsFactory availableSkillsFactory;

    public AIManager(AvailableSkillsFactory availableSkillsFactory) {
        this.entitiesBehaviours = new HashMap<>();
        this.availableSkillsFactory = availableSkillsFactory;
    }

    @Override
    public void entityAdded(Entity entity) {
        AIComponent aiComponent = aim.get(entity);

        BehaviourType behaviourType = aiComponent.getBehaviourType();
        this.entitiesBehaviours.put(entity, createBehaviourTree(behaviourType));
    }

    @Override
    public void entityRemoved(Entity entity) {
        entitiesBehaviours.remove(entity);
    }

    public void step(Entity entity, Blackboard blackboard){
        BehaviorTree behaviorTree = entitiesBehaviours.get(entity);
        behaviorTree.setObject(blackboard);
        behaviorTree.step();
    }

    private BehaviorTree<?> createBehaviourTree(BehaviourType behaviourType){
        return createAggressiveBehavior();
    }

    private BehaviorTree<Blackboard> createAggressiveBehavior() {
        BehaviorTree<Blackboard> behaviorTree = new BehaviorTree<>();

        Sequence<Blackboard> sequence = new Sequence<>();
        PlayerSeenTask playerSeenTask = new PlayerSeenTask();
        AttackTask attackTask = new AttackTask(this.availableSkillsFactory);

        sequence.addChild(playerSeenTask);
        sequence.addChild(attackTask);

        behaviorTree.addChild(sequence);

        return behaviorTree;
    }
}