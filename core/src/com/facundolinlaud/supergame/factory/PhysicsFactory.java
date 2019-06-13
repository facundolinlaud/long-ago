package com.facundolinlaud.supergame.factory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.facundolinlaud.supergame.utils.Dimensions;

/**
 * Created by facundo on 27/7/16.
 */
public class PhysicsFactory {
    private static final float RADIUS = 0.4f;
    private static final float DENSITY = 0.5f;
    private static final float FRICTION = 0.4f;
    private static final float RESTITUTION = 0.6f;
    private static PhysicsFactory instance = null;

    private World world;

    protected PhysicsFactory() {}

    public Body createObstacleBody(float x, float y, float width, float height){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, y);
        Body body = world.createBody(bodyDef);
        PolygonShape square = new PolygonShape();
        square.setAsBox(width / 2, height / 2);

        FixtureDef fixtureDef = createFixtureDefinition();
        fixtureDef.shape = square;

        Fixture fixture = body.createFixture(fixtureDef);
        square.dispose();

        return body;
    }

    public Body createItemBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;

        Body body = world.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(RADIUS);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1f;
        fixtureDef.friction = 2f;
        fixtureDef.restitution = 0.1f;

        fixtureDef.shape = circle;

        body.createFixture(fixtureDef);
        circle.dispose();

        body.setLinearDamping(3f);
        return body;
    }

    private FixtureDef createFixtureDefinition(){
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = DENSITY;
        fixtureDef.friction = FRICTION;
        fixtureDef.restitution = RESTITUTION; // Make it bounce a little bit

        return fixtureDef;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public static PhysicsFactory get(){
        if(instance == null){
            instance = new PhysicsFactory();
        }

        return instance;
    }

    public Body createProjectileBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;

        Body body = world.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(0.1f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 0.1f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 1f;

        fixtureDef.shape = circle;

        body.createFixture(fixtureDef);
        circle.dispose();

        body.setLinearDamping(3f);
        body.setBullet(true);
        return body;
    }
}
