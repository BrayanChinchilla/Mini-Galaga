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
			}
		}
	}
	
}
