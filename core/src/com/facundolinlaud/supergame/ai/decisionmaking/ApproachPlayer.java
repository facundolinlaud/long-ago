package com.facundolinlaud.supergame.ai.decisionmaking;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.ai.pathfinding.Node;
import com.facundolinlaud.supergame.ai.pathfinding.PathFinderResult;
import com.facundolinlaud.supergame.managers.world.AIManager;

public class ApproachPlayer extends LeafTask<Blackboard> {
    private AIManager aiManager;

    public ApproachPlayer(AIManager aiManager) {
        this.aiManager = aiManager;
    }

    @Override
    public Status execute() {
        Blackboard blackboard = getObject();
        Vector2 agentPosition = blackboard.getAgentPosition();
        Vector2 playerPosition = blackboard.getPlayerPosition();

        PathFinderResult result = aiManager.searchNodePath(agentPosition, playerPosition);
        GraphPath<Node> path = result.getPath();

        System.out.println("##########################################");
        for(int i = 0; i < path.getCount(); i++) {
            Node node = path.get(i);
            System.out.println("[" + i + "] (" + node.getX() + ", " + node.getY() + ")");
        }

        return Status.SUCCEEDED;
    }

    @Override
    protected Task<Blackboard> copyTo(Task<Blackboard> task) {
        return new ApproachPlayer(aiManager);
    }
}
