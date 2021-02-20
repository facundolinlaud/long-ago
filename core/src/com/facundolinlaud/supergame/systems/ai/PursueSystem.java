package com.facundolinlaud.supergame.systems.ai;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.ai.pathfinding.LinkedGraphPath;
import com.facundolinlaud.supergame.ai.pathfinding.Node;
import com.facundolinlaud.supergame.ai.pathfinding.PathFinderResult;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.ai.PursueComponent;
import com.facundolinlaud.supergame.components.ai.TraverseComponent;
import com.facundolinlaud.supergame.managers.world.PathFindingManager;
import com.facundolinlaud.supergame.utils.Mappers;

public class PursueSystem extends IntervalIteratingSystem {
    private static final float INTERVAL_IN_SECONDS = 0.4f;

    private ComponentMapper<TraverseComponent> tm = Mappers.traverse;
    private ComponentMapper<PositionComponent> pom = Mappers.position;
    private ComponentMapper<PursueComponent> pum = Mappers.pursue;

    private PathFindingManager pathFindingManager;

    public PursueSystem(PathFindingManager pathFindingManager) {
        super(Family.all(PursueComponent.class).get(), INTERVAL_IN_SECONDS);
        this.pathFindingManager = pathFindingManager;
    }

    @Override
    protected void processEntity(Entity agent) {
        PursueComponent pursuableComponent = pum.get(agent);
        Entity pursuable = pursuableComponent.getPursuable();
        Vector2 agentPosition = pom.get(agent).getPosition();
        Vector2 pursuablePosition = pom.get(pursuable).getPosition();
        float seekedProximity = pursuableComponent.getSeekedProximity();

        PathFinderResult result = pathFindingManager.calculate(agentPosition, pursuablePosition);

        if (!result.isFound()) {
            agent.remove(PursueComponent.class);
            pursuableComponent.getOnFail().run();
            return;
        }

        LinkedGraphPath<Node> path = result.getPath();

        if (tm.has(agent)) {
            tm.get(agent).setPath(path);
        } else {
            Runnable onArrive = () -> {
                pursuableComponent.getOnArrive().run();
                agent.remove(PursueComponent.class);
            };

            agent.add(new TraverseComponent(path, seekedProximity, onArrive));
        }
    }
}
