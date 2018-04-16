package logica;

import java.awt.Graphics;

import myUtilities.SquareImage;

public class CazadorFuego extends SquareImage {
	
	Thread threadFire;
		
	public CazadorFuego(Cazador cazador) {
		super("/fuegoEnemigo.png", cazador.xPosition + 22, cazador.yPosition+50, 6, 30);
		yVelocity = 15;
		
		threadFire = new Thread(new Runnable() {
			@Override
			public void run() {
				while (yPosition < 650) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					move(0, yVelocity);
				}
				
			}
		});
		threadFire.start();
	}

	public void drawBullet(Graphics g) {
		g.drawImage(img, xPosition, yPosition, imgWidth, imgHeight, null);
	}
}
