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
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Could not wait 1 full second");
			}
			
			if (!game.over && !game.paused ) {
					game.tiempo--;			
					game.lblTiempo.setText("Tiempo: "+game.tiempo);
					if(game.tiempo <= 0)
						game.over = true;
			}
		}
	}

}
