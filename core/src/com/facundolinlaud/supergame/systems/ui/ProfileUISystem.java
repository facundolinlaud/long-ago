package com.facundolinlaud.supergame.systems.ui;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.facundolinlaud.supergame.components.HealthComponent;
import com.facundolinlaud.supergame.components.KeyboardComponent;
import com.facundolinlaud.supergame.helper.Mappers;
import com.facundolinlaud.supergame.ui.ProfileUI;

/**
 * Created by facundo on 3/25/16.
 */
public class ProfileUISystem extends IteratingSystem {
    private ComponentMapper<HealthComponent> hm = Mappers.health;

    private ProfileUI profileUI;

    public ProfileUISystem(ProfileUI profileUI) {
        super(Family.all(HealthComponent.class, KeyboardComponent.class).get());
        this.profileUI = profileUI;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        profileUI.setFPS(Gdx.graphics.getFramesPerSecond());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        HealthComponent health = hm.get(entity);
        profileUI.setHealth(health.health);
    }
}
