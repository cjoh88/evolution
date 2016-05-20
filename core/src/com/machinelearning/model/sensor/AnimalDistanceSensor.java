package com.machinelearning.model.sensor;

import com.machinelearning.model.Animal;
import com.machinelearning.model.Config;
import com.machinelearning.model.Plant;

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
		} else if(c=='d') {
			a = environment.getNearestAnimal(animal);
			f = environment.findDistance(animal.position, a.position);
		}else{
			Plant ab = environment.getNearestPlant(animal.getPosition());
			f = environment.findDistance(animal.position, ab.position);
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
