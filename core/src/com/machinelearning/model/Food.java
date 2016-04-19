package com.machinelearning.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Food {
	
	public Vector2 position;
	
	private Color color;
	
	public Food(Vector2 position) {
		this.position = position;
		color = Color.ORANGE;
	}
	
	public float x() {
		return position.x;
	}
	
	public float y() {
		return position.y;
	}
	
	public Color color() {
		return color;
	}

}
