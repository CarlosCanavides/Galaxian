package edu.uns.galaxian.entidades.equipamiento;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import edu.uns.galaxian.entidades.inanimadas.Disparo;

public class ArmaComun extends ArmaEspecial {

	private static float damageDisparo = 50;
	private static float velocidad = 10;
	private static Texture texturaDisparo = new Texture(Gdx.files.internal("disparos/fire01.png")); // textura por defecto
	
	/**
	 * Crea un arma con disparo doble; por defecto el tipo de Disparo sera comun (ni DisparoEnemigo, ni DisparoJugador).
	 */
	public ArmaComun() {
		disparo = new Disparo(0,0,10,10,10,damageDisparo,velocidad,texturaDisparo);  // TODO inicializacion por defecto por ahora
	}
	
	public List<Disparo> disparar(float xPos, float yPos, Vector2 direccion) {
		disparo.setDireccion(direccion);
		disparo.setPosicion(xPos, yPos);
		List<Disparo> fire = new LinkedList<Disparo>();
		fire.add(disparo.clone());
		return fire;
	}

	public void setDisparo(Disparo nuevoDisparo) {
		this.disparo = nuevoDisparo;
	}
	
}
