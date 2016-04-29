package com.machinelearning.model.sensor;

import com.machinelearning.model.Animal;
import com.machinelearning.model.Config;
import com.machinelearning.model.Food;

public class FoodPositionSensor extends Sensor {
	
	private char c;
	
	public FoodPositionSensor(char c) {
		this.c = c;
	}

	@Override
	public float readSensorValue(Animal animal) {
		Food f = environment.getNearestFood(animal.position);
		float result;
		if(c == 'x') {
			result = f.x() / Config.WIDTH;
		}
		else {
			result = f.y() / Config.HEIGHT;
		}
		return result;
	}
	
	

}
