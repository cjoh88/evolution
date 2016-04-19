package com.machinelearning.model.sensor;

import com.machinelearning.model.Animal;
import com.machinelearning.model.Environment;

public class PositionSensor extends Sensor{
	
	private char c;
	
	public PositionSensor(char c) {
		this.c = c;
	}

	@Override
	public float readSensorValue(Animal animal) {
		if(c == 'y') {
			return animal.y() / Environment.HEIGHT;
		}
		else {
			return animal.x() / Environment.WIDTH;
		}
	}

}
