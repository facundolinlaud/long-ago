package com.facundolinlaud.supergame.systems.ui;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.facundolinlaud.supergame.components.HealthComponent;
import com.facundolinlaud.supergame.components.KeyboardComponent;
import com.facundolinlaud.supergame.ui.services.ProfileUIService;
import com.facundolinlaud.supergame.ui.view.OverlayUI;
import com.facundolinlaud.supergame.utils.Mappers;

/**
 * Created by facundo on 3/25/16.
 */
public class ProfileUISystem extends IteratingSystem {
    private ComponentMapper<HealthComponent> hm = Mappers.health;

    private ProfileUIService profileUIService;

    private OverlayUI overlayUI;

    public ProfileUISystem(OverlayUI overlayUI) {
        super(Family.all(HealthComponent.class, KeyboardComponent.class).get());
        this.overlayUI = overlayUI;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        overlayUI.setFPS(Gdx.graphics.getFramesPerSecond());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        HealthComponent health = hm.get(entity);
        overlayUI.setHealth(health.health);
    }
}
