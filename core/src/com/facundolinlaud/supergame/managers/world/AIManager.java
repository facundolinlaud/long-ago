package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.branch.RandomSelector;
import com.badlogic.gdx.ai.btree.branch.Selector;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.facundolinlaud.supergame.ai.decisionmaking.*;
import com.facundolinlaud.supergame.ai.pathfinding.PathFinderAuthority;
import com.facundolinlaud.supergame.behaviortree.PoolableTaskManager;
import com.facundolinlaud.supergame.components.SkillsComponent;
import com.facundolinlaud.supergame.components.ai.AIComponent;
import com.facundolinlaud.supergame.utils.Mappers;

import java.util.HashMap;
import java.util.Map;

public class AIManager extends PoolableTaskManager implements EntityListener {
    private ComponentMapper<AIComponent> aim = Mappers.ai;
    private ComponentMapper<SkillsComponent> sm = Mappers.skills;

    private Map<Entity, BehaviorTree<?>> entitiesBehaviours;
    private PathFinderAuthority pathFinderAuthority;

    public AIManager(MapManager mapManager, PhysicsManager physicsManager) {
        this.entitiesBehaviours = new HashMap();
        this.pathFinderAuthority = new PathFinderAuthority(mapManager, physicsManager);
    }

    @Override
    public void entityAdded(Entity agent) {
        AIComponent aiComponent = aim.get(agent);
        BehaviorTree<?> behaviorTree;

        switch (aiComponent.getBehaviorType()) {
            default:
                behaviorTree = buildAggressiveBehavior(agent);
        }

        this.entitiesBehaviours.put(agent, behaviorTree);
    }

    @Override
    public void entityRemoved(Entity agent) {
        entitiesBehaviours.remove(agent);
    }

    public void step(Entity entity, Blackboard blackboard) {
        BehaviorTree behaviorTree = entitiesBehaviours.get(entity);
        behaviorTree.setObject(blackboard);
        behaviorTree.step();
    }

    /* TODO: Move to text format */
    private BehaviorTree<Blackboard> buildAggressiveBehavior(Entity agent) {
        SkillsComponent skillsComponent = sm.get(agent);
        boolean hasMeleeSkills = !skillsComponent.getMeleeSkills().isEmpty();
        boolean hasRangedSkills = !skillsComponent.getRangedSkills().isEmpty();

        BehaviorTree<Blackboard> behaviorTree = new BehaviorTree<>();
        Selector<Blackboard> mainSelector = new Selector();
        Sequence<Blackboard> offensiveSequence = new Sequence<>();
        PlayerSeenTask playerSeenTask = new PlayerSeenTask();
        RandomSelector<Blackboard> attackRandomSelector = new RandomSelector();
        Sequence<Blackboard> rangedSequence = new Sequence();
        FaceTowardsPlayerTask faceTowardsPlayerTask = new FaceTowardsPlayerTask();
        AttackTask rangedAttackTask = new AttackTask();
        Sequence<Blackboard> meleeSequence = new Sequence<>();
        ApproachPlayerTask approachPlayerTask = new ApproachPlayerTask(pathFinderAuthority);
        AttackTask meleeAttackTask = new AttackTask();

        PatrolTask patrolTask = new PatrolTask(pathFinderAuthority);

        rangedSequence.addChild(faceTowardsPlayerTask);
        rangedSequence.addChild(rangedAttackTask);

        meleeSequence.addChild(approachPlayerTask);
        meleeSequence.addChild(faceTowardsPlayerTask);
        meleeSequence.addChild(meleeAttackTask);

        if (hasRangedSkills)
            attackRandomSelector.addChild(rangedSequence);

        if (hasMeleeSkills)
            attackRandomSelector.addChild(meleeSequence);

        offensiveSequence.addChild(playerSeenTask);
        offensiveSequence.addChild(attackRandomSelector);

        mainSelector.addChild(offensiveSequence);
        mainSelector.addChild(patrolTask);

        behaviorTree.addChild(mainSelector);

        return behaviorTree;
    }

    public PathFinderAuthority getPathFinderAuthority() {
        return pathFinderAuthority;
    }
}