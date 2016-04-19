package com.machinelearning.model;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.machinelearning.model.action.Action;
import com.machinelearning.model.sensor.Sensor;

public class Animal {
	
	private static Random random = new Random();
	
	private Sensor[] sensors;
	private float[] sensorData;
	
	private NeuralNetwork ann;
	
	private Action[] actions;
	private float[] actionData;
	
	public Vector2 position;
	public Vector2 velocity;
	//public Vector2 velocity;
	
	private float speed;
	
	private Color color;
	
	public Animal(Sensor[] sensors, Action[] actions) {
		this.sensors = sensors;
		this.actions = actions;
		this.sensorData = new float[sensors.length];
		this.actionData = new float[actions.length];
		this.position = new Vector2(
			random.nextFloat() * Environment.WIDTH, 
			random.nextFloat() * Environment.HEIGHT
		);
		this.velocity = new Vector2(
			random.nextFloat() - 0.5f,
			random.nextFloat() - 0.5f
		);
		//this.velocity = new Vector2(newVelocity);
		this.speed = 3.0f;
		this.color = Color.VIOLET;
		this.ann = new NeuralNetwork(sensors.length, sensors.length * 2, actions.length);
	}
	
	public void readSensorData() {
		for(int i=0; i<sensors.length; i++) {
			sensorData[i] = sensors[i].readSensorValue(this);
		}
		actionData = ann.execute(sensorData);
	}
	
	public void executeAction() {
		for(int i=0; i<actions.length; i++) {
			actions[i].executeAction(this, actionData[i]);
		}
	}
	
	public void update(float delta) {
		velocity.nor();
		velocity.scl(speed);
		velocity.scl(delta);
		position.add(velocity);
		//velocity.x = velocity.x;
		//velocity.y = velocity.y;
		wrapAround();
	}
	
	private void wrapAround() {
		if(position.x >= Environment.WIDTH) {
			position.x -= Environment.WIDTH;
		}
		else if (position.x < 0) {
			position.x += Environment.WIDTH;
		}
		if(position.y >= Environment.HEIGHT) {
			position.y -= Environment.HEIGHT;
		}
		else if (position.y < 0) {
			position.y += Environment.HEIGHT;
		}
	}
	
	public float x() {
		return position.x;
	}
	
	public float y() {
		return position.y;
	}
	
	public Color color() {
		return color;
	}
	

}
