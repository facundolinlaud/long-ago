package com.facundolinlaud.supergame.managers.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.facundolinlaud.supergame.model.skill.ScreenShake;

public class CameraManager {
    private static final float MAXIMUM_DISTANCE_FROM_SHAKE = 12;
    private static final int VIEWPORT_WIDTH_IN_METERS = 38;
    private static final int Z = 0;

    private ScreenShakeManager shakeManager;
    private OrthographicCamera camera;
    private Vector3 position;

    public CameraManager() {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        this.position = new Vector3(17, 40, Z);
        this.shakeManager = new ScreenShakeManager();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, VIEWPORT_WIDTH_IN_METERS,
                VIEWPORT_WIDTH_IN_METERS * (height / width));
    }

    public void shake(float power, float duration, Vector2 epicenter) {
        if(cameraCloseToEpicenter(epicenter))
            shakeManager.shake(power, duration);
    }

    private boolean cameraCloseToEpicenter(Vector2 epicenter) {
        return new Vector3(epicenter, Z).sub(position).len() < MAXIMUM_DISTANCE_FROM_SHAKE;
    }

    public void resize(int width, int height){
        camera.viewportWidth = VIEWPORT_WIDTH_IN_METERS;
        camera.viewportHeight = VIEWPORT_WIDTH_IN_METERS * height / width;
        camera.update();
    }

    public void render(float delta) {
        camera.position.set(position);
        // camera.position.lerp(position, .2f); use this to get a smooth return

        if(shakeManager.getShakingTimeLeft() > 0)
            camera.translate(shakeManager.tick(delta));

        camera.update();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setPosition(float x, float y){
        this.position = new Vector3(x, y, Z);
    }
}
