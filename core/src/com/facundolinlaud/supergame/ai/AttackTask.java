package com.facundolinlaud.supergame.ai;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.components.skills.SkillCastingComponent;
import com.facundolinlaud.supergame.components.skills.SkillClickComponent;
import com.facundolinlaud.supergame.factory.AvailableSkillsFactory;
import com.facundolinlaud.supergame.utils.Mappers;

public class AttackTask extends LeafTask<Blackboard> {
    public static final int BASIC_SPELL = 1;
    private ComponentMapper<StatusComponent> sm = Mappers.status;

    private AvailableSkillsFactory availableSkillsFactory;

    public AttackTask(AvailableSkillsFactory availableSkillsFactory) {
        this.availableSkillsFactory = availableSkillsFactory;
    }

    @Override
    public Status execute() {
        Blackboard blackboard = getObject();

        Entity agent = blackboard.getAgent();
        StatusComponent agentStatus = sm.get(agent);

        if(!agentStatus.getAction().isBusy()){
            Vector2 playerPosition = blackboard.getPlayerPosition();
            attackPlayer(agent, playerPosition);
        }

        return Status.SUCCEEDED;
//        super.getObject().attack(); // WAT WAT WAT WAT WAT WAT ok me olvide de esto
    }

    void attackPlayer(Entity agent, Vector2 playerPosition){
        agent.add(new SkillClickComponent(playerPosition));
        agent.add(new SkillCastingComponent(availableSkillsFactory.getSkillById(BASIC_SPELL)));
    }

    @Override
    protected Task<Blackboard> copyTo(Task<Blackboard> task) {
        return new AttackTask(this.availableSkillsFactory);
    }
}
