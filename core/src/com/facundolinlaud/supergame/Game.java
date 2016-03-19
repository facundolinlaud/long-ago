package com.facundolinlaud.supergame;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.facundolinlaud.supergame.engine.GameResources;
import com.facundolinlaud.supergame.managers.ScreenManager;
import com.facundolinlaud.supergame.systems.CameraFocusSystem;
import com.facundolinlaud.supergame.systems.InputMovementSystem;
import com.facundolinlaud.supergame.systems.RenderSystem;
import com.facundolinlaud.supergame.screens.WorldScreen;

import java.util.Arrays;
import java.util.List;

public class Game extends ApplicationAdapter {
	private Engine engine;
	private SpriteBatch batch;
	private BitmapFont bitmapFont;
	private ScreenManager screenManager;

	@Override
	public void create () {
		engine = new Engine();
		batch = new SpriteBatch();
		bitmapFont = new BitmapFont();

		initializeScreen();
		initializeSystems();

		/**/
		Texture txt = new Texture("player/player1.png");
		List<TextureRegion> textures = Arrays.asList(
				new TextureRegion(txt, 0, 64, 32, 32),
				new TextureRegion(txt, 32, 64, 32, 32),
				new TextureRegion(txt, 64, 64, 32, 32)
		);

		animation = new Animation(0.25f, new Array(textures.toArray()), Animation.PlayMode.LOOP);
		/**/
	}

	private void initializeScreen() {
		screenManager = new ScreenManager(new GameResources(engine, batch));
		screenManager.loadWorldScreen();
	}

	private void initializeSystems() {
		RenderSystem renderSystem = new RenderSystem(batch);
		engine.addSystem(renderSystem);
	}

	float keyFrame = 0;
	Animation animation;

	@Override
	public void render () {
		float deltaTime = Gdx.graphics.getDeltaTime();

		clearScreen();
		screenManager.getCurrentScreen().render(deltaTime);

		batch.begin();
		engine.update(deltaTime);




		/**/
		keyFrame += deltaTime;
		TextureRegion t = animation.getKeyFrame(keyFrame, true);
		batch.draw(t, 50, 50);
		System.out.println(keyFrame);
		/**/





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
