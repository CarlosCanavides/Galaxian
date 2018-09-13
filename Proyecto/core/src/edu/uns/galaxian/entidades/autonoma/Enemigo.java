package edu.uns.galaxian.entidades.autonoma;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import edu.uns.galaxian.entidades.equipamiento.*;
import edu.uns.galaxian.entidades.inanimadas.*;
import edu.uns.galaxian.controladores.ControladorEnemigo;
public class Enemigo extends EntidadAutonoma{
	
	// TODO El atributo ancho y alto varia de acuerdo a el tipo de enemigo.
	private static final int ANCHO_MAX = 64;
	private static final int ALTO_MAX = 64;
	
	private Texture textura;

	private ControladorEnemigo controlador;
	private Arma arma;

    public Enemigo(int xPos, int yPos, int vidaMaxima) {
        super(xPos, yPos, ALTO_MAX, ANCHO_MAX, vidaMaxima);
        this.textura = new Texture(Gdx.files.internal("enemigos/enemyBlack2.png"));
    }

    /**
	 * Setea el arma del enemigo con la nueva pasada como par�metro.
	 * @param nuevaArma Nueva arma que tendr� el enemigo.
	 */
	public void setArma(Arma nuevaArma) {
		arma = nuevaArma;
	}
	
	/**
	 * Devuelve el arma que el enemigo posee actualmente.
	 * @return Arma que posee el enemigo.
	 */
	public Arma getArma() {
		return arma;
	}
	
	/**
	 * Devuelve un nuevo disparo realizado por el enemigo.
	 * @return Disparo realizado por el enemigo.
	 */
	public List<Disparo> disparar() {
		return arma.disparar(posicion.x,posicion.y,new Vector2(0,1));
	}
	
	public static int getAnchoMaxEnemigo() {
		return ANCHO_MAX;
	}
	
	public static int getAltoMaxEnemigo() {
		return ALTO_MAX;
	}
	

	// Implementacion de metodos abstractos
    // TODO Implementar metodos
    @Override
    public void actualizar() {

    }

    @Override
    public void dibujar(SpriteBatch batch) {
    	batch.draw(textura, posicion.x-getAncho()/2, posicion.y-getAlto()/2, getAncho(), getAlto());
    }

    @Override
    public void eliminar() {

    }
}
