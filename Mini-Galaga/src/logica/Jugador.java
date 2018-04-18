package logica;

public class Jugador {

	String name;
	int puntos;
	
	public Jugador(String name) {
		if (name.equals(""))
			this.name = " -----";
		else
			this.name = name;
		this.puntos = 0;
	}
	
	public void arcaicoKilled() {
		puntos += 10;
	}
	
	public void cazadorKilled() {
		puntos += 20;
	}
	
	public void caoticaKilled() {
		puntos += 30;
	}
}