package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.facundolinlaud.supergame.components.HealthComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.RenderComponent;
import com.facundolinlaud.supergame.components.player.KeyboardComponent;
import com.facundolinlaud.supergame.utils.Mappers;

import static com.facundolinlaud.supergame.utils.Dimensions.BOX_2D_OFFSET;
import static com.facundolinlaud.supergame.utils.Dimensions.PX_PER_METER;

public class HealthSystem extends IteratingSystem {
    private static final String HEALTH_BAR_BACKGROUND_PATH = "ui/game/health_bar_background.png";
    private static final String HEALTH_BAR_FILL_PATH = "ui/game/health_bar_fill.png";

    private ComponentMapper<HealthComponent> hm = Mappers.health;
    private ComponentMapper<PositionComponent> pm = Mappers.position;

    private SpriteBatch spriteBatch;
    private Sprite healthBarBackground;
    private Sprite healthBarFill;
    private float sizeOfFullHealthBar;

    private float xHealthBarFillOffset;
    private float yHealthBarFillOffset;
    private float xHealthBarsOffset;
    private float yHealthBarsOffset;

    public HealthSystem(SpriteBatch spriteBatch) {
        super(Family.all(HealthComponent.class, PositionComponent.class, RenderComponent.class)
                .exclude(KeyboardComponent.class).get());

        this.spriteBatch = spriteBatch;
        this.healthBarBackground = new Sprite(new Texture(HEALTH_BAR_BACKGROUND_PATH));
        this.healthBarFill = new Sprite(new Texture(HEALTH_BAR_FILL_PATH));

        this.xHealthBarFillOffset = 1 / PX_PER_METER;
        this.yHealthBarFillOffset = 1 / PX_PER_METER;
        this.xHealthBarsOffset = -BOX_2D_OFFSET;
        this.yHealthBarsOffset = 1.8f;

        this.sizeOfFullHealthBar = healthBarFill.getRegionWidth();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        HealthComponent healthComponent = hm.get(entity);

        if (healthComponent.isFull())
            return;

        if (healthComponent.getCurrentHealth() <= 0)
            healthComponent.getZeroHealthStrategy().onZeroHealth(entity);
        else
            updateHealthBar(entity, healthComponent);
    }

    private void updateHealthBar(Entity entity, HealthComponent healthComponent) {
        PositionComponent positionComponent = pm.get(entity);

        float entityMaxHealth = healthComponent.getTotalHealth();
        float currentHealth = healthComponent.getCurrentHealth();
        float sizeOfHealthBar = currentHealth * sizeOfFullHealthBar / entityMaxHealth;

        float x = positionComponent.x + xHealthBarsOffset;
        float y = positionComponent.y + yHealthBarsOffset;

        spriteBatch.draw(healthBarBackground, x, y,
                healthBarBackground.getRegionWidth() / PX_PER_METER,
                healthBarBackground.getRegionHeight() / PX_PER_METER);

        spriteBatch.draw(healthBarFill, x + xHealthBarFillOffset, y + yHealthBarFillOffset,
                sizeOfHealthBar / PX_PER_METER,
                healthBarFill.getRegionHeight() / PX_PER_METER);
    }
}
