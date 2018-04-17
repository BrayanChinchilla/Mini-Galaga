package logica;

import java.awt.Graphics;

import myUtilities.RectangleImage;

public class Cazador extends Enemy{
	
	Game game;
	Thread threadCazador;
	
	CazadorFire[] fire = new CazadorFire[5];
	int numBullets = 0;

	public Cazador(Game game, int randomX) {
		super("/cazador.png", randomX, 0, 50, 50);
		this.game = game;
		vidaRestante = 2;
		
		threadCazador = new Thread(new Runnable() {
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
					
					if (checkForCollision(game)) {
						vidaRestante--;
						if (vidaRestante == 0)
							game.jugador.cazadorKilled();
					}
				}	
			}
		});
		threadCazador.start();
		
		Thread threadFire = new Thread(new Runnable() {
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
		});
		threadFire.start();

	}
	
	public void fire() {
		fire[numBullets % 10] = new CazadorFire(this, game);
		numBullets++;
	}

}

