package logica;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import myUtilities.RectangleImage;

/*
 * Crea al Falcon
 * Independiza el movimiento de la nave de los disparos 
 */
public class Falcon extends RectangleImage {
	
	Game game;
	
	boolean alive = true;
	boolean right, left, shoot = false;
	
	Action rightPress;
	Action rightRelease;
	Action leftPress;
	Action leftRelease;
	Action shootPress;
	
	FalconFire[] fire = new FalconFire[15];
	int numBullets = 0;
	
	int protonShots = 0;
	boolean escudoActive = false;
	
	Image escudo = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/escudo.png"));
	
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
	        		if(alive) {
	        			if (protonShots > 0) {
	        				protonShots--;
	        				fire("/fuegoJugadorSpecial.png");
	        			}
	        			else {
	        				fire("/fuegoJugador.png");
	        			}
	        		}
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
		 * Mantiene el movimiento de la nave independiente
		 */
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(alive) {
					try {
						Thread.sleep(25);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (right && x < 600 -width) {
						moveRight();
					}
					else if (left && x > 0) {
						moveLeft();
					}
					if (checkForCollision()) {
						if (escudoActive) {
							escudoActive = false;
						}
						else {
							alive = false;
							game.gameOver();
							break;
						}
					}
					if (!game.running) {
						alive = false;
					}
				}
			}	
		}).start();
									
	}
	
	public void fire (String type) {
		fire[numBullets % 15] = new FalconFire(game, type);
		numBullets++;
	}
	
	/**
	 * Verifica si la nave ha chocado con alguna nave/bala enemiga
	 * @return true si choca con algo
	 */
	public boolean checkForCollision() {		
		for (Enemy e : game.enemies) {
			if (e != null) {
				if (intersects(e)) {
					e.destroyMyself();
					if (e instanceof Arcaico)
						game.jugador.arcaicoKilled();
					else if (e instanceof Cazador)
						game.jugador.cazadorKilled();
					else if (e instanceof Caotica)
						game.jugador.caoticaKilled();
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
	
	public void activateProtonCanon() {
		protonShots += 5;
	}
	
	public void activateShield() {
		escudoActive = true;
	}
	
	/**
	 * Determina si las acciones relacionadas con las teclas son ejecutadas
	 * @param flag yes/no
	 */
	public void enableActions(boolean flag) {
		rightPress.setEnabled(flag);
		rightRelease.setEnabled(flag);
		leftPress.setEnabled(flag);
		leftRelease.setEnabled(flag);
		shootPress.setEnabled(flag);	
	}
	
	public void drawNaveJugador(Graphics g) {
		if (escudoActive)
			g.drawImage(escudo, x-10, y-10, width+20, height+20, null);
		g.drawImage(img, x, y, width, height, null);
	}
}

/*
 * https://stackoverflow.com/questions/13999506/threads-with-key-bindings?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
 */
