package com.machinelearning.model.sensor;

import com.badlogic.gdx.math.Vector2;
import com.machinelearning.model.Animal;
import com.machinelearning.model.Config;

public class DSensor extends Sensor {
	
	public enum Target {
		PREDATOR,
		PLANT,
		PREY,
	}
	
	public enum Type {
		DISTANCE,
		ANGLE,
	}
	
	private Target target;
	private Type type;
	
	public DSensor(Target target, Type type) {
		this.target = target;
		this.type = type;
	}

	@Override
	public float readSensorValue(Animal animal) {
		float result;
		Vector2 v;
		switch(target) {
		case PREDATOR:
			v = animal.getEnvironment().getNearestPredator(animal).getPosition().cpy();
			break;
		case PREY:
			v = animal.getEnvironment().getNearestAnimal(animal).getPosition().cpy();
			break;
		case PLANT:
			v = animal.getEnvironment().getNearestPlant(animal.getPosition()).getPosition().cpy();
			break;
		default:
			v = new Vector2();
		}
		switch(type) {
		case ANGLE:
			result = animal.getPosition().angleRad(v);
			result /= 2*Math.PI;
			break;
		case DISTANCE:
			result = animal.getEnvironment().findDistance(animal.getPosition(), v);
			result /= 25;
			if(result > 25) {
				result = 1.0f;
			}
			break;
		default:
			result = 0.0f;
		}
		return result;
	}

}
