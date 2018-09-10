package edu.uns.galaxian.entidades.autonoma;

import edu.uns.galaxian.entidades.*;
import edu.uns.galaxian.entidades.autonoma.ia.*;

public abstract class EntidadAutonoma extends EntidadColisionable {

	private InteligenciaArtificial inteligencia;

	public EntidadAutonoma(int xPos, int yPos, int alto, int ancho, int vidaMaxima) {
		super(xPos, yPos, alto, ancho, vidaMaxima);
	}

	/**
	 * Setea la inteligencia de la entidad autonoma.
	 * @param ia nueva Inteligencia Artificial que tendra la entidad autonoma.
	 */
	public void setInteligencia(InteligenciaArtificial ia) {
		inteligencia = ia;
	}
	
	/**
	 * Devuelve la inteligencia artificial que la entidad autonoma posee actualmente.
	 * @return Inteligencia Artificial de la entidad autonoma.
	 */
	public InteligenciaArtificial getInteligencia() {
		return inteligencia;
	}
	
}