package com.facundolinlaud.supergame.managers.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.facundolinlaud.supergame.managers.Renderable;
import com.facundolinlaud.supergame.model.spawn.SpawnLocation;
import com.facundolinlaud.supergame.utils.Dimensions;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by facundo on 3/23/16.
 */
public class MapManager implements Renderable {
    public static final String PATH_TO_TILE_MAP = "map/test_green_debug.tmx";
    public static final int VIEWPORT_WIDTH_IN_METERS = 32;

    public static final int[] BASE_LAYERS = new int[]{0, 1, 2};
    public static final int[] UPPER_LAYERS = new int[]{3};
    public static final String SPAWN_LAYER = "spawn";
    public static final String AGENT_ID_PROPERTY = "agentID";
    public static final String AGENTS_COUNT_PROPERTY = "agentsCount";

    private SpriteBatch batch;

    /* Tile map */
    private TiledMap map;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer mapRenderer;

    public MapManager(SpriteBatch batch) {
        this.batch = batch;

        initializeMap();
        initializeCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    private void initializeCamera(float width, float height) {
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, VIEWPORT_WIDTH_IN_METERS, VIEWPORT_WIDTH_IN_METERS * (height / width));
    }

    private void initializeMap() {
        this.map = new TmxMapLoader().load(PATH_TO_TILE_MAP);
        this.mapRenderer = new OrthogonalTiledMapRenderer(this.map, Dimensions.METERS_PER_PX, this.batch);
    }

    @Override
    public void render() {
        batch.setProjectionMatrix(camera.combined);
        mapRenderer.setView(camera);
        mapRenderer.render(BASE_LAYERS);
    }

    public void renderUpperLayer(){
        mapRenderer.render(UPPER_LAYERS);
    }

    public void resize(int width, int height){
        camera.viewportWidth = VIEWPORT_WIDTH_IN_METERS;
        camera.viewportHeight = VIEWPORT_WIDTH_IN_METERS * height / width;
        camera.update();
    }

    public void dispose(){
        map.dispose();
        mapRenderer.dispose();
    }

    public OrthographicCamera getCamera(){
        return this.camera;
    }

    public TiledMap getMap(){
        return this.map;
    }

    public List<SpawnLocation> getSpawnLocations(){
        List<SpawnLocation> spawnLocations = new LinkedList();
        MapObjects spawnObjects = map.getLayers().get(SPAWN_LAYER).getObjects();

        for(MapObject object : spawnObjects){
            MapProperties properties = object.getProperties();

            int id = (int) properties.get(AGENT_ID_PROPERTY);
            int agentsCount = (int) properties.get(AGENTS_COUNT_PROPERTY);
            RectangleMapObject rmo = (RectangleMapObject) object;

            spawnLocations.add(new SpawnLocation(id, agentsCount, rmo.getRectangle()));
        }

        return spawnLocations;
    }

}
