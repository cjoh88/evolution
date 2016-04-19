package com.machinelearning.model.sensor;

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
		//TODO Implement
		Food food = environment.getNearestFood(animal.position);
		if(c == 'y') {
			return food.y() / Environment.HEIGHT;
		}
		else {
			return food.x() / Environment.WIDTH;
		}
	}

}
