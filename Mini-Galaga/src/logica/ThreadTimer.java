package logica;

public class ThreadTimer implements Runnable {

	MiniGalaga game;
	
	public ThreadTimer(MiniGalaga game) {
		this.game = game;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1200);
			} catch (InterruptedException e) {
				System.out.println("Could not wait 1 full second");
			}
			
			if (game.tiempo > 0 && !game.paused ) {
					game.tiempo--;			
					game.lblTiempo.setText("Tiempo: "+game.tiempo);
			}
			else if (game.tiempo <= 0) {
				//Time is over
			}
		}
	}

}
