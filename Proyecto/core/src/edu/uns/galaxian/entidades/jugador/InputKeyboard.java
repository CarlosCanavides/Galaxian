package edu.uns.galaxian.entidades.jugador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputKeyboard implements ProcesadorInput {

	public int getXAxis() {
		boolean sePresionoIzquierda =  Gdx.input.isKeyPressed(Input.Keys.LEFT);
		boolean sePresionoDerecha   = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
		if(sePresionoIzquierda) return -1;
		if(sePresionoDerecha) return 1;
		else return 0;
	}

	public boolean sePresionoDisparar() {
		boolean sePresionoDisparar =  Gdx.input.isKeyPressed(Input.Keys.SPACE);
		return sePresionoDisparar;
	}

}
