package com.facundolinlaud.supergame.managers.world;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.facundolinlaud.supergame.components.BodyComponent;
import com.facundolinlaud.supergame.managers.Renderable;
import com.facundolinlaud.supergame.model.light.LightModel;
import com.facundolinlaud.supergame.utils.lights.DimmingLight;

public class LightsManager implements Renderable {
    public static final ComponentMapper<BodyComponent> bm = ComponentMapper.getFor(BodyComponent.class);

    private static final int RAYS_COUNT = 32;
    private static final Color LIGHTS_COLOR = new Color(0f, 0f, 0f, 1f);
    private static final Color NIGHT_LIGHT_COLOR = new Color(0.1f, 0.1f, 0.55f, 0.16f);

    private static final Color NIGHT_LIGHT_COLOR_FOR_DIFFUSED = new Color(0.1f, 0.1f, 0.5f, 0.6f);
    private static final Color AMBIENT_LIGHT_COLOR = new Color(722711);

    private RayHandler rayHandler;
    private PointLight playerLight;
    private OrthographicCamera camera;

    public LightsManager(World world, OrthographicCamera camera, Entity player) {
        this.rayHandler = new RayHandler(world);
        this.rayHandler.setAmbientLight(NIGHT_LIGHT_COLOR);
        this.rayHandler.setBlur(true);
        this.rayHandler.setBlurNum(2);
        this.camera = camera;

        initializePlayerLight();
        setPlayerLightBody(player);
    }

    private void initializePlayerLight(){
        this.playerLight = new PointLight(rayHandler, RAYS_COUNT, LIGHTS_COLOR, 10, 20, 42);
        this.playerLight.setSoft(true);
        this.playerLight.setIgnoreAttachedBody(true);
        this.playerLight.setXray(true);
    }

    private void setPlayerLightBody(Entity player){
        Body body = bm.get(player).body;
        this.playerLight.attachToBody(body);
    }

    public void create(LightModel model, float x, float y){
        switch (model.getType()){
            case POINT:
            case FLICKERING:
                break;
            case DIMMING:
                new DimmingLight(rayHandler, RAYS_COUNT, LIGHTS_COLOR, model.getDistance(), x, y, model.getDuration());
                break;
        }
    }

    @Override
    public void render() {
        rayHandler.setCombinedMatrix(camera);
        rayHandler.updateAndRender();
    }

    public void dispose(){
        rayHandler.dispose();
    }
}
