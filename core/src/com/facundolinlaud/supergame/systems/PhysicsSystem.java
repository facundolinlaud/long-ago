package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.facundolinlaud.supergame.components.BodyComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.items.ItemComponent;
import com.facundolinlaud.supergame.utils.Dimensions;
import com.facundolinlaud.supergame.utils.Mappers;

/**
 * Created by facundo on 3/20/16.
 */
public class PhysicsSystem extends IteratingSystem {
    public static final float UPDATE_TIME_STEP = 1 / 45f;

    private ComponentMapper<PositionComponent> pm = Mappers.position;
    private ComponentMapper<BodyComponent> bm = Mappers.body;

    private World world;

    public PhysicsSystem(World world) {
        super(Family.all(BodyComponent.class, PositionComponent.class).get());

        this.world = world;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        world.step(UPDATE_TIME_STEP, 6, 2);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = pm.get(entity);
        BodyComponent body = bm.get(entity);

        if(position.overridePhysicsSystem){
            position.overridePhysicsSystem = false;

            body.body.setTransform(position.x + Dimensions.BOX_2D_OFFSET,
                    position.y + Dimensions.BOX_2D_OFFSET, body.body.getAngle());
        }else{
            Vector2 vector = body.body.getPosition();

            position.x = vector.x - Dimensions.BOX_2D_OFFSET;
            position.y = vector.y - Dimensions.BOX_2D_OFFSET;
        }
    }
}
