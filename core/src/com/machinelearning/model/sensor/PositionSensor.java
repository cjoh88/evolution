package com.machinelearning.model.sensor;

import com.badlogic.gdx.math.Vector2;
import com.machinelearning.model.Animal;
import com.machinelearning.model.Environment;

public class PositionSensor extends Sensor{
	
	private char c;
	
	public PositionSensor(char c) {
		this.c = c;
	}
	
	@Override
	public float readSensorValue(Animal animal) {
		Vector2 direction = animal.velocity.cpy().nor();
		if(c == 'y') {
			return direction.y;
		}
		else {
			return direction.x;
		}
	}

	/*@Override
	public float readSensorValue(Animal animal) {
		if(c == 'y') {
			return animal.y() / Environment.HEIGHT;
		}
		else {
			return animal.x() / Environment.WIDTH;
		}
	}*/

}
