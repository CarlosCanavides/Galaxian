package edu.uns.galaxian.controladores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import edu.uns.galaxian.colision.DetectorColision;
import edu.uns.galaxian.entidades.autonoma.enemigo.Enemigo;
import edu.uns.galaxian.entidades.autonoma.enemigo.FabricaEnemigos;
import edu.uns.galaxian.entidades.autonoma.enemigo.TipoEnemigo;
import edu.uns.galaxian.entidades.status.StatusVida;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ControladorEnemigo implements ControladorEntidad {

    private static final int TAMANIO_NAVES = 40;
    
    private static final long CADENCIA_ATAQUE = 1100;
    private long ultimoAtaque;

    private List<List<Enemigo>> enemigos;
    private List<Enemigo> listaEliminar;
    private StatusVida estadoJugador;
    private DetectorColision detector;
    private ControladorDisparo controladorDisparo;
    private Map<Enemigo,Boolean> atacantes;

    public ControladorEnemigo(FabricaEnemigos fabrica, List<List<TipoEnemigo>> formacion, StatusVida estadoJugador, DetectorColision detector, ControladorDisparo cDisparos){
    	this.detector = detector;
    	this.estadoJugador = estadoJugador;
    	this.controladorDisparo = cDisparos;
        listaEliminar = new LinkedList<>();
        atacantes = new HashMap<>();
        
        this.ultimoAtaque = System.currentTimeMillis();

        // Crear formacion de enemigos
        enemigos = new ArrayList<>(formacion.size());
        int numFila = 0;
        int numColumna = 0;
        for(List<TipoEnemigo> fila : formacion) {
            List<Enemigo> filaDeEnemigos = new ArrayList<>(fila.size());
            for(TipoEnemigo tipoEnemigo : fila){
                Enemigo enemigo = crearEnemigoCorrespondiente(tipoEnemigo, fabrica, getPosX(fila.size(), numColumna), getPosY(numFila));
                // TODO setear la IA al enemigo
                detector.registrarColisionable(enemigo);
                filaDeEnemigos.add(enemigo);
                numColumna++;
                
                // TODO experimento para poder realizar un ataque de enemigos
                atacantes.put(enemigo,false);
                
            }
            enemigos.add(filaDeEnemigos);
            numFila++;
            numColumna = 0;
        }
    }

    private int getPosX(int cantidadNaves, int j) {
    	int mitadPantalla = Gdx.graphics.getWidth() / 2;
    	int margen = 25;
    	int resultado = 0;
    	int espacioOcupado;
    	int espacioSobrante;

    	if(cantidadNaves%2==0) {
    		espacioOcupado = (cantidadNaves/2 * TAMANIO_NAVES) + (cantidadNaves/2 * margen);
    	}
    	else {
    		espacioOcupado = (cantidadNaves/2 * TAMANIO_NAVES) + (cantidadNaves/2 * margen + TAMANIO_NAVES/2);
    	}

    	espacioSobrante = mitadPantalla - espacioOcupado;
    	int aux = 0;
		for(int i=0; i<=j; i++) {
			aux += TAMANIO_NAVES + margen;
		}
		resultado = aux - margen - (TAMANIO_NAVES/2) + espacioSobrante;

    	return resultado;
    }

    private int getPosY(int numeroFila) {
    	int margen = 10;
    	return Gdx.graphics.getHeight() - (numeroFila+1)*TAMANIO_NAVES - margen;
    }

    private Enemigo crearEnemigoCorrespondiente(TipoEnemigo tipoEnemigo, FabricaEnemigos fabrica, int xPos, int yPos){
        Enemigo nuevoEnemigo = null;
        switch (tipoEnemigo){
            case KAMIKAZE: { nuevoEnemigo = fabrica.getKamikaze(xPos, yPos, this, estadoJugador); break; }
            case KAMIKAZE_ALEATORIO: { nuevoEnemigo = fabrica.getKamikazeAleatorio(xPos, yPos, this); break;}
            case KAMIKAZE_MIXTO:{ nuevoEnemigo = fabrica.getKamikazeMixto(xPos, yPos, this, estadoJugador); break; }
            case ARMADO: { nuevoEnemigo = fabrica.getArmado(xPos, yPos, this, estadoJugador); break; }
            case ARMADO_DEBIL:{ nuevoEnemigo = fabrica.getArmadoDebil(xPos, yPos, this, estadoJugador); break; }
        }
        return nuevoEnemigo;
    }

    public Vector2 getPosicionJugador(){
        return estadoJugador.getPosicion();
    }

    @Override
    public void actualizarEstado(float delta) {
  
    	realizarAtaque();
    	
    	for(List<Enemigo> fila : enemigos) {
    		for(Enemigo enemigo : fila) {
                // TODO Se debe actualizar el enemigo
    			enemigo.actualizar(delta);
    			if(atacantes.get(enemigo) == true) {
    				controladorDisparo.agregarDisparos(enemigo.disparar());
    			}
                detector.verificarYResolverColisiones(enemigo);
            }
    	}
    	
    	for(Enemigo eliminado : listaEliminar) {
    		boolean encontre = false;
    		for(int i=0; i<enemigos.size() && !encontre; i++) {
    			List<Enemigo> fila = enemigos.get(i);
    			for(int j=0; j<fila.size() && !encontre ;j++) {
    				if(fila.get(j)==eliminado) {
    					encontre = true;
    					fila.remove(eliminado);
    					detector.eliminarEntidad(eliminado);
    					atacantes.remove(eliminado);  // provisorio
    				}
    			}
    		}
		}
    	listaEliminar = new LinkedList<>();
    }
    
    private void realizarAtaque() {
    	if(TimeUtils.timeSinceMillis(ultimoAtaque) > CADENCIA_ATAQUE) {
    		
    		Random r = new Random();
    		int filaAtacante = r.nextInt(enemigos.size());
    		int cantidad = enemigos.get(filaAtacante).size();
    		while(cantidad==0) {
    			filaAtacante = r.nextInt(enemigos.size());
    			cantidad = enemigos.get(filaAtacante).size();
    		}
    		List<Enemigo> filaDeEnemigos = enemigos.get(filaAtacante);
    		Random rd = new Random();
    		int num_Enemigo = rd.nextInt(filaDeEnemigos.size());
    		Enemigo elegido = filaDeEnemigos.get(num_Enemigo);
    		elegido.atacar();
    		atacantes.put(elegido,true);
    		ultimoAtaque = TimeUtils.millis();
    	}
    }

    public void dibujar(SpriteBatch batch) {
        for(List<Enemigo> fila : enemigos){
            for(Enemigo enemigo : fila){
                enemigo.dibujar(batch);
            }
        }
    }
	
	public void deregistrar(Enemigo enemy) {
		listaEliminar.add(enemy);
	}
}
