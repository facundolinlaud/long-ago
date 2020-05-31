package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by facundo on 3/20/16.
 */
public class BodyComponent implements Component {
    public Body body;

    public BodyComponent(Body body) {
        this.body = body;
    }

    public BodyComponent(Body body, Entity entity) {
        this(body);
        body.setUserData(entity);
    }

    public void setShootable(Entity agentToTakeDamage){
        body.setUserData(agentToTakeDamage);
    }
}
