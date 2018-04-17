package logica;

import java.awt.Graphics;

import myUtilities.*;

public class Arcaico extends Enemy{
	
	Thread threadArcaico;

	/**
	 * Crea una nave arcaica y la independiza en su propia thread
	 * @param game
	 * @param randomX coordenada X en donde aparecera la nave
	 */
	public Arcaico(Game game, int randomX) {
		super("/arcaico.png", randomX, 0, 50, 50);
		vidaRestante = 3;
		
		threadArcaico = new Thread(new Runnable() {
			@Override
			public void run() {
				while (y < 650 && vidaRestante > 0) {
					try {
						Thread.sleep(77);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					if(!game.paused)
						moveDown();
					
					if (checkForCollision(game)) {
						vidaRestante--;
						if (vidaRestante == 0)
							game.jugador.arcaicoKilled();
					}	
				}
				
			}
		});
		threadArcaico.start();

	}

}

