package logica;

import java.util.concurrent.ThreadLocalRandom;

public class ThreadGenerateItems implements Runnable {

	Game game;
	
	public ThreadGenerateItems(Game game) {
		this.game = game;
	}

	@Override
	public void run() {
		int numItems = 0;
		while (!game.over && numItems < 3) {
			int offset = ThreadLocalRandom.current().nextInt(10, 20);
			try {
				Thread.sleep(offset * 1000);
			} catch (InterruptedException e) {
				System.out.println("Could not wait 1 full second");
			}
			
			if (!game.paused) {
				int type = ThreadLocalRandom.current().nextInt(0, 2);
				int xPosition = ThreadLocalRandom.current().nextInt(0, 550);
				
				switch (type) {
				case 0:
					game.specialItem = new SpecialItem("/itemEscudo.png", game, xPosition);
					break;
				case 1:
					game.specialItem = new SpecialItem("/itemShot.png", game, xPosition);
					break;
				}
				numItems++;
			}
		}
	}
}