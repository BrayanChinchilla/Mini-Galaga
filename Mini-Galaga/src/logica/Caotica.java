package logica;

import java.awt.Graphics;

import myUtilities.RectangleImage;

public class Caotica extends Enemy{
	
	/**
	 * Crea una nave caotica y la independiza en su propia thread
	 * @param game
	 * @param randomX coordenada X en donde aparecera la nave
	 */
	public Caotica(Game game, int randomX) {
		super("/caotica.png", randomX, 0, 50, 50);
		vidaRestante = 1;
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (y < 650) {
					try {
						Thread.sleep(39);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					if(!game.paused)
						moveDown();
					
					switch(checkForCollision(game)) {
					case ("/fuegoJugadorSpecial.png"):
						vidaRestante = 0;
						destroyMyself();
						game.jugador.caoticaKilled();
						break;
					}
					
					if (!game.running)
						destroyMyself();
				}	
			}
		}).start();

	}

}

