package com.facundolinlaud.supergame.behaviortree.leaves;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.behaviortree.PoolableTask;
import com.facundolinlaud.supergame.behaviortree.composites.Blackboard;
import com.facundolinlaud.supergame.behaviortree.stack.Value;
import com.facundolinlaud.supergame.components.OverlayRenderComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.components.TargetComponent;
import com.facundolinlaud.supergame.model.status.Direction;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.shape.Shape;

import static com.facundolinlaud.supergame.utils.PositionUtils.getFacingDirection;

/**
 * Pops: nothing
 * Pushes: a position-value corresponding to the clicked position
 */
public class WaitForClickTask extends PoolableTask<Blackboard> {
    private ComponentMapper<TargetComponent> tm = Mappers.target;
    private ComponentMapper<PositionComponent> pm = Mappers.position;
    private ComponentMapper<StatusComponent> sm = Mappers.status;
    private ComponentMapper<OverlayRenderComponent> orm = Mappers.overlayRender;

    private Entity agent;
    private Shape areaShape;
    private Color areaColor;

    public WaitForClickTask() {
    }

    public WaitForClickTask(Shape areaShape, Color areaColor) {
        this.areaShape = areaShape;
        this.areaColor = areaColor;
    }

    @Override
    protected void onBlackboardAvailable(Blackboard blackboard) {
        agent = getBlackboard().getAgent();
    }

    @Override
    public void activate() {
        super.activate();

        if (areaShape != null) {
            agent.add(new OverlayRenderComponent(areaShape, areaColor));
        }
    }

    @Override
    public void tick(float delta) {
        TargetComponent targetComponent = tm.get(agent);
        face(targetComponent);
        pushIfClicking(targetComponent);
    }

    private void face(TargetComponent targetComponent) {
        PositionComponent positionComponent = pm.get(agent);
        Vector2 playerPosition = positionComponent.getPosition();
        Vector2 clickedPosition = targetComponent.getPosition();
        Vector2 delta = new Vector2(clickedPosition.x - playerPosition.x, playerPosition.y - clickedPosition.y);

        Direction newDirection = getFacingDirection(delta);

        StatusComponent statusComponent = sm.get(agent);
        statusComponent.setDirection(newDirection);

        if (orm.has(agent)) {
            OverlayRenderComponent overlayRender = orm.get(agent);
            overlayRender.setPosition(clickedPosition);
        }
    }

    private void pushIfClicking(TargetComponent targetComponent) {
        if (targetComponent.isClicking()) {
            Vector2 clickedPosition = targetComponent.getPosition();
            Value value = new Value(clickedPosition);
            stack.push(value);


            removeOverlayRender();
            completed();
        }
    }

    @Override
    protected void postAbort() {
        removeOverlayRender();
    }

    private void removeOverlayRender() {
        if (orm.has(agent)) {
            agent.remove(OverlayRenderComponent.class);
        }
    }

}
