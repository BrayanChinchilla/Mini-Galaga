package logica;

import java.awt.Graphics;

import myUtilities.RectangleImage;

public class CazadorFire extends RectangleImage {
	
	boolean draw = true;
	
	Thread threadFire;
		
	public CazadorFire(Cazador cazador, Game game) {
		super("/fuegoEnemigo.png", cazador.x + 22, cazador.y+50, 6, 30);
		
		threadFire = new Thread(new Runnable() {
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
				}
				
			}
		});
		threadFire.start();
	}

	public void drawBullet(Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}
	
	public void destroyBullet() {
		draw = false;
		x = 750;
	}
}
