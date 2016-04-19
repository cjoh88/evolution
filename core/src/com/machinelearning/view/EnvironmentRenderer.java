package com.machinelearning.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.machinelearning.model.Environment;

public class EnvironmentRenderer {
	
	private Environment environment;
	private OrthographicCamera camera;
	
	private ShapeRenderer shapeRenderer;
	private AnimalRenderer animalRenderer;
	
	public EnvironmentRenderer(Environment environment, OrthographicCamera camera) {
		this.environment = environment;
		this.camera = camera;
		this.shapeRenderer = new ShapeRenderer();
		this.animalRenderer = new AnimalRenderer(environment.getAnimals(), camera);
	}
	
	public void render() {
		camera.update();
		/*shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(1,1,0,1);
			shapeRenderer.rect(1,1,2,2);
		shapeRenderer.end();*/
		animalRenderer.render();
	}

}
