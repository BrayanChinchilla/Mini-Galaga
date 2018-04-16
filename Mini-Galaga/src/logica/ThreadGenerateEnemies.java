package logica;

import java.util.concurrent.ThreadLocalRandom;

public class ThreadGenerateEnemies implements Runnable {

	MiniGalaga game;
	
	public ThreadGenerateEnemies (MiniGalaga game) {
		this.game = game;
	}
	
	@Override
	public void run() {
		int numEnemies = 0;
		while (true) {
			int offset = ThreadLocalRandom.current().nextInt(1, 4);
			try {
				Thread.sleep(offset * 1000);
			} catch (InterruptedException e) {
				System.out.println("Could not wait 1 full second");
			}
			
			int type = ThreadLocalRandom.current().nextInt(0, 3);
			int xPosition = ThreadLocalRandom.current().nextInt(0, 550);
			
			if (game.tiempo > 0 && !game.paused ) {
				switch (type) {
				case 0:
					game.pnlSpace.enemies[numEnemies % 10] = new Arcaico(game, xPosition);
					break;
				case 1:
					game.pnlSpace.enemies[numEnemies % 10] = new Cazador(game, xPosition);
					break;
				case 2:
					game.pnlSpace.enemies[numEnemies % 10] = new Caotica(game, xPosition);
					break;
				}
				numEnemies++;
			}
		}
	}
}