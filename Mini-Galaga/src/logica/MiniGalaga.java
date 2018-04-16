package logica;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import myUtilities.*;

public class MiniGalaga extends JFrame {
	
	int puntos = 0;
	int tiempo = 60;
	
	MyLabel lblPuntos = new MyLabel("Puntos:  "+puntos);
	MyLabel lblTiempo = new MyLabel("Tiempo: "+tiempo);
	
	boolean paused = true;
		
	JPanel pnlMain = new JPanel(new BorderLayout());
	PanelSpace pnlSpace = new PanelSpace(this);

	Thread threadTimer;
	Thread threadGenerateEnemies;
		
	public MiniGalaga() {
		super("Mini Galaga");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pnlMain.add(new PanelMenuBar(this), BorderLayout.PAGE_START);
		pnlMain.add(pnlSpace, BorderLayout.PAGE_END);
				
		add(pnlMain);
		pack();
		setResizable(false);
		setVisible(true);
	}
	
	public void newGame() {
		paused = false;
		puntos = 0;
		tiempo = 60;
		
		threadTimer = new Thread(new ThreadTimer(this));
		threadTimer.start();
		
		threadGenerateEnemies = new Thread(new ThreadGenerateEnemies(this));
		threadGenerateEnemies.start();
		
		pnlSpace.jugador.enableActions(true);
	}
	
	public void pauseGame() {
		paused = true;
		pnlSpace.jugador.enableActions(false);

	}
	
	public void resumeGame() {
		paused = false;

		pnlSpace.jugador.enableActions(true);
	}
	
	public static void main(String[] args) {
		new MiniGalaga();
	}
	
}
