package com.machinelearning.model;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Food {
	
	private static Random random = new Random();
	
	public Vector2 position;
	
	private Color color;
	
	public Food() {
		this.position = new Vector2(
				random.nextFloat() * Config.WIDTH,
				random.nextFloat() * Config.HEIGHT
		);
		color = Color.ORANGE;
	}
	
	public void found() {
		position.x = random.nextFloat() * Config.WIDTH;
		position.y = random.nextFloat() * Config.HEIGHT;
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
