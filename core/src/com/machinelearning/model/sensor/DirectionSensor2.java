package com.machinelearning.model.sensor;

import com.badlogic.gdx.math.Vector2;
import com.machinelearning.model.Animal;
import com.machinelearning.model.Environment;

public class DirectionSensor2 extends Sensor{
	
	
	@Override
	public float readSensorValue(Animal animal) {
		Vector2 direction = animal.velocity.cpy().nor();
		return (float) (direction.angleRad() / (2 * Math.PI));
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
