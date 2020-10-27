package com.facundolinlaud.supergame.systems.ai;

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
import com.facundolinlaud.supergame.ai.pathfinding.Node;
import com.facundolinlaud.supergame.components.ai.BehaviorComponent;
import com.facundolinlaud.supergame.components.ai.TraverseComponent;
import com.facundolinlaud.supergame.engine.GameResources;
import com.facundolinlaud.supergame.managers.world.CameraManager;
import com.facundolinlaud.supergame.managers.world.PathFindingManager;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.PhasedIteratingSystem;

public class DebugTraverseSystem extends PhasedIteratingSystem {
    private ComponentMapper<TraverseComponent> tm = Mappers.traverse;
    private static final Color FADED_BLUE = new Color(Color.BLUE.r, Color.BLUE.g, Color.BLUE.b, 0.4f);
    private static final Color FADED_RED = new Color(Color.RED.r, Color.RED.g, Color.RED.b, 0.4f);
    private Camera camera;
    private ShapeRenderer renderer;
    private SpriteBatch spriteBatch;
    private PathFindingManager pathFindingManager;

    public DebugTraverseSystem(CameraManager cameraManager, GameResources gameResources, PathFindingManager pathFindingManager) {
        super(Family.all(BehaviorComponent.class, TraverseComponent.class).get());
        this.camera = cameraManager.getCamera();
        this.spriteBatch = gameResources.getBatch();
        this.renderer = new ShapeRenderer();
        this.pathFindingManager = pathFindingManager;
    }

    @Override
    protected void beforeFrame() {
        spriteBatch.end();
        Gdx.gl.glEnable(GL30.GL_BLEND);
        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(FADED_BLUE);
        TraverseComponent traverseComponent = tm.get(entity);
        traverseComponent.getPath().forEach(this::drawNode);
        renderer.setColor(FADED_RED);
        pathFindingManager.getOccupatedCells().forEach(this::drawNode);
        renderer.end();
    }

    private void drawNode(Vector2 position) {
        renderer.rect(position.x, position.y, 1f, 1f);
    }

    private void drawNode(Node node) {
        renderer.rect(node.getX(), node.getY(), 1f, 1f);
    }

    @Override
    protected void afterFrame() {
        Gdx.gl.glDisable(GL30.GL_BLEND);
        spriteBatch.begin();
    }
}
