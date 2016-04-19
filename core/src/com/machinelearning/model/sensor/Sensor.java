package com.machinelearning.model.sensor;

import com.machinelearning.model.Animal;
import com.machinelearning.model.Environment;

public abstract class Sensor {
	
	protected Environment environment;
	
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
	public abstract float readSensorValue(Animal animal);

}
