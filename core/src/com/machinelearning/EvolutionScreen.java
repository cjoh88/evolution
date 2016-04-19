package com.machinelearning;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.machinelearning.model.Environment;
import com.machinelearning.view.EnvironmentRenderer;

public class EvolutionScreen implements Screen{
	
	private Environment environment;
	private EnvironmentRenderer renderer;
	
	private OrthographicCamera camera;

	@Override
	public void show() {
		camera = new OrthographicCamera(Environment.WIDTH, Environment.HEIGHT);
		camera.position.x = Environment.WIDTH / 2;
		camera.position.y = Environment.HEIGHT / 2;
		environment = new Environment();
		renderer = new EnvironmentRenderer(environment, camera);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0.5f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		environment.update(delta);
		renderer.render();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
