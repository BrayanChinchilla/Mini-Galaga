package logica;

import java.awt.Graphics;

import myUtilities.RectangleImage;

/*
 * Crea una bala para el Falcon
 * y la independiza en su propia thread para que se mueva
 */
public class FalconFire extends RectangleImage {
	
	boolean active = true;
	String type;
		
	public FalconFire(Game game, String type) {
		super(type, game.falcon.x + 10, game.falcon.y-50+20, 30, 30);
		
		this.type = type;
		
		new Thread (new Runnable() {
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
					if (!game.running)
						destroyBullet();
				}
				
			}
		}).start();
	}
	
	/*
	 * Provoca que la thread pare y la bala desaparezca de la pantalla
	 */
	public void destroyBullet() {
		active = false;
		y = -100;
	}
	
	public void drawBullet(Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}
}
