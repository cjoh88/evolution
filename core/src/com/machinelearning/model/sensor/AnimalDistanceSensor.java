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
		float f = 1.0f;
		if (c == 'p') {
			a = environment.getNearestPredator(animal);
			f = environment.findDistance(animal.getPosition(), a.getPosition());
		} else {
			a = environment.getNearestAnimal(animal);
			f = environment.findDistance(animal.position, a.position);
		}

		/*Vector2 target = new Vector2();

		if (a != null)
			target = a.position.cpy().sub(animal.position);
*/
		f /= Config.WIDTH/2;
		if (f > 1.0f) {
			f = 1.0f;
		}
		return f;
	}

}
