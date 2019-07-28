package com.facundolinlaud.supergame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.facundolinlaud.supergame.engine.Game;
import com.facundolinlaud.supergame.utils.Dimensions;

import java.awt.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimensions.SCREEN_WIDTH = screenSize.width;
		Dimensions.SCREEN_HEIGHT = screenSize.height;

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Dimensions.SCREEN_WIDTH;
		config.height = Dimensions.SCREEN_HEIGHT;
		config.fullscreen = false;
		new LwjglApplication(new Game(), config);
	}
}
