package com.facundolinlaud.supergame.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.facundolinlaud.supergame.components.*;
import com.facundolinlaud.supergame.engine.GameResources;
import com.facundolinlaud.supergame.helper.AnimationType;
import com.facundolinlaud.supergame.helper.BodiesFactory;
import com.facundolinlaud.supergame.helper.Dimensions;
import com.facundolinlaud.supergame.systems.*;

import java.util.Arrays;
import java.util.Iterator;

// * ver si extender PositionComponent para usarlo con physicsSystem
// * ver si el chabon de youtube lo encara de alguna manera diferente (en el video y en su github)
// * ver si la animacion la puedo encarar por el lado de getLinearVelocity

/**
 * Created by facundo on 3/18/16.
 */
public class WorldScreen implements Screen {
    private static final int Z = 0;
    public static final String PATH_TO_TILE_MAP = "map/test4.tmx";
    public static final String PATH_TO_PLAYER_SPRITE = "player/player2.png";
    public static final int VIEWPORT_WIDTH_IN_METERS = 32;

    private GameResources gameResources;

    /* Box2d */
    private World world;
    private BodiesFactory bodiesFactory;
    private Box2DDebugRenderer physicsDebugRenderer;

    /* Tile map */
    private TiledMap map;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer mapRenderer;

    public WorldScreen(GameResources gameResources) {
        this.gameResources = gameResources;

        initializeMap();
        initializePhysics();
        initializePlayer();
        initializeSystems();
    }

    private void initializePhysics() {
        boolean doSleep = true;
        world = new World(new Vector2(0, 0), doSleep);
        physicsDebugRenderer = new Box2DDebugRenderer();
        bodiesFactory = new BodiesFactory(world);

        MapLayer layer = map.getLayers().get("collision");
        MapObjects objects = layer.getObjects();
        Iterator<MapObject> objectIt = objects.iterator();

        while(objectIt.hasNext()) {
            RectangleMapObject object = (RectangleMapObject) objectIt.next();
            Rectangle rectangle = object.getRectangle();

            float width = rectangle.getWidth() / Dimensions.ONE_METER_IN_PIXELS;
            float height = rectangle.getHeight() / Dimensions.ONE_METER_IN_PIXELS;

            float x = (rectangle.getX() / Dimensions.ONE_METER_IN_PIXELS) + width / 2;
            float y = (rectangle.getY() / Dimensions.ONE_METER_IN_PIXELS) + height / 2;

            bodiesFactory.createObstacle(x, y, width, height);
        }
    }

    private void initializeSystems() {
        gameResources.engine.addSystem(new AnimationSystem());
        gameResources.engine.addSystem(new KeyboardSystem());
        gameResources.engine.addSystem(new MovementSystem());
        gameResources.engine.addSystem(new CameraFocusSystem(camera));
        gameResources.engine.addSystem(new PhysicsSystem(world));
    }


    private void initializeMap(){
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        map = new TmxMapLoader().load(PATH_TO_TILE_MAP);
        mapRenderer = new OrthogonalTiledMapRenderer(map, Dimensions.ONE_PIXEL_IN_METERS, gameResources.batch);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH_IN_METERS, VIEWPORT_WIDTH_IN_METERS * (height / width));
    }

    private void initializePlayer(){
        gameResources.engine.addEntity(new Entity()
                .add(new PositionComponent(20, 20))
                .add(new InputComponent())
                .add(new KeyboardComponent())
                .add(new VelocityComponent(1.9f))
                .add(new RenderComponent())
                .add(new BodyComponent(bodiesFactory.createDefault()))
                .add(new AnimationComponent(Arrays.asList(
                        AnimationType.DOWN, AnimationType.LEFT, AnimationType.RIGHT, AnimationType.UP),
                        new Texture(PATH_TO_PLAYER_SPRITE))));
    }

    @Override
    public void render(float delta) {
        gameResources.batch.setProjectionMatrix(camera.combined);
        mapRenderer.setView(camera);
        mapRenderer.render(new int[]{0, 1});

        gameResources.batch.begin();
        gameResources.engine.update(delta);
        gameResources.font.draw(gameResources.batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 0, 0);
        gameResources.batch.end();

        mapRenderer.render(new int[]{2});
        physicsDebugRenderer.render(world, camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = VIEWPORT_WIDTH_IN_METERS;
        camera.viewportHeight = VIEWPORT_WIDTH_IN_METERS * height / width;
        camera.update();
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        map.dispose();
        mapRenderer.dispose();
    }

    @Override
    public void show() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}
}
