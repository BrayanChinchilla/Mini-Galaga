package logica;

public class ThreadTimer implements Runnable {

	Game game;
	
	public ThreadTimer(Game game) {
		this.game = game;
	}
	
	@Override
	public void run() {
		while (!game.over) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Could not wait 1 full second");
			}
			
			if (!game.paused) {
				game.tiempo--;			
				game.lblTiempo.setText("Tiempo: "+game.tiempo);
				
				if(game.tiempo <= 0) {
					game.gameOver();
				}
			}
		}
	}

}
