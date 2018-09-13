package edu.uns.galaxian.entidades.jugador;

public interface ProcesadorInput {

	/*
	 * Devuelve 1 si se presiono LEFT, devuelve -1 si se presiono RIGHT
	 * @return Direccion en la que debe moverse el jugador
	 */
	public int getXAxis();
	
	/*
	 * Devuelve si se acciono la tecla disparar
	 * @ true si disparo, false en caso contrario
	 */
	public boolean sePresionoDisparar();
}
