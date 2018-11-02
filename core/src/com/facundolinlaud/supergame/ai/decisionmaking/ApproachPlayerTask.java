package com.facundolinlaud.supergame.ai.decisionmaking;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.ai.pathfinding.Node;
import com.facundolinlaud.supergame.ai.pathfinding.PathFinderAuthority;
import com.facundolinlaud.supergame.ai.pathfinding.PathFinderResult;
import com.facundolinlaud.supergame.components.ai.AIMoveToComponent;

public class ApproachPlayerTask extends LeafTask<Blackboard> {
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

        String out = result.isFound() + ", pos= " + agentPosition + ":";
        if(result.isFound()){
            for(int i = 0; i < result.getPath().getCount(); i++){
                out += result.getPath().get(i).toString() + ", ";
            }
        }
        System.out.println(out);

        if(!result.isFound())
            return Status.FAILED;

        if(result.getPath().getCount() <= MINIMUM_DISTANCE_FROM_PLAYER_DESIRED)
            return Status.SUCCEEDED;

        GraphPath<Node> path = result.getPath();
        Entity agentEntity = blackboard.getAgent();

        agentEntity.add(new AIMoveToComponent(path.get(1)));

        return Status.RUNNING;
    }

    @Override
    protected Task<Blackboard> copyTo(Task<Blackboard> task) {
        return new ApproachPlayerTask(pathFinderAuthority);
    }
}
