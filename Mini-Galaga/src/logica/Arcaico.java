package logica;

import java.awt.Graphics;

import myUtilities.SquareImage;

public class Arcaico extends Enemy{
	
	Thread threadArcaico;

	public Arcaico(MiniGalaga game, int randomX) {
		super("/arcaico.png", randomX, 0, 50, 50);
		yVelocity = 6;
		
		threadArcaico = new Thread(new Runnable() {
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
		threadArcaico.start();

	}

}

