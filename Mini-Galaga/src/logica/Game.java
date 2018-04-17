package logica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import myUtilities.MyLabel;


public class Game extends JPanel {
	
	Jugador jugador;
	
	Falcon falcon;
	Enemy[] enemies = new Enemy[10];
	MiniGalaga app;
	
	int tiempo;
	MyLabel lblTiempo = new MyLabel("Tiempo: 60");
	MyLabel lblPuntos = new MyLabel("Puntos:  0");
	
	boolean paused = true;
	boolean over = false;
	
	Thread threadTimer;
	Thread threadGenerateEnemies;
	Thread threadGenerateItems;

	public Game(MiniGalaga app) {
		this.app = app;
		setPreferredSize(new Dimension(600,650));
		setBackground(Color.BLACK);
		setLayout(null);
		add(lblPuntos);
		add(lblTiempo);
				
		lblPuntos.setBounds(500, 0, 100, 15);
		lblTiempo.setBounds(500, 20, 100, 15);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						System.out.println("Could not wait 1 full second");
					}
					
					repaint();
				}
			}
		}).start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (jugador != null)
			lblPuntos.setText("Puntos:  "+jugador.puntos);
		
		if(falcon != null) {
			falcon.drawNaveJugador(g);
		
			for (FalconFire f : falcon.fire) {
				if (f != null && f.draw) {
					f.drawBullet(g);
				}
			}
		}
		
		for (Enemy e : enemies) {
			if (e != null) {
				if (e.vidaRestante > 0)
					e.drawEnemy(g);
				
				if (e instanceof Cazador) {
					for (CazadorFire f : ((Cazador) e).fire)
						if (f != null && f.draw) 
							f.drawBullet(g);
				}
			}
		}
	}
	
	public void newGame() {
		jugador = new Jugador(JOptionPane.showInputDialog(this, "Cual es tu nombre? Tu inicial sera tu arma", "Juego Nuevo", JOptionPane.QUESTION_MESSAGE));
		
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).clear();
		falcon = new Falcon(this, KeyStroke.getKeyStroke(jugador.name.toLowerCase().charAt(0)));
		falcon.enableActions(true);

		paused = false;
		over = false;
		
		tiempo = 60;
		threadTimer = new Thread(new ThreadTimer(this));
		threadTimer.start();
		
		threadGenerateEnemies = new Thread(new ThreadGenerateEnemies(this));
		threadGenerateEnemies.start();
		
		threadGenerateItems = new Thread(new ThreadGenerateItems(this));
		threadGenerateItems.start();
		
	}
	
	public void pauseGame() {
		paused = true;
		falcon.enableActions(false);
	}
	
	public void resumeGame() {
		paused = false;
		falcon.enableActions(true);
	}
	
	public void gameOver() {
		over = true;
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).clear();
		
		String message = "Gracias por jugar!\nLograste "+jugador.puntos+" puntos\n";
		if (app.checkTop3(jugador))
			message += "Entraste a la lista del Top 3";
		JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
	}
}
