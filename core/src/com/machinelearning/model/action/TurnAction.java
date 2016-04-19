package com.machinelearning.model.action;

import com.badlogic.gdx.math.Vector2;
import com.machinelearning.model.Animal;

public class TurnAction extends Action{
	
	private char d;
	
	public TurnAction(char d) {
		this.d = d;
	}

	@Override
	public void executeAction(Animal animal, float value) {
		//Vector2 oldVelocity = animal.velocity.cpy();
		float angle = value / 5.0f;
		if(d == 'l') {
			animal.velocity.rotateRad(-angle);
		}
		else {
			animal.velocity.rotateRad(angle);
		}
	}

}
