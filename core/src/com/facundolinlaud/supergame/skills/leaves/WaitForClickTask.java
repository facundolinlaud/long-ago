package com.facundolinlaud.supergame.skills.leaves;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.behaviortree.PoolableTask;
import com.facundolinlaud.supergame.behaviortree.stack.Value;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.components.TargetComponent;
import com.facundolinlaud.supergame.managers.world.CameraManager;
import com.facundolinlaud.supergame.model.status.Direction;
import com.facundolinlaud.supergame.skills.SkillBlackboard;
import com.facundolinlaud.supergame.utils.Mappers;

import java.util.Vector;

import static com.facundolinlaud.supergame.utils.Dimensions.calculateGlobalPositionInPixelsToMetersRelativeToCenter;
import static com.facundolinlaud.supergame.utils.Dimensions.toMeters;
import static com.facundolinlaud.supergame.utils.PositionUtils.getFacingDirection;

/**
 * Pops: nothing
 * Pushes: two float-values corresponding to the x and y clicked position values respectively
 */
public class WaitForClickTask extends PoolableTask<SkillBlackboard> {
    private ComponentMapper<TargetComponent> tm = Mappers.target;
    private ComponentMapper<PositionComponent> pm = Mappers.position;
    private ComponentMapper<StatusComponent> sm = Mappers.status;

    private Entity caster;
    private CameraManager cameraManager;

    @Override
    protected void onBlackboardAvailable(SkillBlackboard blackboard) {
        this.cameraManager = blackboard.getCameraManager();
    }

    @Override
    public void activate() {
        super.activate();
        caster = getBlackboard().getCaster();
    }

    @Override
    public void tick(float delta) {
        TargetComponent targetComponent = tm.get(caster);

        face(targetComponent);
        pushIfClicking(targetComponent);
    }

    private void face(TargetComponent targetComponent) {
        PositionComponent positionComponent = pm.get(caster);
        Vector2 playerPosition = positionComponent.getPosition();
        Vector2 clickedPosition = targetComponent.getPosition();
        Vector2 delta = new Vector2(clickedPosition.x - playerPosition.x, playerPosition.y - clickedPosition.y);

        Direction newDirection = getFacingDirection(delta);

        StatusComponent statusComponent = sm.get(caster);
        statusComponent.setDirection(newDirection);
    }

    private void pushIfClicking(TargetComponent targetComponent) {
        if (targetComponent.isClicking()) {
            Vector2 clickedPosition = targetComponent.getPosition();
            stack.push(new Value(clickedPosition.x));
            stack.push(new Value(clickedPosition.y));

            completed();
        }
    }

}
