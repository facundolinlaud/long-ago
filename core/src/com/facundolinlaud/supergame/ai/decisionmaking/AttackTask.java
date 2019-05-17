package com.facundolinlaud.supergame.ai.decisionmaking;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.components.skills.SkillCastingComponent;
import com.facundolinlaud.supergame.components.skills.SkillClickComponent;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.utils.Mappers;

public class AttackTask extends LeafTask<Blackboard> {
    private ComponentMapper<StatusComponent> sm = Mappers.status;

    private Skill skill;
    private boolean waitingForSkillCasting = false;
    private boolean castingHasBegun = false;

    public AttackTask(Skill skill) {
        this.skill = skill;
    }

    @Override
    public Status execute() {
        if(status.equals(Status.FRESH)){
            this.waitingForSkillCasting = false;
            this.castingHasBegun = false;
        }

        Blackboard blackboard = getObject();

        Entity agent = blackboard.getAgent();
        StatusComponent agentStatus = sm.get(agent);
        Action action = agentStatus.getAction();

        /* Weird state machine logic because there are like 2 ticks from skill casting request to cast execution */
        /* In order to wait for the player to end the attack and then succeed(), this logic is necessary */
        if(waitingForSkillCasting) {
            if (action.isCasting()) {
                castingHasBegun = true;

                return Status.RUNNING;
            } else if (castingHasBegun) {
                this.waitingForSkillCasting = false;
                this.castingHasBegun = false;

                return Status.SUCCEEDED;
            } else {
                return Status.RUNNING;
            }
        }

        Vector2 playerPosition = blackboard.getPlayerPosition();
        attackPlayer(agent, playerPosition);

        return Status.RUNNING;
    }

    void attackPlayer(Entity agent, Vector2 playerPosition){
        agent.add(new SkillClickComponent(playerPosition));
        agent.add(new SkillCastingComponent(skill));

        this.waitingForSkillCasting = true;
    }

    @Override
    protected Task<Blackboard> copyTo(Task<Blackboard> task) {
        return new AttackTask(this.skill);
    }
}
