package com.facundolinlaud.supergame.systems.ui;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.facundolinlaud.supergame.components.BodyComponent;
import com.facundolinlaud.supergame.components.HealthComponent;
import com.facundolinlaud.supergame.components.ManaComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.player.KeyboardComponent;
import com.facundolinlaud.supergame.ui.controller.OverlayUIController;
import com.facundolinlaud.supergame.utils.Mappers;

/**
 * Created by facundo on 3/25/16.
 */
public class ProfileUISystem extends IteratingSystem {
    private ComponentMapper<HealthComponent> hm = Mappers.health;
    private ComponentMapper<ManaComponent> mm = Mappers.mana;
    private ComponentMapper<PositionComponent> pm = Mappers.position;
    private ComponentMapper<BodyComponent> bm = Mappers.body;

    private OverlayUIController overlayController;

    public ProfileUISystem(OverlayUIController overlayUIController) {
        super(Family.all(HealthComponent.class, KeyboardComponent.class).get());
        this.overlayController = overlayUIController;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        overlayController.setFPS(Gdx.graphics.getFramesPerSecond());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        HealthComponent health = hm.get(entity);
        overlayController.setHealth(health.getCurrentHealth(), health.getTotalHealth());

        ManaComponent mana = mm.get(entity);
        overlayController.setMana(mana.getCurrentMana(), mana.getTotalMana());

        PositionComponent position = pm.get(entity);
        overlayController.setPosition(position.getPosition());

        BodyComponent body = bm.get(entity);
        overlayController.setBodyPosition(body.body.getPosition());
    }
}
