package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.AgentComponent;
import com.facundolinlaud.supergame.components.BodyComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.engine.GameResources;
import com.facundolinlaud.supergame.managers.world.CameraManager;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.PhasedIteratingSystem;

public class PositionTestSystem extends PhasedIteratingSystem {
    private ComponentMapper<PositionComponent> pm = Mappers.position;
    private ComponentMapper<BodyComponent> bm = Mappers.body;

    private Camera camera;
    private ShapeRenderer renderer;
    private SpriteBatch spriteBatch;

    public PositionTestSystem(CameraManager cameraManager, GameResources gameResources) {
        super(Family.all(PositionComponent.class, AgentComponent.class).get());
        this.camera = cameraManager.getCamera();
        this.spriteBatch = gameResources.getBatch();
        this.renderer = new ShapeRenderer();
    }

    @Override
    protected void beforeFrame() {
        spriteBatch.end();
        Gdx.gl.glEnable(GL30.GL_BLEND);
        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
    }

    @Override
    protected void processEntity(Entity agent, float deltaTime) {
        PositionComponent positionComponent = pm.get(agent);
        BodyComponent bodyComponent = bm.get(agent);

        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.RED);
        renderer.circle(positionComponent.x, positionComponent.y, 1/8f, 20);

        Vector2 bodyPosition = bodyComponent.body.getPosition();
        renderer.setColor(Color.BLUE);
        renderer.circle(bodyPosition.x, bodyPosition.y, 1/8f, 20);
//        System.out.println(positionComponent.x + ", " + positionComponent.y + "; " + bodyPosition.x + ", " + bodyPosition.y);
        renderer.end();
    }

    @Override
    protected void afterFrame() {
        Gdx.gl.glDisable(GL30.GL_BLEND);
        spriteBatch.begin();
    }
}
