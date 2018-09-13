package edu.uns.galaxian.entidades.equipamiento;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import edu.uns.galaxian.entidades.inanimadas.Disparo;
import edu.uns.galaxian.entidades.inanimadas.DisparoJugador;

/**
 * Modela el tipo de arma que puede llegar a tener un jugador.
 */
public class ArmaJugador implements Arma {
	
	private ArmaEspecial arma;
	private static Texture texturaDisparo = new Texture(Gdx.files.internal("disparos/laserGreen11.png"));
	
	/**
	 * Crea una arma especifica para el jugador en base a un arma especial ,previamente elegida.
	 * @param gun Arma especial que se adaptara para el jugador.
	 */
	public ArmaJugador(ArmaEspecial gun) {
		arma = gun;
		Disparo sh = arma.getDisparo();
		Disparo dg = new DisparoJugador(0,0,15,5,20,20,170, texturaDisparo);
		dg.setDireccion(new Vector2(0,1));
		arma.setDisparo(dg);
		// Se asegura que el arma especial tenga el tipo de disparo correcto.
	}

	public List<Disparo> disparar(float xPos, float yPos, Vector2 direccion) {
		return arma.disparar(xPos,yPos+(yPos/2),direccion);
	}

}
