package logica;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import myUtilities.SquareImage;

public class Jugador extends SquareImage {
	
	Action moveRight;
	Action moveLeft;
	Action fire;
	
	JugadorFuego[] bullets = new JugadorFuego[10];
	int numBullets = 0;
		
	public Jugador(MiniGalaga game) {
		super("/naveJugador.png", 275, 580, 50, 50);
		xVelocity = 10;
		
		moveRight = new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	        		move(xVelocity, 0, game.pnlSpace);
	        }
		};
		moveRight.setEnabled(false);
		
		moveLeft = new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	        		move(-xVelocity, 0, game.pnlSpace);
	        }
		}; 
		moveLeft.setEnabled(false);
		
		fire = new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
        			System.out.println("Fire GREEN");
        			bullets[numBullets % 10] = new JugadorFuego(game);
        			numBullets++;
	        }
		};
		fire.setEnabled(false);
		
		game.pnlMain.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "moveRight");
		game.pnlMain.getActionMap().put("moveRight", moveRight);
		
		game.pnlMain.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "moveLeft");
		game.pnlMain.getActionMap().put("moveLeft", moveLeft);
		
		game.pnlMain.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_M, 0), "fire");
		game.pnlMain.getActionMap().put("fire", fire);
									
	}

	public void enableActions(boolean flag) {
		moveRight.setEnabled(flag);
		moveLeft.setEnabled(flag);
		fire.setEnabled(flag);	
	}
	
	public void drawNaveJugador(Graphics g) {
		g.drawImage(img, xPosition, yPosition, imgWidth, imgHeight, null);
	}
}

/*
 * https://stackoverflow.com/questions/13999506/threads-with-key-bindings?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
 * Could improve animations by allowing user to shoot while moving, it can be done by using 
 * getKeyStroke(int keyCode, int modifiers, boolean onKeyRelease) together with flags
 * Might not be worth it
 */
