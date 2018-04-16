package myUtilities;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/*
 * Encargada de setear la apariencia estandard de las JLabels del programa
 */
public class MyLabel extends JLabel{

	public MyLabel(String text) {
		super(text);
		setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		setForeground(Color.WHITE);
	}
	
	public MyLabel(String text, int fontSize) {
		super(text);
		setFont(new Font("Lucida Grande", Font.PLAIN, fontSize));
	}
}
