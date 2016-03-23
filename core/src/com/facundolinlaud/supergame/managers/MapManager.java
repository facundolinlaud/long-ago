package com.facundolinlaud.supergame.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.facundolinlaud.supergame.engine.GameResources;
import com.facundolinlaud.supergame.helper.Dimensions;

/**
 * Created by facundo on 3/23/16.
 */
public class MapManager {
    public static final String PATH_TO_TILE_MAP = "map/test4.tmx";
    public static final int VIEWPORT_WIDTH_IN_METERS = 32;

    private GameResources res;

    /* Tile map */
    private TiledMap map;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer mapRenderer;

    public MapManager(GameResources res) {
        this.res = res;
    }

    public void initialize(){
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        map = new TmxMapLoader().load(PATH_TO_TILE_MAP);
        mapRenderer = new OrthogonalTiledMapRenderer(map, Dimensions.ONE_PIXEL_IN_METERS, res.batch);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH_IN_METERS, VIEWPORT_WIDTH_IN_METERS * (height / width));
    }

    public void renderBaseMap(){
        mapRenderer.setView(camera);
        mapRenderer.render(new int[]{0, 1});
    }

    public void renderFlora(){
        mapRenderer.render(new int[]{2});
    }
}
