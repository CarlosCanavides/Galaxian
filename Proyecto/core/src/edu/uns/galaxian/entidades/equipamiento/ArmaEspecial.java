package edu.uns.galaxian.entidades.equipamiento;

import edu.uns.galaxian.entidades.inanimadas.Disparo;

/**
 * Modela las diferentes propiedades que puede tener un arma, sin tener en cuenta quien las usara.
 */
public abstract class ArmaEspecial implements Arma {
	
	protected Disparo disparo;
	
	public Disparo getDisparo() {
		return disparo;
	}

	/**
	 * Setea el tipo de disparo que tendra el arma.
	 * @param nuevoDisparo Disparo que tendra el arma.
	 */
	abstract public void setDisparo(Disparo nuevoDisparo);
	
}
