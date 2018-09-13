package edu.uns.galaxian.entidades.inanimadas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import edu.uns.galaxian.entidades.EntidadColisionable;

public class Disparo extends EntidadColisionable{

	protected float damage;
	protected float velocidad;
	protected Vector2 direccion;
	protected Texture textura;
	
	public Disparo(float xPos, float yPos, float alto, float ancho, int vidaMaxima,float danio, float velocidad, Texture textura) {
		super(xPos,yPos,alto,ancho,vidaMaxima);
		this.textura = textura;
		this.velocidad = velocidad;
		damage = danio;
	}
	
	/**
	 * Devuelve el danio asociado al disparo.
	 * @return Danio que provoca el disparo.
	 */
	public float getDamage() {
		return damage;
	}
	
	/**
	 * Devuelve la direccion del disparo.
	 * @return Direccion del disparo.
	 */
	public Vector2 getDireccion() {
		return direccion;
	}
	
	/**
	 * Devuelve la textura del disparo.
	 * @return Textura asociada al disparo.
	 */
	public Texture getTextura() {
		return textura;
	}
	
	/**
	 * Devuelve la velocidad del disparo.
	 * @return Velocidad del disparo.
	 */
	public float geVelocidad() {
		return velocidad;
	}
	
	/**
	 * Setea la direccion del disparo.
	 * @param nuevaDireccion Nueva direccion que tendra el disparo.
	 */
	public void setDireccion(Vector2 nuevaDireccion) {
		direccion = nuevaDireccion;
	}
	
	/**
	 * Devuelve un disparo clonado.
	 */
	public Disparo clone() {
		Disparo clon = new Disparo(posicion.x, posicion.y, alto, ancho, vidaMaxima, damage, velocidad,  textura);
		clon.setDireccion(direccion);
		return clon;
	}

	@Override
	public void actualizar() {
		posicion.add(velocidad * direccion.x * Gdx.graphics.getDeltaTime(), velocidad * direccion.y * Gdx.graphics.getDeltaTime());
	}

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dibujar(SpriteBatch batch) {
		batch.draw(textura, posicion.x-getAncho()/2, posicion.y-getAlto()/2, getAncho(), getAlto());
	}
	
}
