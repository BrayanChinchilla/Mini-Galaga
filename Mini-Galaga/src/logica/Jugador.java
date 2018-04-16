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
	
	boolean right, left, shoot = false;
	
	Action rightPress;
	Action rightRelease;
	Action leftPress;
	Action leftRelease;
	Action shootPress;
	
	JugadorFuego[] bullets = new JugadorFuego[10];
	int numBullets = 0;
		
	public Jugador(MiniGalaga game) {
		super("/naveJugador.png", 275, 580, 50, 50);
		xVelocity = 10;
		
		rightPress = new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	        		right = true;
	        }
		};
		rightPress.setEnabled(false);
		
		rightRelease = new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	        		right = false;
	        }
		};
		rightRelease.setEnabled(false);
		
		leftPress = new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	        		left = true;
	        }
		}; 
		leftPress.setEnabled(false);
		
		leftRelease = new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	        		left = false;
	        }
		};
		leftRelease.setEnabled(false);
		
		shootPress = new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
        			fire();
	        }
		};
		shootPress.setEnabled(false);
		
			
		game.pnlMain.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "rightPress");
		game.pnlMain.getActionMap().put("rightPress", rightPress);
		
		game.pnlMain.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "rightRelease");
		game.pnlMain.getActionMap().put("rightRelease", rightRelease);
		
		game.pnlMain.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "leftPress");
		game.pnlMain.getActionMap().put("leftPress", leftPress);
		
		game.pnlMain.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "leftRelease");
		game.pnlMain.getActionMap().put("leftRelease", leftRelease);
		
		game.pnlMain.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_M, 0, false), "shootPress");
		game.pnlMain.getActionMap().put("shootPress", shootPress);
		
		/*
		 * Mantiene el movimiento de la nave independiente de su artilleria
		 */
		Thread threadListenKeys = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (right) {
						move(xVelocity, 0);
					}
					if (left) {
						move(-xVelocity, 0);
					}
				}
			}
			
		});
		threadListenKeys.start();
									
	}
	

	public void fire() {
		bullets[numBullets % 10] = new JugadorFuego(this);
		numBullets++;
	}
	
	public void enableActions(boolean flag) {
		rightPress.setEnabled(flag);
		rightRelease.setEnabled(flag);
		leftPress.setEnabled(flag);
		leftRelease.setEnabled(flag);
		shootPress.setEnabled(flag);	
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
