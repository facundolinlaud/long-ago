package com.facundolinlaud.supergame.managers.world;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.facundolinlaud.supergame.managers.Renderable;

public class LightsManager implements Renderable {
    public static final Color AMBIENT_LIGHT_COLOR = new Color(722711);

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
        float c = 0f;
        float a = 1f;
        Color lightColor = new Color(c, c, c, a);

        this.playerLight = new PointLight(rayHandler, 32, lightColor, 16, 20, 42);
        this.playerLight.setSoft(true);
        this.playerLight.setIgnoreAttachedBody(false);
        this.playerLight.setXray(true);
    }

    public void setPlayerLightBody(Body player){
        this.playerLight.attachToBody(player);
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
