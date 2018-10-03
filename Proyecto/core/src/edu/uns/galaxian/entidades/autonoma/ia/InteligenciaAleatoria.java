package edu.uns.galaxian.entidades.autonoma.ia;

import com.badlogic.gdx.math.Vector2;

import edu.uns.galaxian.entidades.autonoma.enemigo.Enemigo;
import edu.uns.galaxian.entidades.status.StatusMutableVida;

public class InteligenciaAleatoria implements InteligenciaArtificial {
	
	private Enemigo enemigo;
	
	public InteligenciaAleatoria(Enemigo controlado) {
		enemigo = controlado;
	}

	public void pensar(StatusMutableVida estado) {
		float posY = estado.getPosicion().y - 1 ;
		float posX = (float) (estado.getPosicion().x + 10*Math.sin(posY/35));
		Vector2 nueva_pos = new Vector2(posX,posY);
		estado.setPosicion(nueva_pos);
	}

}