package com.machinelearning.model.sensor;

import com.machinelearning.model.Animal;
import com.machinelearning.model.Config;
import com.machinelearning.model.Plant;

public class PlantPositionSensor extends Sensor {
	
	private char c;
	
	public PlantPositionSensor(char c) {
		this.c = c;
	}

	@Override
	public float readSensorValue(Animal animal) {
		Plant f = environment.getNearestPlant(animal.position);
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
