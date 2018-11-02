package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.ai.decisionmaking.*;
import com.facundolinlaud.supergame.ai.pathfinding.*;
import com.facundolinlaud.supergame.components.ai.AIComponent;
import com.facundolinlaud.supergame.factory.AvailableSkillsFactory;
import com.facundolinlaud.supergame.model.ai.BehaviourType;
import com.facundolinlaud.supergame.utils.Mappers;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AIManager implements EntityListener {
    private ComponentMapper<AIComponent> aim = Mappers.ai;

    private Map<Entity, BehaviorTree<?>> entitiesBehaviours;
    private AvailableSkillsFactory availableSkillsFactory;

    private IndexedAStarPathFinder<Node> pathfinder;
    private MapGraph mapGraph;

    private MapGraphCreator mapGraphCreator;

    private int columns;
    private int rows;

    public AIManager(AvailableSkillsFactory availableSkillsFactory, MapManager mapManager, PhysicsManager physicsManager) {
        this.entitiesBehaviours = new HashMap<>();
        this.availableSkillsFactory = availableSkillsFactory;

        this.mapGraphCreator = new MapGraphCreator(mapManager.getMap(), physicsManager.getObstacles());
        this.mapGraph = mapGraphCreator.createMapGraphFromTiledMap();
        this.pathfinder = new IndexedAStarPathFinder<>(mapGraph);

        MapProperties prop = mapManager.getMap().getProperties();
        this.columns = prop.get("width", Integer.class);
        this.rows = prop.get("height", Integer.class);
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
        FaceTowardsPlayerTask faceTowardsPlayerTask = new FaceTowardsPlayerTask();
        ApproachPlayer approachPlayer = new ApproachPlayer(this);
        AttackTask attackTask = new AttackTask(this.availableSkillsFactory);

        sequence.addChild(playerSeenTask);
        sequence.addChild(faceTowardsPlayerTask);
        sequence.addChild(approachPlayer);
        sequence.addChild(attackTask);

        behaviorTree.addChild(sequence);

        return behaviorTree;
    }

    public PathFinderResult searchNodePath(Vector2 from, Vector2 to){
        GraphPath<Node> outPath = new DefaultGraphPath<>();

        System.out.println(from + " a " + to);

        int fromNodeIndex = ((int) from.x) + ((int) from.y) * rows;
        int toNodeIndex = ((int) to.x) + ((int) to.y) * rows;

        Node fromNode = mapGraph.getNode(fromNodeIndex);
        Node toNode = mapGraph.getNode(toNodeIndex);

        boolean pathFound = pathfinder.searchNodePath(fromNode, toNode, new ManhattanDistance(), outPath);

        return new PathFinderResult(outPath, pathFound);
    }


}