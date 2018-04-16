package myUtilities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import logica.PanelSpace;

public class SquareImage {
	
	public Image img;
	public int xPosition;
	public int xVelocity;
	public int yPosition;
	public int yVelocity;
	public int imgWidth = 50;
	public int imgHeight = 50;
	Rectangle rectangle;

	public SquareImage(String path, int x, int y, int imgWidth, int imgHeight) {
		try {
			img = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(path));
		}
		catch (Exception e) {
			System.out.println("Image not found: "+ e.getMessage());
		}
		
		this.xPosition = x;
		this.yPosition = y;
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
		rectangle = new Rectangle(x, y, imgWidth, imgHeight);
	}
	
	public void move(int xIncrement, int yIncrement, PanelSpace pnlSpace) {
		xPosition = xPosition + xIncrement;
		yPosition = yPosition + yIncrement;
		rectangle.setBounds(xPosition, yPosition, imgWidth, imgHeight);
		pnlSpace.repaint();
	}
}