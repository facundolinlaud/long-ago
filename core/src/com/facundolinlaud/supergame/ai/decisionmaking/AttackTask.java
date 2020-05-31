package com.facundolinlaud.supergame.ai.decisionmaking;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.ManaComponent;
import com.facundolinlaud.supergame.components.SkillsComponent;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.components.TargetComponent;
import com.facundolinlaud.supergame.managers.world.SkillsManager;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.model.skill.SkillType;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.utils.Mappers;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class AttackTask extends LeafTask<Blackboard> {
    private ComponentMapper<StatusComponent> sm = Mappers.status;
    private ComponentMapper<ManaComponent> mm = Mappers.mana;
    private ComponentMapper<SkillsComponent> ssm = Mappers.skills;

    private Random rand;

    public AttackTask() {
        this.rand = new Random();
    }

    @Override
    public Status execute() {
        Blackboard blackboard = getObject();
        Entity attacker = blackboard.getAgent();
        SkillsManager skillsManager = blackboard.getSkillsManager();

        StatusComponent agentStatus = sm.get(attacker);
        Action action = agentStatus.getAction();

        /* TODO: Fix this. For some reason Return SUCCEEDED keeps tasks returning as SUCCEEDED instead of FRESH */
        if (firstTimeExecutingTask()) {
            if (canCast(action)) {
                Skill skill = getBestFittingSkill(attacker);

                if (skill == null)
                    return Status.FAILED;

                attackPlayer(attacker, blackboard.getPlayerPosition(), skill, skillsManager);
            }
        } else {
            if (isCastingDone(action))
                return Status.SUCCEEDED;
        }

        return Status.RUNNING;
    }

    private Skill getBestFittingSkill(Entity caster) {
        SkillsComponent skills = ssm.get(caster);
        ManaComponent manaComponent = mm.get(caster);

        List<Skill> options = skills.getAvailableSkills().stream()
                .filter(skill -> manaComponent.canCast(skill)).collect(Collectors.toList());

        if (options.isEmpty())
            return null;

        return random(options);
    }

    private Skill random(List<Skill> options) {
        return options.get(rand.nextInt(options.size()));
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

    void attackPlayer(Entity attacker, Vector2 playerPosition, Skill skill, SkillsManager skillsManager) {
        SkillType skillType = skill.getSkillType();

        if (skillType == SkillType.SPELL || skillType == SkillType.PROJECTILE)
            attacker.add(new TargetComponent(playerPosition));

        skillsManager.requestCasting(attacker, skill);
    }

    @Override
    protected Task<Blackboard> copyTo(Task<Blackboard> task) {
        return new AttackTask();
    }
}
