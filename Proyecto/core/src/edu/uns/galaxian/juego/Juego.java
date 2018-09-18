package edu.uns.galaxian.juego;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import edu.uns.galaxian.juego.exceptions.NonExistentGameDataException;
import edu.uns.galaxian.juego.keys.DatosGuardadosKeys;
import org.json.JSONArray;
import org.json.JSONObject;

public class Juego extends Game {
	// Direccion de los datos guardados
	private static final String DATOS_GUARDADOS_PATH = "./files/save_data.json";

	// Direccion de los datos del juego
	private static final String DATOS_DEL_JUEGO_PATH = "./files/game_data.json";

	private SpriteBatch batch;
	private JSONObject datosGuardados;
	private JSONArray niveles;

	@Override
	public void create () {
		batch = new SpriteBatch();

		// Cargar archivos de configuracion
		datosGuardados = cargarDatosGuardados();
		niveles = cargarDatosDelJuego();

		// Crear nivel correspondiente
		JSONObject configuracionDelNivel = niveles.getJSONObject(datosGuardados.getInt(DatosGuardadosKeys.NIVEL_ALCANZADO.getKey())-1);
		setScreen(new Nivel(configuracionDelNivel, this));
	}

	@Override
	public void render () {
		super.render();
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	/**
	 * Devuelve el SpriteBatch del juego.
	 * @return SpriteBatch del juego
	 */
	public SpriteBatch getBatch(){
		return batch;
	}

	/**
	 * Carga el nivel siguiente al actual.
	 * @param nivelActual Nivel actual
	 */
	public void cargarSiguienteNivel(Nivel nivelActual){
		// TODO Implementar metodo
	}

	// Metodos privados

	/**
	 * Carga los datos guardados en memoria, generando y retornando un objeto JSON.
	 * En caso de que no exista ningun archivo de datos guardados se crea uno nuevo
	 * con un estado por defecto.
	 * @return Objeto JSON con los datos guardados
	 */
	private JSONObject cargarDatosGuardados(){
		FileHandle path = Gdx.files.local(DATOS_GUARDADOS_PATH);
		JSONObject datos;
		if(path.exists()){
			// Se cargan los datos y se encapsulan en un objeto
			datos = new JSONObject(path.readString());
		}
		else{
			// Se crea un objeto con nuevos datos y luego es persistido en memoria
			datos = new JSONObject();
			datos.put(DatosGuardadosKeys.NIVEL_ALCANZADO.getKey(), 1);
			path.writeString(datos.toString(), false);
		}

		return datos;
	}

	/**
	 * Carga los datos del juego, generando y retornando un objeto JSON.
	 * @return Objeto JSON conteniendo los datos del juego
	 * @throws NonExistentGameDataException Si el archivo de datos no existe o es invalido
	 */
	private JSONArray cargarDatosDelJuego() throws NonExistentGameDataException {
		try{
			return new JSONArray(Gdx.files.local(DATOS_DEL_JUEGO_PATH).readString());
		}catch (GdxRuntimeException exception){
			throw new NonExistentGameDataException("Los datos del juego no existen.");
		}
	}
}
