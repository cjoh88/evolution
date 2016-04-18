package com.machinelearning.model;

public abstract class Sensor {
	
	protected Environment environment;
	
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
	public abstract float readSensorValue(Animal animal);

}
