package edu.uns.galaxian.entidades.equipamiento;

import java.util.List;

import com.badlogic.gdx.math.Vector2;

import edu.uns.galaxian.entidades.inanimadas.*;

/**
 * Modela el tipo de armas que pueden tener las entidades habilitadas a disparar.
 */
public interface Arma {

	/**
	 * Devuelve una coleccion iterable de disparos que realiza el arma.
	 * @return Coleccion iterable de disparos efectuados por el arma.
	 */
	public List<Disparo> disparar(float xPos, float yPos, Vector2 direccion);
	
}
