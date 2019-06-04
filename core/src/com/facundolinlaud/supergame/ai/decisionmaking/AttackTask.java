package com.facundolinlaud.supergame.ai.decisionmaking;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.components.skills.SkillCastingComponent;
import com.facundolinlaud.supergame.components.skills.SkillCastingRequestComponent;
import com.facundolinlaud.supergame.components.skills.SkillClickComponent;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.model.skill.SkillType;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.utils.Mappers;

import java.util.List;
import java.util.Random;

public class AttackTask extends LeafTask<Blackboard> {
    private ComponentMapper<StatusComponent> sm = Mappers.status;

    private List<Skill> skills;
    private Random rand;

    public AttackTask(List<Skill> skills) {
        this.skills = skills;
        this.rand = new Random();
    }

    @Override
    public Status execute() {
        Blackboard blackboard = getObject();
        Entity agent = blackboard.getAgent();

        StatusComponent agentStatus = sm.get(agent);
        Action action = agentStatus.getAction();

        /* TODO: Fix this. For some reason Return SUCCEEDED keeps tasks returning as SUCCEEDED instead of FRESH */
        if(firstTimeExecutingTask()){
            if(canCast(action))
                attackPlayer(agent, blackboard.getPlayerPosition());
        }else{
            if(isCastingDone(action))
                return Status.SUCCEEDED;
        }

        return Status.RUNNING;
    }

    private boolean firstTimeExecutingTask() {
        return status.equals(Status.FRESH) || Status.SUCCEEDED.equals(status);
    }

    private boolean canCast(Action action) {
        return Action.STANDING.equals(action);
    }

    private boolean isCastingDone(Action action) {
        return !action.isCasting();
    }

    void attackPlayer(Entity agent, Vector2 playerPosition){
        Skill skill = chooseSkill();
        SkillType skillType = skill.getSkillType();

        if(skillType == SkillType.SPELL || skillType == SkillType.PROJECTILE)
            agent.add(new SkillClickComponent(playerPosition));

        agent.add(new SkillCastingRequestComponent(skill));
    }

    private Skill chooseSkill() {
        return skills.get(rand.nextInt(skills.size()));
    }

    @Override
    protected Task<Blackboard> copyTo(Task<Blackboard> task) {
        return new AttackTask(this.skills);
    }
}
