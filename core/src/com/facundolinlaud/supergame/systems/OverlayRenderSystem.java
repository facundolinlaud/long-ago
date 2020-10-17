package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.facundolinlaud.supergame.components.OverlayRenderComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.engine.GameResources;
import com.facundolinlaud.supergame.managers.world.CameraManager;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.PhasedIteratingSystem;
import com.facundolinlaud.supergame.utils.shape.Shape;

public class OverlayRenderSystem extends PhasedIteratingSystem {
    private ComponentMapper<OverlayRenderComponent> orm = Mappers.overlayRender;

    private Camera camera;
    private ShapeRenderer renderer;
    private SpriteBatch spriteBatch;

    public OverlayRenderSystem(CameraManager cameraManager, GameResources gameResources) {
        super(Family.all(PositionComponent.class, OverlayRenderComponent.class).get());
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
    protected void processEntity(Entity entity, float deltaTime) {
        OverlayRenderComponent renderComponent = orm.get(entity);
        Shape shape = renderComponent.getShape();

        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(renderComponent.getShapeType());
        renderer.setColor(renderComponent.getColor());
        shape.render(renderer);
        renderer.end();
    }

    @Override
    protected void afterFrame() {
        Gdx.gl.glDisable(GL30.GL_BLEND);
        spriteBatch.begin();
    }
}
