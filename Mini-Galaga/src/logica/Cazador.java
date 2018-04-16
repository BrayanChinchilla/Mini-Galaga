package logica;

import java.awt.Graphics;

import myUtilities.SquareImage;

public class Cazador extends Enemy{
	
	Thread threadCazador;

	public Cazador(MiniGalaga game, int randomX) {
		super("/cazador.png", randomX, 0, 50, 50);
		yVelocity = 9;
		
		threadCazador = new Thread(new Runnable() {
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
		threadCazador.start();

	}

}

