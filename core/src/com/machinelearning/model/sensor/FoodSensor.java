package com.machinelearning.model.sensor;

import com.machinelearning.model.Animal;

public class FoodSensor extends Sensor{
	
	private char c;
	
	public FoodSensor(char c) {
		this.c = c;
	}

	@Override
	public float readSensorValue(Animal animal) {
		//TODO Implement
		if(c == 'y') {
			return 0.0f;
		}
		else {
			return 1.0f;
		}
	}

}
