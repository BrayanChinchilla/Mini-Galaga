package logica;

import java.awt.Graphics;

import myUtilities.SquareImage;

public class JugadorFuego extends SquareImage {
	
	Thread threadFire;
		
	public JugadorFuego(MiniGalaga game) {
		super("/fuegoJugador.png", game.pnlSpace.jugador.xPosition + 10, game.pnlSpace.jugador.yPosition-50+20, 30, 30);
		yVelocity = 20;
		
		threadFire = new Thread(new Runnable() {
			@Override
			public void run() {
				while (yPosition > 0 - imgHeight) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					move(0, -yVelocity, game.pnlSpace);
				}
				
			}
		});
		threadFire.start();
	}

	public void drawBullet(Graphics g) {
		g.drawImage(img, xPosition, yPosition, imgWidth, imgHeight, null);
	}
}
