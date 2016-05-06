package com.machinelearning;

import java.util.Arrays;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class Evolution extends Game {
	// SpriteBatch batch;
	// Texture img;

	EvolutionScreen mainScreen;

	@Override
	public void create() {
		// batch = new SpriteBatch();
		// img = new Texture("badlogic.jpg");
		mainScreen = new EvolutionScreen();
		setScreen(mainScreen);

	}

	@Override
	public void render() {
		super.render();
		// Gdx.gl.glClearColor(0, 0, 0, 1);
		// Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// batch.begin();
		// batch.draw(img, 0, 0);
		// batch.end();
	}
	
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		System.out.println("Best Prey:");
		System.out.println(Arrays.toString(mainScreen.environment.getPrey()[0].getGenome()));
		
		System.out.println("Best Pred:");
		System.out.println(Arrays.toString(mainScreen.environment.getPred()[0].getGenome()));
	}

}
