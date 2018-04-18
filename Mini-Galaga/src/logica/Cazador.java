package logica;

import java.awt.Graphics;

import myUtilities.RectangleImage;

public class Cazador extends Enemy{
	
	Game game;
	
	CazadorFire[] fire = new CazadorFire[5];
	int numBullets = 0;

	/**
	 * Crea una nave cazador y la independiza en su propia thread
	 * @param game
	 * @param randomX coordenada X en donde aparecera la nave
	 */
	public Cazador(Game game, int randomX) {
		super("/cazador.png", randomX, 0, 50, 50);
		this.game = game;
		vidaRestante = 2;
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (y < 650 && vidaRestante > 0) {
					try {
						Thread.sleep(54);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (!game.paused)
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
		
		new Thread (new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				while(y < 650 && vidaRestante > 0) {
					if (!game.paused)
						fire();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

	}
	
	public void fire() {
		fire[numBullets % 5] = new CazadorFire(this, game);
		numBullets++;
	}

}

