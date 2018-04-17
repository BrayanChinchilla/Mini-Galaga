package logica;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import myUtilities.RectangleImage;

public class Falcon extends RectangleImage {
	
	Game game;
	
	boolean alive = true;
	boolean right, left, shoot = false;
	
	Action rightPress;
	Action rightRelease;
	Action leftPress;
	Action leftRelease;
	Action shootPress;
	
	FalconFire[] fire = new FalconFire[10];
	int numBullets = 0;
		
	public Falcon(Game game, KeyStroke shootingKeyStroke) {
		super("/naveJugador.png", 275, 580, 50, 50);
		this.game = game;
		
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
	        		if(alive)
	        			fire();
	        }
		};
		shootPress.setEnabled(false);
		
			
		game.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "rightPress");
		game.getActionMap().put("rightPress", rightPress);
		
		game.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "rightRelease");
		game.getActionMap().put("rightRelease", rightRelease);
		
		game.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "leftPress");
		game.getActionMap().put("leftPress", leftPress);
		
		game.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "leftRelease");
		game.getActionMap().put("leftRelease", leftRelease);
		
		game.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(shootingKeyStroke, "shootPress");
		game.getActionMap().put("shootPress", shootPress);
		
		/*
		 * Mantiene el movimiento de la nave independiente de su artilleria
		 */
		Thread threadListenKeys = new Thread(new Runnable() {
			@Override
			public void run() {
				while(alive) {
					try {
						Thread.sleep(25);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (right) {
						moveRight();
					}
					if (left) {
						moveLeft();
					}
					if (checkForCollision()) {
						alive = false;
						game.gameOver();
						break;
					}
				}
			}
			
		});
		threadListenKeys.start();
									
	}
	
	public void fire() {
		fire[numBullets % 10] = new FalconFire(game);
		numBullets++;
	}
	
	public boolean checkForCollision() {
		
		for (Enemy e : game.enemies) {
			if (e != null) {
				if (intersects(e)) {
					return true;
				}
				
				if (e instanceof Cazador) {
					for (CazadorFire b : ((Cazador) e).fire)
						if (b != null) 
							if (intersects(b)) {
								b.destroyBullet();
								return true;
							}
				}
			}
		}
		return false;
	}
	
	public void enableActions(boolean flag) {
		rightPress.setEnabled(flag);
		rightRelease.setEnabled(flag);
		leftPress.setEnabled(flag);
		leftRelease.setEnabled(flag);
		shootPress.setEnabled(flag);	
	}
	
	public void drawNaveJugador(Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}
}

/*
 * https://stackoverflow.com/questions/13999506/threads-with-key-bindings?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
 * Could improve animations by allowing user to shoot while moving, it can be done by using 
 * getKeyStroke(int keyCode, int modifiers, boolean onKeyRelease) together with flags
 * Might not be worth it
 */
