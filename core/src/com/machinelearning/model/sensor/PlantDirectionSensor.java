package com.machinelearning.model.sensor;

import com.badlogic.gdx.math.Vector2;
import com.machinelearning.model.Animal;
import com.machinelearning.model.Environment;
import com.machinelearning.model.Plant;

public class PlantDirectionSensor extends Sensor{
	
	private char c;
	
	public PlantDirectionSensor(char c) {
		this.c = c;
	}
	
	@Override
	public float readSensorValue(Animal animal) {
		Plant food = environment.getNearestPlant(animal.position);
		Vector2 target = food.position.cpy().sub(animal.position);
		target.nor();
		if(c == 'y') {
			return target.y;
		}
		else {
			return target.x;
		}
	}

	/*@Override
	public float readSensorValue(Animal animal) {
		//TODO Implement
		Food food = environment.getNearestFood(animal.position);
		if(c == 'y') {
			return food.y() / Environment.HEIGHT;
		}
		else {
			return food.x() / Environment.WIDTH;
		}
	}*/

}
