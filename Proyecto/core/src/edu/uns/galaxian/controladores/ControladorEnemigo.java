package edu.uns.galaxian.controladores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import edu.uns.galaxian.entidades.autonoma.Enemigo;
import edu.uns.galaxian.entidades.jugador.Jugador;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ControladorEnemigo implements ControladorEntidad {

    private static final int TAMANIO_NAVES = 64;

    private List<List<Enemigo>> enemigos;
    private Jugador jugador;

    public ControladorEnemigo(JSONObject config, Jugador jugador){
        this.jugador = jugador;

        // Crear formacion de enemigos
        JSONArray formacion = config.getJSONArray("formacion");
        enemigos = new ArrayList<>(formacion.length());
        for(int i = 0; i < formacion.length(); i++) {
            JSONArray fila = formacion.getJSONArray(i);
            List<Enemigo> filaLista = new ArrayList<Enemigo>(fila.length());
            for(int j = 0; j < fila.length(); j++){
                // TODO Los enemigos deben depender de la informacion provista, ademas se deben colocar en distintas posiciones
                Enemigo enemigo = new Enemigo(getPosX(fila.length(),j),getPosY(i),5);
                // TODO setear la IA al enemigo
                filaLista.add(enemigo);
            }
            enemigos.add(filaLista);
        }
    }
    
    private int getPosX(int cantidadNaves, int j) {
    	int medio = Gdx.graphics.getWidth() / 2;
    	int margen = 15;
    	int resultado = 0;
    	int espacioOcupado;
    	int espacioSobrante;
    	
    	if(cantidadNaves%2==0) {
    		espacioOcupado = (cantidadNaves/2 * Enemigo.getAnchoMaxEnemigo()) + (cantidadNaves/2 * margen);
    	}
    	else {
    		espacioOcupado = (cantidadNaves/2 * Enemigo.getAnchoMaxEnemigo()) + (cantidadNaves/2 * margen + Enemigo.getAnchoMaxEnemigo()/2);
    	}
    	
    	espacioSobrante = medio - espacioOcupado;
    	int aux = 0;
		for(int i=0; i<=j; i++) {
			aux += Enemigo.getAnchoMaxEnemigo() + margen;
		}
		resultado = aux - margen - (Enemigo.getAnchoMaxEnemigo()/2) + espacioSobrante;
    	
    	return resultado;
    }
    
    public int getPosY(int numeroFila) {
    	int margen = 0;
    	return (int) (Gdx.graphics.getHeight() - ((numeroFila+1)*Enemigo.getAltoMaxEnemigo()*1.2) - margen);
    }

    public Vector2 getPosicionJugador(){
        return jugador.getPosicion();
    }

    @Override
    public void actualizarEstado() {
        // TODO Este metodo debe decidir cuando un enemigo sale de la formacion
        for(List<Enemigo> fila : enemigos){
            for(Enemigo enemigo : fila){
                // TODO Se debe actualizar el enemigo
            }
        }
    }

    @Override
    public void dibujar(SpriteBatch batch) {
        for(List<Enemigo> fila : enemigos){
            for(Enemigo enemigo : fila){
                enemigo.dibujar(batch);
            }
        }
    }
}
