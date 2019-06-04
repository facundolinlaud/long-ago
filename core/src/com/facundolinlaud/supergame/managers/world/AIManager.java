package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.branch.*;
import com.facundolinlaud.supergame.ai.decisionmaking.*;
import com.facundolinlaud.supergame.ai.pathfinding.PathFinderAuthority;
import com.facundolinlaud.supergame.components.ai.AIComponent;
import com.facundolinlaud.supergame.factory.SkillsFactory;
import com.facundolinlaud.supergame.utils.Mappers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AIManager implements EntityListener {
    private ComponentMapper<AIComponent> aim = Mappers.ai;

    private Map<Entity, BehaviorTree<?>> entitiesBehaviours;
    private SkillsFactory skillsFactory;
    private PathFinderAuthority pathFinderAuthority;

    public AIManager(SkillsFactory skillsFactory, MapManager mapManager, PhysicsManager physicsManager) {
        this.entitiesBehaviours = new HashMap<>();
        this.skillsFactory = skillsFactory;
        this.pathFinderAuthority = new PathFinderAuthority(mapManager, physicsManager);
    }

    @Override
    public void entityAdded(Entity agent) {
        AIComponent aiComponent = aim.get(agent);
        BehaviorTree<?> behaviorTree;

        switch(aiComponent.getBehaviorType()) {
            default:
                behaviorTree = buildAggressiveBehavior(aiComponent.getMeleeSkills(),
                        aiComponent.getRangedSkills());
        }

        this.entitiesBehaviours.put(agent, behaviorTree);
    }

    @Override
    public void entityRemoved(Entity agent) {
        entitiesBehaviours.remove(agent);
    }

    public void step(Entity entity, Blackboard blackboard){
        BehaviorTree behaviorTree = entitiesBehaviours.get(entity);
        behaviorTree.setObject(blackboard);
        behaviorTree.step();
    }

    /* TODO: Move to text format */
    private BehaviorTree<Blackboard> buildAggressiveBehavior(List<Integer> meleeSkills, List<Integer> rangedSkills) {
        BehaviorTree<Blackboard> behaviorTree = new BehaviorTree<>();

        Selector<Blackboard> mainSelector = new Selector();
        Sequence<Blackboard> offensiveSequence = new Sequence<>();
        PlayerSeenTask playerSeenTask = new PlayerSeenTask();
        RandomSelector<Blackboard> attackRandomSelector = new RandomSelector();
        Sequence<Blackboard> magicSequence = new Sequence();
        FaceTowardsPlayerTask faceTowardsPlayerTask = new FaceTowardsPlayerTask();
        AttackTask rangedAttackTask = new AttackTask(skillsFactory.get(rangedSkills));
        Sequence<Blackboard> meleeSequence = new Sequence<>();
        ApproachPlayerTask approachPlayerTask = new ApproachPlayerTask(pathFinderAuthority);
        AttackTask meleeAttackTask = new AttackTask(skillsFactory.get(meleeSkills));

        PatrolTask patrolTask = new PatrolTask(pathFinderAuthority);

        magicSequence.addChild(faceTowardsPlayerTask);
        magicSequence.addChild(rangedAttackTask);

        meleeSequence.addChild(approachPlayerTask);
        meleeSequence.addChild(faceTowardsPlayerTask);
        meleeSequence.addChild(meleeAttackTask);

        attackRandomSelector.addChild(magicSequence);
        attackRandomSelector.addChild(meleeSequence);

        offensiveSequence.addChild(playerSeenTask);
        offensiveSequence.addChild(attackRandomSelector);

        mainSelector.addChild(offensiveSequence);
        mainSelector.addChild(patrolTask);

        behaviorTree.addChild(mainSelector);

        return behaviorTree;
    }
}