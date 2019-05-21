package com.facundolinlaud.supergame.ai.decisionmaking;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.ai.pathfinding.LinkedGraphPath;
import com.facundolinlaud.supergame.ai.pathfinding.Node;
import com.facundolinlaud.supergame.ai.pathfinding.PathFinderAuthority;
import com.facundolinlaud.supergame.ai.pathfinding.PathFinderResult;
import com.facundolinlaud.supergame.components.ai.AIMoveToComponent;
import com.facundolinlaud.supergame.utils.Mappers;

public class ApproachPlayerTask extends LeafTask<Blackboard> {
    private ComponentMapper<AIMoveToComponent> mtm = Mappers.aiMoveTo;

    /* Counts starting and ending tile as well */
    public static final int MINIMUM_DISTANCE_FROM_PLAYER_DESIRED = 2;

    private PathFinderAuthority pathFinderAuthority;

    public ApproachPlayerTask(PathFinderAuthority pathFinderAuthority) {
        this.pathFinderAuthority = pathFinderAuthority;
    }

    @Override
    public Status execute() {
        Blackboard blackboard = getObject();
        Vector2 agentPosition = blackboard.getAgentPosition();
        Vector2 playerPosition = blackboard.getPlayerPosition();

        PathFinderResult result = pathFinderAuthority.searchNodePath(agentPosition, playerPosition);

//        traceDebug(agentPosition, result);

        if(!result.isFound()) {
            System.out.println("{ApproachPlayerTask} FAILED");
            return Status.FAILED;
        }

        if(result.getPath().getCount() <= MINIMUM_DISTANCE_FROM_PLAYER_DESIRED) {
            System.out.println("{ApproachPlayerTask} SUCCEEDED");
            return Status.SUCCEEDED;
        }

        LinkedGraphPath<Node> path = result.getPath();
        Entity agent = blackboard.getAgent();
        updateAgentPath(agent, path);

        System.out.println("{ApproachPlayerTask} RUNNING");
        return Status.RUNNING;
    }

    private void updateAgentPath(Entity agent, LinkedGraphPath<Node> path) {
        if(mtm.has(agent))
            mtm.get(agent).setPath(path);
        else
            agent.add(new AIMoveToComponent(path));
    }

    private void traceDebug(Vector2 agentPosition, PathFinderResult result) {
        String out = result.isFound() + ", pos= " + agentPosition + ":";
        if(result.isFound()){
            for(int i = 0; i < result.getPath().getCount(); i++){
                out += result.getPath().get(i).toString() + ", ";
            }
        }

        System.out.println(out);
    }

    @Override
    protected Task<Blackboard> copyTo(Task<Blackboard> task) {
        return new ApproachPlayerTask(pathFinderAuthority);
    }
}
