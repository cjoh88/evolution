package com.machinelearning.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.machinelearning.model.Animal;
import com.machinelearning.model.Plant;

public class PlantRenderer {
	
	private Plant[] food;
	private OrthographicCamera camera;
	
	private ShapeRenderer shapeRenderer;
	
	public PlantRenderer(Plant[] food, OrthographicCamera camera) {
		this.food = food;
		this.camera = camera;
		this.shapeRenderer = new ShapeRenderer();
	}
	
	public void render() {
		//camera.update();
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Filled);
			for(Plant f : food) {
				shapeRenderer.setColor(f.color());
				shapeRenderer.circle(f.x(), f.y(), 0.2f, 10);
			}
		shapeRenderer.end();
	}

}
