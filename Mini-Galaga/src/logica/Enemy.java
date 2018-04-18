package logica;

import java.awt.Graphics;

import myUtilities.RectangleImage;

public class Enemy extends RectangleImage {
	
	int vidaRestante;
	int puntos;
	
	public Enemy(String path, int x, int y, int imgWidth, int imgHeight) {
		super(path, x, y-imgHeight, imgWidth, imgHeight);
	}
	
	public void drawEnemy(Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}
	
	public String checkForCollision(Game game) {
		for (FalconFire b : game.falcon.fire) {
			if (b != null ) {
				if (intersects(b)) {
					b.destroyBullet();
					return b.type;
				}
			}
		}
		return "";
	}
	
	public void destroyMyself() {
		vidaRestante = 0;
		y = 750;
	}

}
