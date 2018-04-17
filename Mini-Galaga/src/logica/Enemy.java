package logica;

import java.awt.Graphics;

import myUtilities.RectangleImage;

public class Enemy extends RectangleImage {
	
	int vidaRestante;
	int puntos;
	
	public Enemy(String path, int x, int y, int imgWidth, int imgHeight) {
		super(path, x, y, imgWidth, imgHeight);
	}
	
	public void drawEnemy(Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}
	
	public boolean checkForCollision(Game game) {
		for (FalconFire b : game.falcon.fire) {
			if (b != null ) {
				if (intersects(b)) {
					b.destroyBullet();
					return true;
				}
			}
		}
		return false;
	}

}
