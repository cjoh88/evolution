package com.machinelearning.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.machinelearning.Evolution;
import com.machinelearning.diversity.ShannonWienerDiversity;
import com.machinelearning.model.Animal;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1920;
		config.height = 1080;
		config.vSyncEnabled = false;
		config.foregroundFPS = 0;
		config.backgroundFPS = 0;
		
		new LwjglApplication(new Evolution(), config);
		

	}
}
