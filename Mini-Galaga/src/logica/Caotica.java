package logica;

import java.awt.Graphics;

import myUtilities.RectangleImage;

public class Caotica extends Enemy{
	
	Thread threadCaotica;

	public Caotica(Game game, int randomX) {
		super("/caotica.png", randomX, 0, 50, 50);
		vidaRestante = 1;
		
		threadCaotica = new Thread(new Runnable() {
			@Override
			public void run() {
				while (y < 650) {
					try {
						Thread.sleep(39);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					if(!game.paused)
						moveDown();
					
					if (checkForCollision(game)) {
					}
				}	
			}
		});
		threadCaotica.start();

	}

}

