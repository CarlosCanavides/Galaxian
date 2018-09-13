package edu.uns.galaxian.entidades.jugador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import edu.uns.galaxian.controladores.ControladorDisparo;
import edu.uns.galaxian.controladores.ControladorEntidad;
import edu.uns.galaxian.entidades.EntidadColisionable;
import edu.uns.galaxian.entidades.equipamiento.Arma;
import edu.uns.galaxian.entidades.equipamiento.ArmaComun;
import edu.uns.galaxian.entidades.equipamiento.ArmaDisparoDoble;
import edu.uns.galaxian.entidades.equipamiento.ArmaEspecial;
import edu.uns.galaxian.entidades.equipamiento.ArmaJugador;
import edu.uns.galaxian.entidades.equipamiento.Escudo;
import edu.uns.galaxian.entidades.inanimadas.Disparo;
import edu.uns.galaxian.juego.Nivel;
import edu.uns.galaxian.juego.keys.GameDataKeys;

import java.util.List;

import org.json.JSONObject;

public class Jugador extends EntidadColisionable {

	private static final int TAMANIO_NAVE = 32;
	private static final long CADENCIA_DISPARO = 400;
 
	private ArmaJugador arma;
	private Escudo escudo;
	private ProcesadorInput input;
	private int velocidadMaxima;
	private Nivel nivel;
	private long tiempoUltimoDisparo;
	private ControladorDisparo controladorDisparos;  // TODO por ahora lo hacemos asi para dibujar el disparo
	
	public Jugador(int xPos, int yPos, int tamano, JSONObject config, Nivel nivel){
	    super(xPos, yPos, TAMANIO_NAVE, TAMANIO_NAVE, config.getInt(GameDataKeys.JUGADOR_VIDA_MAXIMA.getKey()));
		this.nivel = nivel;
		this.tiempoUltimoDisparo = System.currentTimeMillis() - CADENCIA_DISPARO;
		
		// TODO Utilizar el objeto config para setear vida, velocidad maxima, arma, escudo, etc.
		
		velocidadMaxima = config.getInt(GameDataKeys.JUGADOR_VELOCIDAD_MAXIMA.getKey());
		
		String armaElegida = config.getString(GameDataKeys.JUGADOR_ARMA.getKey());
		ArmaEspecial gun = new ArmaComun(); // Por si el archivo es corrupto
		
		if(armaElegida.equals("normal")) {
			gun = new ArmaComun();
		}
		else if(armaElegida.equals("doble")) {
			gun = new ArmaDisparoDoble();
		}
		
		arma = new ArmaJugador(gun);
		
	}
	
	/**
	 * Setea el arma del jugador con la nueva pasada como parametro.
	 * @param nuevaArma Nueva arma que tendria el jugador.
	 */
	public void setArma (ArmaJugador nuevaArma) {
		arma = nuevaArma;
	}
	
	/**
	 * Devuelve el arma que el jugador posee actualmente.
	 * @return Arma que posee el jugador.
	 */
	public Arma getArma() {
		return arma;
	}
	
	/**
	 * Setea el escudo al jugador con el nuevo escudo pasado como parametro.
	 * @param nuevoEscudo nuevo escudo que tendria el jugado.
	 */
	public void setEsdcudo(Escudo nuevoEscudo) {
		escudo = nuevoEscudo;
	}
	
	/**
	 * Devuelve el escudo que el jugador posee actualmente.
	 * @return Escudo que posee el jugador.
	 */
	public Escudo getEscudo() {
		return escudo;
	}
	
	/**
	 * Setea el procesador al jugador con el nuevo procesador pasado como parametro.
	 * @param procesadorInput nuevo procesador que tendria el jugado.
	 */
	public void setProcesadorInput(ProcesadorInput procesadorInput) {
		input = procesadorInput;
	}

	// Implementacion de metodos abstractos
	
	public void actualizar() {
		
		// Actualizar posicion
		float posNueva = (posicion.x + (velocidadMaxima * input.getXAxis() * Gdx.graphics.getDeltaTime()));
		float anchoTextura = getAncho()/2; 
		if(posNueva - anchoTextura >= 0 && posNueva + anchoTextura < Gdx.graphics.getWidth()) {
			posicion.set(posNueva, posicion.y);
		}	
		
		// Verificar disparo
		if(input.sePresionoDisparar() && (System.currentTimeMillis() - tiempoUltimoDisparo) > CADENCIA_DISPARO) {
			List<Disparo> disparos = arma.disparar(posicion.x, posicion.y, new Vector2(0,1));
			for(Disparo d : disparos) {
				controladorDisparos.agregarDisparo(d);
			}
			tiempoUltimoDisparo = System.currentTimeMillis();
		}
	}
	
	public void setControlador(ControladorDisparo c) {
		controladorDisparos = c;
	}

	@Override
	public void dibujar(SpriteBatch batch) {
		// TODO Dibujarse a si mismo utilizando una textura o similar
		Texture textura = new Texture(Gdx.files.internal("jugador/playerShip2_blue.png"));
		batch.draw(textura, posicion.x-getAncho()/2, posicion.y-getAlto()/2, getAncho(), getAlto());
	}

	@Override
	public void eliminar() {
		// TODO Eliminar texturas
	}
}
