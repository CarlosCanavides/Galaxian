package edu.uns.galaxian.entidades.status;

import com.badlogic.gdx.math.Vector2;

public interface Status{

	/**
	 * Retorna la posicion actual.
	 * @return Posicion actual
	 */
	public Vector2 getPosicion();

	/**
	 * Retorna la velocidad actual.
	 * @return Velocidad actual
	 */
	public Vector2 getVelocidad();

	/**
	 * Retorna la rotacion actual en grados.
	 * El eje horizontal de la pantalla en sentido derecho, se considera grado 0.
	 * @return Rotacion actual en grados
	 */
	public float getRotacion();
}
