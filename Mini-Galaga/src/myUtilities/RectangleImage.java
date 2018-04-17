package myUtilities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import logica.Game;

public class RectangleImage extends Rectangle{
	
	public Image img;

	public RectangleImage(String path, int x, int y, int imgWidth, int imgHeight) {
		super(x, y, imgWidth, imgHeight);
		
		try {
			img = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(path));
		}
		catch (Exception e) {
			System.out.println("Image not found: "+ e.getMessage());
		}
	}
	
	public void moveRight() {
		x += 5;
	}
	
	public void moveLeft() {
		x += -5;
	}
	
	public void moveDown() {
		y += 5;
	}
	
	public void moveUp() {
		y += -5;
	}
	

}