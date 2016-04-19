package com.machinelearning;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Evolution extends Game {
	//SpriteBatch batch;
	//Texture img;
	
	EvolutionScreen mainScreen;
	
	@Override
	public void create () {
		//batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		mainScreen = new EvolutionScreen();
		setScreen(mainScreen);
	}

	@Override
	public void render () {
		super.render();
		//Gdx.gl.glClearColor(0, 0, 0, 1);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//batch.begin();
		//batch.draw(img, 0, 0);
		//batch.end();
	}
	//HEJ
}
