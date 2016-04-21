package com.machinelearning.model.sensor;

import com.badlogic.gdx.math.Vector2;
import com.machinelearning.model.Animal;
import com.machinelearning.model.Environment;
import com.machinelearning.model.Food;

public class FoodSensor extends Sensor{
	
	private char c;
	
	public FoodSensor(char c) {
		this.c = c;
	}
	
	@Override
	public float readSensorValue(Animal animal) {
		Food food = environment.getNearestFood(animal.position);
		Vector2 target = food.position.cpy().sub(animal.position);
		target.nor();
		if(c == 'y') {
			return target.y;
		}
		else {
			return target.x;
		}
	}

	/*@Override
	public float readSensorValue(Animal animal) {
		//TODO Implement
		Food food = environment.getNearestFood(animal.position);
		if(c == 'y') {
			return food.y() / Environment.HEIGHT;
		}
		else {
			return food.x() / Environment.WIDTH;
		}
	}*/

}
