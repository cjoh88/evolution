package com.machinelearning.view;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.machinelearning.model.Animal;

public class AnimalRenderer {
	
	private Animal[] animals;
	private OrthographicCamera camera;
	
	private ShapeRenderer shapeRenderer;
	
	public AnimalRenderer(Animal[] animals, OrthographicCamera camera) {
		this.animals = animals;
		this.camera = camera;
		this.shapeRenderer = new ShapeRenderer();
	}
	
	public void render() {
		//camera.update();
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Filled);
		//shapeRenderer.setColor(1,1,0,1);
			for(Animal animal : animals) {
				//shapeRenderer.rect(animal.x(), animal.y() , 1, 1);
				shapeRenderer.setColor(animal.color());
				//shapeRenderer.box(x, y, z, width, height, depth); 
				shapeRenderer.rect(animal.x(), animal.y(), 0.5f, 0.5f);
				//shapeRenderer.circle(animal.x(), animal.y(), 0.5f, 10);
			}
		shapeRenderer.end();
	}

}
