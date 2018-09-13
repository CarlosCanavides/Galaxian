package edu.uns.galaxian.entidades;
import edu.uns.galaxian.colision.Colisionable;

public abstract class EntidadColisionable extends Entidad implements Colisionable{

	protected float alto;
	protected float ancho;
	protected int vidaActual;
	protected int vidaMaxima;

	public EntidadColisionable(float xPos, float yPos, float alto, float ancho, int vidaMaxima){
		super(xPos, yPos);
		this.alto = alto;
		this.ancho = ancho;
		this.vidaMaxima = vidaMaxima;
		this.vidaActual = vidaMaxima;
	}

	/**
	 * Devuelve la altura de la entidad en pixeles.
	 * @return Altura de la entidad en pixeles.
	 */
	public float getAlto(){
		return alto;
	}
	
	/**
	 * Devuelve el ancho de la entidad en pixeles
	 * @return Ancho de la entidad en pixeles.
	 */
	public float getAncho(){
		return ancho;
	}
	
	/**
	 * Devuelve la vida actual de la entidad.
	 * @return Cantidad de vida de la entidad.
	 */
	public int getVida(){
		return vidaActual;
	}
	
	/**
	 * Setea la vida de la entidad.
	 * @param nuevaVida Nueva cantidad de vida de la entidad.
	 * @throws IllegalArgumentException Si la nueva vida es negativa o es mayor a la vida maxima de la entidad
	 */
	public void setVida(int nuevaVida) throws IllegalArgumentException {
		if(nuevaVida < 0 || nuevaVida > vidaMaxima)
			throw new IllegalArgumentException("La nuevo valor de vida proveido es invalido.");
		vidaActual = nuevaVida;
	}
	
	/**
	 * Setea la vida de la entidad al maximo permitido.
	 */
	public void setVidaAlMaximo(){
		vidaActual = vidaMaxima;
	}
	
}
