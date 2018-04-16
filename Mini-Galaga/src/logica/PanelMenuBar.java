package logica;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import myUtilities.*;

public class PanelMenuBar extends JPanel {
	
	MyButton btnJuegoNuevo = new MyButton("Juego Nuevo");
	MyButton btnPausar = new MyButton("Pausar");
	MyButton btnReanudar = new MyButton("Reanudar");
	MyButton btnTop3 = new MyButton("Top 3");

	public PanelMenuBar(MiniGalaga game) {
		super(new FlowLayout(FlowLayout.LEFT, 0, 0));
		add(btnJuegoNuevo);
		add(btnPausar);
		add(btnReanudar);
		add(btnTop3);
		
		btnJuegoNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.newGame();
			}
		});
		
		btnPausar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.pauseGame();
			}
		});
		
		btnReanudar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.resumeGame();
			}
		});
		
		btnTop3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
}
