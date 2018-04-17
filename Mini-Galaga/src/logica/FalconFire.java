package logica;

import java.awt.Graphics;

import myUtilities.RectangleImage;

public class FalconFire extends RectangleImage {
	
	Thread threadFire;
	boolean draw = true;
		
	public FalconFire(Game game) {
		super("/fuegoJugador.png", game.falcon.x + 10, game.falcon.y-50+20, 30, 30);
		
		threadFire = new Thread(new Runnable() {
			@Override
			public void run() {
				while (y > 0 - height) {
					try {
						Thread.sleep(23);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (!game.paused)
						moveUp();
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
		y = -100;
	}
}
