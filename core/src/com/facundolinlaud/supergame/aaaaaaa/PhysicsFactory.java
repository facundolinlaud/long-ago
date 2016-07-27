package com.facundolinlaud.supergame.aaaaaaa;

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

    public Body createPlayerBody(){
        // First we create a body definition
        BodyDef bodyDef = new BodyDef();

        // We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        // Set our body's starting position in the world
        float x = 0, y = 0;
        bodyDef.position.set(x + Dimensions.BOX_2D_OFFSET, y + Dimensions.BOX_2D_OFFSET);
        bodyDef.fixedRotation = true;

        // Create our body in the world using our body definition
        Body body = world.createBody(bodyDef);

        // Create a circle shape and set its radius to 6
        CircleShape circle = new CircleShape();
        circle.setRadius(RADIUS);

        // Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = createFixtureDefinition();
        fixtureDef.shape = circle;

        // Create our fixture and attach it to the body
        Fixture fixture = body.createFixture(fixtureDef);

        // Remember to dispose of any shapes after you're done with them!
        // BodyDef and FixtureDef don't need disposing, but shapes do.
        circle.dispose();

        return body;
    }

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

        Fixture fixture = body.createFixture(fixtureDef);
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
}
