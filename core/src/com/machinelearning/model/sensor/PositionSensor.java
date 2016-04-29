package com.machinelearning.model.sensor;

import com.machinelearning.model.Animal;
import com.machinelearning.model.Config;

public class PositionSensor extends Sensor {

	char c;
	
	public PositionSensor(char c) {
		this.c = c;
	}
	
	@Override
	public float readSensorValue(Animal animal) {
		float result;
		if(c == 'x') {
			result = animal.x() / Config.WIDTH;
		}
		else {
			result = animal.y() / Config.HEIGHT;
		}
		return result;
	}

}
