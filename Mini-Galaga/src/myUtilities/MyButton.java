package myUtilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * Encargada de setear la apariencia estandard de los JButtons del programa
 */
public class MyButton extends JButton {
	
	Border originalBorder = BorderFactory.createEtchedBorder(0);
	Border focusBorder = new LineBorder(Color.BLUE, 2);
	
	public MyButton(String text) {
		super(text);
		setFont(new Font("Comic Sans MS", Font.PLAIN, 9));
		setBorder(originalBorder);
		setPreferredSize(new Dimension(80, 30));
	
		addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent evt) {
                if (getModel().isPressed()) {
                    setBorder(focusBorder);
                } else if (getModel().isRollover()) {
                    setBorder(originalBorder);
                } else {
                    setBorder(originalBorder);
                }
            }
        });
	}
}