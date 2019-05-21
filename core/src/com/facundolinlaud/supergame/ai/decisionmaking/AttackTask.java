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

public class AttackTask extends LeafTask<Blackboard> {
    private ComponentMapper<StatusComponent> sm = Mappers.status;

    private Skill skill;

    public AttackTask(Skill skill) {
        this.skill = skill;
    }

    @Override
    public Status execute() {
        Blackboard blackboard = getObject();
        Entity agent = blackboard.getAgent();

        StatusComponent agentStatus = sm.get(agent);
        Action action = agentStatus.getAction();


        /* TODO: Fix this. For some reason Return SUCCEEDED keeps tasks returning as SUCCEEDED instead of FRESH */
        if(status.equals(Status.FRESH) || Status.SUCCEEDED.equals(status)){
            if(Action.STANDING.equals(action)) {
                System.out.println("{AttackTask} RUNNING. Casting --> " + action.toString() + " (" + skill.getName() + ")");
                attackPlayer(agent, blackboard.getPlayerPosition());
            }
        }else{
            if(isCastingDone(action)){
                System.out.println("{AttackTask} SUCCEEDED. not fresh but casting done --> " + action);
                return Status.SUCCEEDED;
            }else{
                System.out.println("{AttackTask} RUNNING. not fresh and still casting --> " + action);
            }
        }

        return Status.RUNNING;

//        if(Action.STANDING.equals(action)){
//            attackPlayer(agent, blackboard.getPlayerPosition());
//            return Status.SUCCEEDED;
//        }else{
//            return Status.FAILED;
//        }

//        if(status.equals(Status.FRESH)){
//            if(canCast(action)) {
//                System.out.println("Fresh and not busy. Running. " + action.toString() + " (" + skill.getName() + ")");
//                attackPlayer(agent, blackboard.getPlayerPosition());
//                return Status.RUNNING;
//            } else { // no deberia suceder
//                System.out.println("Fresh and Busy. Failed. " + action.toString() + " (" + skill.getName() + ")");
//                return Status.FAILED;
//            }
//        }else{
//            if(isCastingDone(action)) {
//                System.out.println("Not fresh and not busy. Succeeded. " + action.toString() + " (" + skill.getName() + ")");
//                return Status.SUCCEEDED;
//            } else {
//                System.out.println("Not fresh and busy. Running. " + action.toString() + " (" + skill.getName() + ")");
//                return Status.RUNNING;
//            }
//        }


//        System.out.println(skill.getName() + " (" + status + ")");
//
//
//
//
//        if(status.equals(Status.RUNNING) && !action.isBusy())
//            return Status.SUCCEEDED;
//
//        if(action.isBusy()) {
//            System.out.println("isBusy => SUCCEEDED");
//            return Status.FAILED;
//        }
//
//
//        attackPlayer(agent, playerPosition);
//
//        System.out.println("SUCCEEDED");
//        return Status.RUNNING;
    }

    private boolean isCastingDone(Action action) {
        return !action.isCasting();
    }

    private boolean canCast(Action action) {
        return Action.STANDING.equals(action);
    }

    void attackPlayer(Entity agent, Vector2 playerPosition){
//        agent.add(new SkillClickComponent(playerPosition));
//        agent.add(new SkillCastingComponent(skill));
        SkillType skillType = skill.getSkillType();

        if(skillType == SkillType.SPELL || skillType == SkillType.PROJECTILE) {
            agent.add(new SkillClickComponent(playerPosition));
        }

//        agent.add(new SkillCastingComponent(skill));
        agent.add(new SkillCastingRequestComponent(skill));
    }

    @Override
    protected Task<Blackboard> copyTo(Task<Blackboard> task) {
        return new AttackTask(this.skill);
    }
}
