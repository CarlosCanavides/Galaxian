package Autonoma;

import java.util.List;
import Equipamiento.*;
import Inanimadas.*;
import Controladores.ControladorEnemigo;

public class Enemigo {

	private ControladorEnemigo controlador;
	private Arma arma;
	
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
		return arma.disparar();
	}
}
