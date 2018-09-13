package edu.uns.galaxian.entidades;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract  class Entidad {

	protected Vector2 posicion;

	public Entidad(float xPos, float yPos){
		posicion = new Vector2(xPos, yPos);
	}

	/**
	 * Setea la posicion de la entidad con la nueva posicion pasada como parametro.
	 * @param nuevaPos Nueva posicion de la entidad representada en un vector
	 * @return Posicion anterior de la entidad
	 */
	public Vector2 setPosicion(Vector2 nuevaPos){
		Vector2 temp = posicion;
		posicion = nuevaPos;
		return temp;
	}

	/**
	 * Setea la posicion de la entidad con la nueva posicion pasada como parametro.
	 * @param xPos Valor en x de la nueva posicion de la entidad
	 * @param yPos Valor en y de la nueva posicion de la entidad
	 * @return Posicion anterior de la entidad
	 */
	public Vector2 setPosicion(float xPos, float yPos){
		Vector2 temp = posicion;
		posicion = new Vector2(xPos, yPos);
		return temp;
	}
	
	/**
	 * Devuelve la posicion actual de la entidad en el mapa.
	 * @return Posicion actual de la entidad.
	 */
	public Vector2 getPosicion(){
		return posicion.cpy();
	}
	
	/**
	 * Dibuja la entidad en el mapa.
	 * @param batch
	 */
	public abstract void dibujar(SpriteBatch batch);
	
	/**
	 * Actualiza la posicion de la entidad en el mapa.
	 */
	public abstract void actualizar();
	
	/**
	 * Elimina la entidad del juego.
	 */
	public abstract void eliminar();
}
