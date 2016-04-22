package com.machinelearning.model;

import java.util.Random;

import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.machinelearning.Utility;
import com.machinelearning.model.action.Action;
import com.machinelearning.model.sensor.Sensor;

public class Animal {
	
	//TODO Give each animal a unique ID
	
	private final long id = Utility.getID();
	
	private static Random random = new Random();
	
	private Sensor[] sensors;
	private double[] sensorData;
	
	private Action[] actions;
	private double[] actionData;
	
	//private NeuralNetwork ann;
	private MultiLayerPerceptron ann;
	
	private int fitness;
	private Environment environment;
	
	public Vector2 position;
	public Vector2 velocity;
	//public Vector2 velocity;
	
	private float speed;
	
	private Color color;
	
	public Animal(Environment environment, Sensor[] sensors, Action[] actions) {
		this.environment = environment;
		this.sensors = sensors;
		this.actions = actions;
		this.sensorData = new double[sensors.length];
		this.actionData = new double[actions.length];
		this.position = new Vector2(
			random.nextFloat() * Environment.WIDTH, 
			random.nextFloat() * Environment.HEIGHT
		);
		this.velocity = new Vector2(
			random.nextFloat() - 0.5f,
			random.nextFloat() - 0.5f
		);
		//this.velocity = new Vector2(newVelocity);
		this.speed = 15;//3.0f;
		this.color = Color.VIOLET;
		//this.ann = new NeuralNetwork(sensors.length, sensors.length * 2, actions.length);
		this.ann = new MultiLayerPerceptron(TransferFunctionType.GAUSSIAN, sensors.length, sensors.length, actions.length);
		
		this.fitness = 0;		
	}
	
	public void readSensorData() {
		for(int i=0; i<sensors.length; i++) {
			sensorData[i] = sensors[i].readSensorValue(this);
		}
		//actionData = ann.execute(sensorData);

		ann.setInput(sensorData);
		ann.calculate();
		actionData = ann.getOutput();
	}
	
	public void executeAction() {
		for(int i=0; i<actions.length; i++) {
			actions[i].executeAction(this, (float) actionData[i]);
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
		hasFoundFood();
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
	
	public void hasFoundFood() {
		for(Food f : environment.getFood()) {
			if(position.dst2(f.position) < 0.5f) {
				//TODO include ID in log
				//Utility.log("Animal " + id + " found food at " + f.x() + ", " + f.y());
				fitness += 1;
				f.found();
			}
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
	
	public int fitness() {
		return fitness;
	}
	
	public void setFitness(int a){
		fitness = a;
		
	}
	
	public long id() {
		return id;
	}
	
	public Double[] getGenome() {
		return ann.getWeights();
	}
	
	public void setGenome(double[] genome) {
		ann.setWeights(genome);
	}
	
	public Environment getEnvironment() {
		return environment;
	}
	
	public Sensor[] getSensors() {
		return sensors;
	}
	
	public Action[] getActions() {
		return actions;
	}
	

}
