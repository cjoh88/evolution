package com.machinelearning.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.machinelearning.EvolutionScreen;
import com.machinelearning.model.Environment;

public class InputController implements InputProcessor{
	
	private EvolutionScreen screen;
	
	public InputController(EvolutionScreen screen) {
		this.screen = screen;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch(keycode) {
		case Input.Keys.SPACE:
			screen.toggleRender();
			break;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
