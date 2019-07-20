package com.facundolinlaud.supergame.systems.skills;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.components.skills.SkillCastingRequestComponent;
import com.facundolinlaud.supergame.components.skills.SkillClickComponent;
import com.facundolinlaud.supergame.managers.world.PlayerInputManager;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.model.skill.TwoClickInformation;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.model.status.Direction;
import com.facundolinlaud.supergame.strategies.skills.castingrequest.KeyPressThenClickCastingRequestStrategy;
import com.facundolinlaud.supergame.utils.Mappers;

public class KeyPressThenClickCastingRequestSystem extends IteratingSystem {
    private ComponentMapper<SkillCastingRequestComponent> scrm = Mappers.skillCastingRequest;
    private ComponentMapper<SkillClickComponent> scm = Mappers.skillClick;
    private ComponentMapper<PositionComponent> pm = Mappers.position;
    private ComponentMapper<StatusComponent> sm = Mappers.status;

    private KeyPressThenClickCastingRequestStrategy requestStrategy;
    private PlayerInputManager playerInputObserver;

    public KeyPressThenClickCastingRequestSystem(PlayerInputManager playerInputObserver) {
        super(Family.all(SkillCastingRequestComponent.class, SkillClickComponent.class).get());

        this.requestStrategy = new KeyPressThenClickCastingRequestStrategy();
        this.playerInputObserver = playerInputObserver;
    }

    @Override
    protected void processEntity(Entity caster, float deltaTime) {
        SkillCastingRequestComponent requestComponent = scrm.get(caster);
        SkillClickComponent clickComponent = scm.get(caster);

        Skill skill = requestComponent.getRequestedSkill();

        /* I should take the ClickComponent away for the main player */
        if(clickComponent.isRegistered()) {
            this.requestStrategy.attemptToCast(caster, skill);
        } else if(playerInputObserver.isClicking()) {
            Vector2 clickedPosition = calculateClickedPositionInMeters(caster);
            clickComponent.registerClick(clickedPosition);
            this.requestStrategy.attemptToCast(caster, skill);
        } else if(clickComponent.isJustCreated()){
            applyWaitingForClickAction(caster, skill);
            clickComponent.setJustCreated(false);
        } else {
            faceTowardsCursorPosition(caster);
        }
    }

    private Vector2 calculateClickedPositionInMeters(Entity caster){
        PositionComponent positionComponent = pm.get(caster);
        Vector2 clickedPosition = playerInputObserver.getLatestClickedPositionInMetersRelativeToScreenCenter();
        clickedPosition.add(positionComponent.x, positionComponent.y);

        return clickedPosition;
    }

    private void applyWaitingForClickAction(Entity caster, Skill skill) {
        TwoClickInformation twoClickInformation = skill.getTwoClickInformation();
        Action action = twoClickInformation.getWaitingForClickAction();

        StatusComponent statusComponent = sm.get(caster);
        statusComponent.setAction(action);
    }

    private void faceTowardsCursorPosition(Entity caster){
        Vector2 cursorPosition = playerInputObserver.getCursorPositionInMetersRelativeToScreenCenter();
        Direction newDirection = resolveFacingDirection(cursorPosition);

        StatusComponent statusComponent = sm.get(caster);
        statusComponent.setDirection(newDirection);
    }

    private Direction resolveFacingDirection(Vector2 cursor){
        if(Math.abs(cursor.x) > Math.abs(cursor.y)){
            if(cursor.x > 0)
                return Direction.RIGHT;
            else
                return Direction.LEFT;
        }else{
            if(cursor.y > 0)
                return Direction.DOWN;
            else
                return Direction.UP;
        }
    }
}
