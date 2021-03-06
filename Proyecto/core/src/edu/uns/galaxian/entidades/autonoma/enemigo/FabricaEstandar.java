package edu.uns.galaxian.entidades.autonoma.enemigo;

import edu.uns.galaxian.controladores.ControladorEnemigo;
import edu.uns.galaxian.entidades.status.StatusMutableVida;
import edu.uns.galaxian.entidades.status.StatusVida;
import edu.uns.galaxian.nave.enemigo.NaveKamikaze;

public class FabricaEstandar implements FabricaEnemigos {

	public Enemigo getKamikaze(int xPos, int yPos, ControladorEnemigo controlador, StatusVida estadoJugador) {
		NaveKamikaze nave = new NaveKamikaze(60, 200, 20, estadoJugador);
		return new Enemigo(xPos, yPos, nave, controlador);
	}

	public Enemigo getKamikazeAleatorio(int xPos, int yPos, ControladorEnemigo controlador) {
		NaveKamikaze nave = new NaveKamikaze(80, 200, 20, new StatusMutableVida());
		return new Enemigo(xPos, yPos, nave, controlador);
	}

	public Enemigo getKamikazeMixto(int xPos, int yPos, ControladorEnemigo controlador, StatusVida estadoJugador) {
		NaveKamikaze nave = new NaveKamikaze(70, 200, 20, estadoJugador);
		return new Enemigo(xPos, yPos, nave, controlador);
	}

	public Enemigo getArmado(int xPos, int yPos, ControladorEnemigo controlador, StatusVida estadoJugador) {
		NaveKamikaze nave = new NaveKamikaze(60, 200, 20, estadoJugador);
		return new Enemigo(xPos, yPos, nave, controlador);
	}

	public Enemigo getArmadoDebil(int xPos, int yPos, ControladorEnemigo controlador, StatusVida estadoJugador) {
		NaveKamikaze nave = new NaveKamikaze(80, 200, 20, estadoJugador);
		return new Enemigo(xPos, yPos, nave, controlador);
	}
}
