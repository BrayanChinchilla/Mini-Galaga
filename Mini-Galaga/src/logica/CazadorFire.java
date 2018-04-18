package logica;

import java.awt.Graphics;

import myUtilities.RectangleImage;

/*
 * Crea una bala para el Cazador
 * y la independiza en su propia thread para que se mueva
 */
public class CazadorFire extends RectangleImage {
	
	boolean active = true;
	
	Thread threadFire;
		
	public CazadorFire(Cazador cazador, Game game) {
		super("/fuegoEnemigo.png", cazador.x + 22, cazador.y+50, 6, 30);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (y < 650) {
					try {
						Thread.sleep(35);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (!game.paused)
						moveDown();
					if (!game.running)
						destroyBullet();
				}
				
			}
		}).start();
	}
	
	/**
	 * Provoca que la thread pare y la bala desaparece de la pantalla
	 */
	public void destroyBullet() {
		active = false;
		y = 750;
	}
	
	
	public void drawBullet(Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}
	

}
