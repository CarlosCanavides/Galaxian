package edu.uns.galaxian.entidades.equipamiento;

import java.util.List;

import com.badlogic.gdx.math.Vector2;

import edu.uns.galaxian.entidades.inanimadas.Disparo;
import edu.uns.galaxian.entidades.inanimadas.DisparoEnemigo;

/**
 * Modela el tipo de armas que puede tener una entidad Enemigo.
 */
public class ArmaEnemigo implements Arma {
	
	private ArmaEspecial arma;
	
	/**
	 * Crea una arma especifica para el enemigo en base a un arma especial ,previamente elegida.
	 * @param gun Arma especial que se adaptara para el enemigo.
	 */
	public ArmaEnemigo(ArmaEspecial gun) {
		arma = gun;
		Disparo sh = arma.getDisparo();
		arma.setDisparo(new DisparoEnemigo(sh.getPosicion().x, sh.getPosicion().y, sh.getAlto(),sh.getAncho(), sh.getVida(), sh.getDamage(), sh.geVelocidad(), sh.getTextura())); // Se asegura que el arma especial tenga el tipo de disparo correcto.
	}

	public List<Disparo> disparar(float xPos, float yPos, Vector2 direccion) {
		return arma.disparar(xPos,yPos,direccion);
	}

}
