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
    private static final Color AMBIENT_LIGHT_COLOR = new Color(722711);
    private static final Color LIGHTS_COLOR = new Color(0f, 0f, 0f, 1f);

    private RayHandler rayHandler;
    private PointLight playerLight;
    private OrthographicCamera camera;

    public LightsManager(World world, OrthographicCamera camera) {
        this.rayHandler = new RayHandler(world);
        this.rayHandler.setAmbientLight(AMBIENT_LIGHT_COLOR);
        this.camera = camera;

        initializePlayerLight();
    }

    private void initializePlayerLight(){
        this.playerLight = new PointLight(rayHandler, RAYS_COUNT, LIGHTS_COLOR, 16, 20, 42);
        this.playerLight.setSoft(true);
        this.playerLight.setIgnoreAttachedBody(true);
        this.playerLight.setXray(true);
    }

    public void setPlayerLightBody(Entity player){
        Body body = bm.get(player).body;
        this.playerLight.attachToBody(body);
    }

    public void create(LightModel model, float x, float y){
        switch (model.getType()){
            case POINT:
                break;
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
