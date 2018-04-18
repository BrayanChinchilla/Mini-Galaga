package logica;

public class ThreadRenderScreen implements Runnable {
	
	Game game;
	
	public ThreadRenderScreen(Game game) {
		this.game = game;
	}

	@Override
	public void run() {
		while (game.running) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				System.out.println("Could not wait 1 full second");
			}		
			game.repaint();
		}
	}

}
