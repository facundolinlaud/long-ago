package com.facundolinlaud.supergame;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.facundolinlaud.supergame.engine.GameResources;
import com.facundolinlaud.supergame.managers.ScreenManager;
import com.facundolinlaud.supergame.systems.RenderSystem;

public class Game extends ApplicationAdapter {
	private Engine engine;
	private SpriteBatch batch;
	private ScreenManager screenManager;

	@Override
	public void create () {
		engine = new Engine();
		batch = new SpriteBatch();

		initializeScreen();
		initializeSystems();
	}

	private void initializeScreen() {
		screenManager = new ScreenManager(new GameResources(engine, batch));
		screenManager.loadWorldScreen();
	}

	private void initializeSystems() {
		RenderSystem renderSystem = new RenderSystem(batch);
		engine.addSystem(renderSystem);
	}

	@Override
	public void render () {
		clearScreen();

		float deltaTime = Gdx.graphics.getDeltaTime();
		screenManager.getCurrentScreen().render(deltaTime);
	}


	private void clearScreen() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

		if(screenManager.getCurrentScreen() != null)
			screenManager.getCurrentScreen().resize(width, height);
	}

	@Override
	public void dispose() {
		if(screenManager.getCurrentScreen() != null)
			screenManager.getCurrentScreen().dispose();

		super.dispose();
	}
}
