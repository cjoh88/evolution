package com.machinelearning.model;

public class Animal {
	
	private Sensor[] sensors;
	private float[] sensorData;
	
	private Action[] actions;
	private float[] actionData;
	
	public Animal(Sensor[] sensors, Action[] actions) {
		this.sensors = sensors;
		this.actions = actions;
		this.sensorData = new float[sensors.length];
		this.actionData = new float[actions.length];
	}
	
	public void readSensorData() {
		for(int i=0; i<sensors.length; i++) {
			sensorData[i] = sensors[i].readSensorValue(this);
		}
	}
	
	public void executeAction() {
		for(int i=0; i<actions.length; i++) {
			actions[i].executeAction(this, actionData[i]);
		}
	}
	
	public void update(float delta) {
		//TODO Animal.update()
	}

}
