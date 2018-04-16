package logica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;


public class PanelSpace extends JPanel {
	
	Jugador jugador;
	Enemy[] enemies = new Enemy[10];

	public PanelSpace(MiniGalaga game) {
		setPreferredSize(new Dimension(600,650));
		setBackground(Color.BLACK);
		setLayout(null);
		add(game.lblPuntos);
		add(game.lblTiempo);
				
		game.lblPuntos.setBounds(500, 0, 100, 15);
		game.lblTiempo.setBounds(500, 20, 100, 15);
		
		jugador = new Jugador(game);
		
		Thread threadRepaint = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						System.out.println("Could not wait 1 full second");
					}
					
					if (!game.over && !game.paused ) {
						repaint();
					}
				}
			}
		});
		threadRepaint.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		jugador.drawNaveJugador(g);
		
		for (JugadorFuego b : jugador.bullets) {
			if (b != null) {
				b.drawBullet(g);
			}
		}
		
		for (Enemy e : enemies) {
			if (e != null) {
				e.drawEnemy(g);
				
				if (e instanceof Cazador) {
					for (CazadorFuego b : ((Cazador) e).bullets)
						if (b != null) 
							b.drawBullet(g);
				}
			}
		}
	}
	
}
