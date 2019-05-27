package com.facundolinlaud.supergame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.facundolinlaud.supergame.engine.Game;
import com.facundolinlaud.supergame.utils.Dimensions;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Dimensions.SCREEN_WIDTH;
		config.height = Dimensions.SCREEN_HEIGHT;
		config.fullscreen = false;
		new LwjglApplication(new Game(), config);
	}
}
