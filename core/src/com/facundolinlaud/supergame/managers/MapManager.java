package com.facundolinlaud.supergame.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.facundolinlaud.supergame.helper.Dimensions;

/**
 * Created by facundo on 3/23/16.
 */
public class MapManager {
    public static final String PATH_TO_TILE_MAP = "map/test5.tmx";
    public static final int VIEWPORT_WIDTH_IN_METERS = 32;
    public static final int[] BASE_LAYERS = new int[]{0, 1};
    public static final int[] UPPER_LAYERS = new int[]{2};

    private SpriteBatch batch;

    /* Tile map */
    private TiledMap map;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer mapRenderer;

    public MapManager(SpriteBatch batch) {
        this.batch = batch;

        initialize();
    }

    private void initialize(){
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        map = new TmxMapLoader().load(PATH_TO_TILE_MAP);
        mapRenderer = new OrthogonalTiledMapRenderer(map, Dimensions.ONE_PIXEL_IN_METERS, batch);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH_IN_METERS, VIEWPORT_WIDTH_IN_METERS * (height / width));
    }

    public void renderBaseLayer(){
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

    public Camera getCamera(){
        return this.camera;
    }

    public TiledMap getMap(){
        return this.map;
    }
}
