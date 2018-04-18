package logica;

import java.awt.Graphics;

import myUtilities.*;

public class Arcaico extends Enemy{
	
	/**
	 * Crea una nave arcaica y la independiza en su propia thread
	 * @param game
	 * @param randomX coordenada X en donde aparecera la nave
	 */
	public Arcaico(Game game, int randomX) {
		super("/arcaico.png", randomX, 0, 50, 50);
		vidaRestante = 3;
		
		new Thread(new Runnable() {
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
					
					switch(checkForCollision(game)) {
					case ("/fuegoJugador.png"):
						vidaRestante--;
						if (vidaRestante == 0) {
							destroyMyself();
							game.jugador.cazadorKilled();
						}
						break;
					case ("/fuegoJugadorSpecial.png"):
						vidaRestante = 0;
						destroyMyself();
						game.jugador.cazadorKilled();
						break;
					}
					
					if (!game.running)
						destroyMyself();
				}
				
			}
		}).start();

	}

}

