package logica;

public class Jugador {

	String name;
	int puntos;
	
	public Jugador(String name) {
		this.name = name;
		this.puntos = 0;
	}
	
	public void arcaicoKilled() {
		puntos += 10;
	}
	
	public void cazadorKilled() {
		puntos += 20;
	}
}
