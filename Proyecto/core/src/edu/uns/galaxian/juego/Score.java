package edu.uns.galaxian.juego;


public class Score {
	
	private int puntaje;
	
	public Score() {
		puntaje=0;
	}
	
	public int getPuntaje(){
		return puntaje;
	}
	
	public void setPuntaje(int p){
		puntaje+=p;
	}
	
}
