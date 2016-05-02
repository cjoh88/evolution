package com.machinelearning.model.sensor;

import com.badlogic.gdx.math.Vector2;
import com.machinelearning.model.Animal;
import com.machinelearning.model.Config;

public class AnimalDistanceSensor extends Sensor {
	
	private char c;
	
	public AnimalDistanceSensor(char c) {
		this.c = c;
	}

	@Override
	public float readSensorValue(Animal animal) {
		Animal a;
		if(c == 'p') {
			a = environment.getNearestPredator(animal.getPosition());
		}
		else {
			a = environment.getNearestAnimal(animal.getPosition());
		}
		Vector2 target = a.position.cpy().sub(animal.position);
		float f = target.len() / Config.WIDTH;
		if(f>1.0f) {
			f = 1.0f;
		}
		return f;
	}

}
