package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by facundo on 3/20/16.
 */
public class BodyComponent implements Component {
    public Body body;

    public BodyComponent(Body body) {
        this.body = body;
    }

    public BodyComponent(Body body, Entity agent) {
        this.body = body;
        this.body.setUserData(agent);
    }

    public void setBullet(boolean isBullet){
        this.body.setBullet(isBullet);
    }
}
