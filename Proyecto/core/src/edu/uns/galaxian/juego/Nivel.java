package edu.uns.galaxian.juego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import edu.uns.galaxian.controladores.ControladorDisparo;
import edu.uns.galaxian.controladores.ControladorEnemigo;
import edu.uns.galaxian.controladores.ControladorEntidad;
import edu.uns.galaxian.entidades.jugador.InputKeyboard;
import edu.uns.galaxian.entidades.jugador.Jugador;
import edu.uns.galaxian.escenario.Background;
import edu.uns.galaxian.juego.keys.GameDataKeys;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Nivel extends ScreenAdapter {

    private Juego juego;
    private Jugador jugador;
    private Background background;
    private Score score;
    private Collection<ControladorEntidad> controladores;
    
    private float contador;
    private int seg;
    private int min;
    private int hora;
    private int puntaje; //TODO fijarse si esta bien tener una clase que se ocupe de esto

    // Constructor
    public Nivel(JSONObject configNivel, Juego juego){
        this.juego = juego;
        this.controladores = new ArrayList<>();
        
        // Inicializar jugador
        JSONObject configJugador = configNivel.getJSONObject(GameDataKeys.NIVEL_JUGADOR.getKey());
        jugador = new Jugador( Gdx.graphics.getWidth()/2, 60, 64, configJugador, this);
        jugador.setProcesadorInput(new InputKeyboard());

        // Inicializar escenario
        background = new Background();
       
        

        // Inicializar controladores
        JSONObject configControladores = configNivel.getJSONObject(GameDataKeys.NIVEL_CONTROLADORES.getKey());
        inicializarControladores(jugador, configControladores);
        
        ControladorDisparo nuevo = new ControladorDisparo();
        controladores.add(nuevo);
        jugador.setControlador(nuevo);
        
      //Inicializa score
        score= new Score();
        
    }


    @Override
    public void render(float delta) {
        SpriteBatch batch = juego.getBatch();
        // Limpiar pantalla
        Gdx.gl.glClearColor(0, 0, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Recibe el tiempo que paso del ultimo frame y lo guarda en una variable float contador
        //que siempre es menor a 1, y aumenta los segundos, cuando pasa los 59seg aumenta los min
        //TODO chequear si conviene un metodo auxiliar que calcule el tiempo (pasas el contador x param)
        contador+= Gdx.graphics.getDeltaTime();
        if(contador>=1) {
        	contador=0;
        	seg++;
        }
        if(seg==60) {
        	seg=0;
        	min++;
        }
        if(min==60) {
        	min=0;
        	hora++;
        }
        
        //Calcula y muestra por consola el tiempo transcurrido y el puntaje
        //TODO insertar graficamente dichos datos
        System.out.println("Hora= "+hora+" Min: "+min+" Seg: "+seg);
        puntaje+=score.getPuntaje();
        System.out.println("Puntaje= "+puntaje);
        
        // Dibujar escenario
        background.draw();

        // Iniciar proceso de dibujado
        batch.begin();
        

        // Dibujar controladores
        for(ControladorEntidad controlador : controladores){
            controlador.actualizarEstado();
            controlador.dibujar(batch);
        }
        
        
     // Dibujar jugador
        jugador.dibujar(batch);
        jugador.actualizar();          // TODO verificar que es el lugar indicado
        
        // Finalizar proceso de dibujado
        batch.end();


    }

    @Override
    public void dispose() {

    }

    /**
     * Inicializa todos los controladores necesarios en este nivel.
     * Requiere una instancia de Jugador y un objeto JSON de configuracion.
     * @param jugador Jugador del nivel
     * @param config Objeto de configuracion de los controladores
     */
    private void inicializarControladores(Jugador jugador, JSONObject config){
        Iterator<String> keys = config.keys();
        String keyActual;
        JSONObject configControladorActual;
        while(keys.hasNext()){
            keyActual = keys.next();
            switch (GameDataKeys.buscarPorKey(keyActual)){
                case CONTROLADOR_ENEMIGO:{
                    configControladorActual = config.getJSONObject(keyActual);
                    ControladorEnemigo controladorEnemigo = new ControladorEnemigo(configControladorActual, jugador);
                    controladores.add(controladorEnemigo);
                }
            }
        }
    }
}
