package com.machinelearning.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.machinelearning.Evolution;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		// Hej Christian
		config.width = 1920;
		config.height = 1080;
		
		new LwjglApplication(new Evolution(), config);
	}
}
