package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.model.skill.ScreenShake;

import java.util.Random;

public class ScreenShakeManager {
    public static final ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);

    private Camera camera;
    private Entity player;

    private float timeLeft = 0;
    private float currentTime = 0;
    private float power = 0;
    private float currentPower = 0;
    private Random random;
    private Vector3 position = new Vector3();

    public ScreenShakeManager(MapManager mapManager, Entity player) {
        this.camera = mapManager.getCamera();
        this.player = player;
    }

    public void shake(ScreenShake screenShake) {
        random = new Random();
        power = screenShake.getPower();
        timeLeft = screenShake.getDuration();
        currentTime = 0;
    }

    private Vector3 tick(float delta) {
        if(currentTime <= timeLeft) {
            currentPower = power * ((timeLeft - currentTime) / timeLeft);

            position.x = (random.nextFloat() - 0.5f) * 2 * currentPower;
            position.y = (random.nextFloat() - 0.5f) * 2 * currentPower;

            currentTime += delta;
        } else {
            timeLeft = 0;
        }

        return position;
    }

    public void update(float delta){
        if(timeLeft > 0){
            tick(delta);
            camera.translate(position);
        } else {
            Vector2 playerPosition = pm.get(player).getPosition();
            // ver lo del z = 0
            camera.position.lerp(new Vector3(playerPosition, 0), .2f);
        }
    }
}
