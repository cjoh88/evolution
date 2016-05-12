package com.machinelearning.model.sensor;

import com.badlogic.gdx.math.Vector2;
import com.machinelearning.model.Animal;

public class PredatorDirectionSensor extends Sensor {

	private char c;
	
	public PredatorDirectionSensor(char c) {
		this.c = c;
	}
	
	@Override
	public float readSensorValue(Animal animal) {
		Animal food = environment.getNearestPredator(animal);

		Vector2 target;
		
		if(food!=null){
			//target = food.position.cpy().sub(animal.position);
			target = environment.findDirection(animal.getPosition(), food.getPosition());
		}else{
			target = new Vector2();
		}
		
		//target.nor();
		if(c == 'y') {
			return target.y;
		}
		else {
			return target.x;
		}
	}

}
