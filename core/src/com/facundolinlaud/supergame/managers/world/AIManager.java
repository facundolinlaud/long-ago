package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.Decorator;
import com.badlogic.gdx.ai.btree.branch.*;
import com.badlogic.gdx.ai.btree.decorator.Repeat;
import com.facundolinlaud.supergame.ai.decisionmaking.*;
import com.facundolinlaud.supergame.ai.pathfinding.PathFinderAuthority;
import com.facundolinlaud.supergame.factory.AvailableSkillsFactory;

import java.util.HashMap;
import java.util.Map;

public class AIManager implements EntityListener {
    public static final int MELEE_SPELL = 0;
    public static final int MAGIC_SPELL = 1;

    private Map<Entity, BehaviorTree<?>> entitiesBehaviours;
    private AvailableSkillsFactory availableSkillsFactory;
    private PathFinderAuthority pathFinderAuthority;

    public AIManager(AvailableSkillsFactory availableSkillsFactory, MapManager mapManager, PhysicsManager physicsManager) {
        this.entitiesBehaviours = new HashMap<>();
        this.availableSkillsFactory = availableSkillsFactory;
        this.pathFinderAuthority = new PathFinderAuthority(mapManager, physicsManager);
    }

    @Override
    public void entityAdded(Entity entity) {
        this.entitiesBehaviours.put(entity, createBehaviourTree());
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

    private BehaviorTree<?> createBehaviourTree(){
        return createAggressiveBehavior();
    }

    private BehaviorTree<Blackboard> createAggressiveBehavior() {
        BehaviorTree<Blackboard> behaviorTree = new BehaviorTree<>();

        Selector<Blackboard> mainSelector = new Selector();
        Sequence<Blackboard> offensiveSequence = new Sequence<>();
        PlayerSeenTask playerSeenTask = new PlayerSeenTask();
        RandomSequence<Blackboard> attackSequence = new RandomSequence();
        Sequence<Blackboard> magicSequence = new Sequence();
        FaceTowardsPlayerTask faceTowardsPlayerTask = new FaceTowardsPlayerTask();
        AttackTask magicAttackTask = new AttackTask(this.availableSkillsFactory.getSkillById(MAGIC_SPELL));
        Sequence<Blackboard> meleeSequence = new Sequence<>();
        ApproachPlayerTask approachPlayerTask = new ApproachPlayerTask(pathFinderAuthority);
        AttackTask meleeAttackTask = new AttackTask(this.availableSkillsFactory.getSkillById(MELEE_SPELL));
        PatrolTask patrolTask = new PatrolTask(pathFinderAuthority);

        magicSequence.addChild(faceTowardsPlayerTask);
        magicSequence.addChild(magicAttackTask);

        meleeSequence.addChild(approachPlayerTask);
        meleeSequence.addChild(faceTowardsPlayerTask);
        meleeSequence.addChild(meleeAttackTask);

        attackSequence.addChild(magicSequence);
        attackSequence.addChild(meleeSequence);

        offensiveSequence.addChild(playerSeenTask);
        offensiveSequence.addChild(magicSequence);
        offensiveSequence.addChild(meleeSequence);

        // usar DynamicGuardSelector para interrumpir una patrulla!!

        mainSelector.addChild(offensiveSequence);
        mainSelector.addChild(patrolTask);

        behaviorTree.addChild(mainSelector);

        return behaviorTree;
    }
}