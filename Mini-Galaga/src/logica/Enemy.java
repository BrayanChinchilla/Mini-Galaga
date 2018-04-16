package logica;

import java.awt.Graphics;

import myUtilities.SquareImage;

public class Enemy extends SquareImage {
	
	int vidaRestante;
	int puntos;
	
	public Enemy(String path, int x, int y, int imgWidth, int imgHeight) {
		super(path, x, y, imgWidth, imgHeight);
	}
	
	public void drawEnemy(Graphics g) {
		g.drawImage(img, xPosition, yPosition, imgWidth, imgHeight, null);
	}

}
