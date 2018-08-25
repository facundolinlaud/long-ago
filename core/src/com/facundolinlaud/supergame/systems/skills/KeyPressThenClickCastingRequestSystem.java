package com.facundolinlaud.supergame.systems.skills;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.skills.SkillCastingRequestComponent;
import com.facundolinlaud.supergame.components.skills.SkillClickComponent;
import com.facundolinlaud.supergame.managers.world.PlayerInputObserver;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.strategies.skills.castingrequest.KeyPressThenClickCastingRequestStrategy;
import com.facundolinlaud.supergame.utils.Mappers;

public class KeyPressThenClickCastingRequestSystem extends IteratingSystem {
    private ComponentMapper<SkillCastingRequestComponent> scrm = Mappers.skillCastingRequest;
    private ComponentMapper<SkillClickComponent> scm = Mappers.skillClick;
    private ComponentMapper<PositionComponent> pm = Mappers.position;

    private KeyPressThenClickCastingRequestStrategy requestStrategy;
    private PlayerInputObserver playerInputObserver;

    public KeyPressThenClickCastingRequestSystem(PlayerInputObserver playerInputObserver) {
        super(Family.all(SkillCastingRequestComponent.class, SkillClickComponent.class).get());

        this.requestStrategy = new KeyPressThenClickCastingRequestStrategy();
        this.playerInputObserver = playerInputObserver;
    }

    @Override
    protected void processEntity(Entity caster, float deltaTime) {
        SkillCastingRequestComponent requestComponent = scrm.get(caster);
        SkillClickComponent clickComponent = scm.get(caster);

        Skill skill = requestComponent.getRequestedSkill();

        if(playerInputObserver.isClicking()){
            Vector2 clickedPosition = calculateClickedPositionInMeters(caster);
            clickComponent.registerClick(clickedPosition);
            this.requestStrategy.attemptToCast(caster, skill);
        }
    }

    private Vector2 calculateClickedPositionInMeters(Entity caster){
        PositionComponent positionComponent = pm.get(caster);
        Vector2 clickedPosition = playerInputObserver.getLatestClickedPositionInMetersRelativeToScreenCenter();

        System.out.println("clicked: " + clickedPosition.x + ", " + clickedPosition.y);
        System.out.println("caster: " + positionComponent.x + ", " + positionComponent.y);
        clickedPosition.add(positionComponent.x, positionComponent.y);

        System.out.println("result: " + clickedPosition);
        return clickedPosition;
    }
}
