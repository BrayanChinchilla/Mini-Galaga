package logica;

import java.awt.Graphics;

import myUtilities.SquareImage;

public class Caotica extends Enemy{
	
	Thread threadCaotica;

	public Caotica(MiniGalaga game, int randomX) {
		super("/caotica.png", randomX, 0, 50, 50);
		yVelocity = 12;
		
		threadCaotica = new Thread(new Runnable() {
			@Override
			public void run() {
				while (yPosition < 650) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					move(0, yVelocity, game.pnlSpace);
				}	
			}
		});
		threadCaotica.start();

	}

}

