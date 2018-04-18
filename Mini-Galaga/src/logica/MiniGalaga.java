package logica;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import myUtilities.*;

public class MiniGalaga extends JFrame {
		
	JPanel pnlMain = new JPanel(new BorderLayout());
	Game game = new Game(this);
	
	Jugador[] top3 = {new Jugador(""), new Jugador(""), new Jugador("")};
		
	public MiniGalaga() {
		super("Mini Galaga");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pnlMain.add(new PanelMenuBar(this), BorderLayout.PAGE_START);
		pnlMain.add(game, BorderLayout.PAGE_END);
				
		add(pnlMain);
		pack();
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MiniGalaga();
	}
	
	public boolean checkTop3(Jugador jugador) {
		if (jugador.puntos > top3[0].puntos) {
			top3[2] = top3[1];
			top3[1] = top3[0];
			top3[0] = jugador; 
			return true;
		}
		else if (jugador.puntos > top3[1].puntos) {
			top3[2] = top3[1];
			top3[1] = jugador; 
			return true;
		}
		else if (jugador.puntos > top3[2].puntos) {
			top3[2] = jugador; 
			return true;
		}
		return false;
	}
	
	public void displayTop3() {
		JOptionPane.showMessageDialog(this, "<html>"
				+ top3[0].puntos+"&emsp;&emsp;&emsp;"+top3[0].name+"<br>"
				+ top3[1].puntos+"&emsp;&emsp;&emsp;"+top3[1].name+"<br>"
				+ top3[2].puntos+"&emsp;&emsp;&emsp;"+top3[2].name+"</html>", "Top 3", JOptionPane.QUESTION_MESSAGE);
	}
}
