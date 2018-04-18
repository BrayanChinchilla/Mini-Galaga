package logica;

import java.awt.Graphics;

import myUtilities.RectangleImage;

public class SpecialItem extends RectangleImage {
	
	Game game;
	String type;
	
	boolean active = true;

	/**
	 * Crea una nave caotica y la independiza en su propia thread
	 * @param game
	 * @param randomX coordenada X en donde aparecera la nave
	 */
	public SpecialItem(String type, Game game, int randomX) {
		super(type, randomX, -50, 50, 50);
		
		this.game = game;
		this.type = type;
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (y < 650) {
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					if (!game.paused)
						moveDown();
					
					if (checkForCollision())
						break;
					
				}	
			}
		}).start();
	}
	
	public boolean checkForCollision() {
		if (intersects(game.falcon)) {
			if (type.equals("/itemEscudo.png")) {
				game.falcon.activateShield();
			}
			else if (type.equals("/itemShot.png")) {
				game.falcon.activateProtonCanon();
			}
			y = 750;
			active = false;
			return true;
		}
		return false;
	}
	
	public void drawItem(Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}

}
