package com.machinelearning.model.action;

import com.machinelearning.model.Animal;

public class SpeedAction extends Action {

	@Override
	public void executeAction(Animal animal, float value) {
		float speed = animal.getMaxSpeed() * value;
		animal.setSpeed(speed);
	}

}
