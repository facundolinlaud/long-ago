package com.facundolinlaud.supergame;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.facundolinlaud.supergame.components.InputComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.RenderComponent;
import com.facundolinlaud.supergame.components.VelocityComponent;
import com.facundolinlaud.supergame.engine.GameResources;
import com.facundolinlaud.supergame.systems.InputMovementSystem;
import com.facundolinlaud.supergame.systems.RenderSystem;
import com.facundolinlaud.supergame.screens.WorldScreen;

public class Game extends ApplicationAdapter {
	private final static int FRAMES_PER_SECOND = 60;

	private Engine engine;
	private Screen screen;
	private SpriteBatch batch;

	@Override
	public void create () {
		engine = new Engine();
		batch = new SpriteBatch();
		GameResources gameResources = new GameResources(engine, batch);

		addSystems();

		screen = new WorldScreen(gameResources);
	}

	private void addSystems() {
		InputMovementSystem inputMovementSystem = new InputMovementSystem();
		engine.addSystem(inputMovementSystem);

		RenderSystem renderSystem = new RenderSystem(batch);
		engine.addSystem(renderSystem);
	}

	@Override
	public void render () {
		float deltaTime = Gdx.graphics.getDeltaTime();

		clearScreen();
		screen.render(deltaTime);
		batch.begin();
		engine.update(deltaTime);
		batch.end();
	}


	private void clearScreen() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		screen.resize(width, height);
	}

	@Override
	public void dispose() {
		screen.dispose();
		super.dispose();
	}
}
