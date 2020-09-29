import java.awt.Dimension;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;



public class ViewSimpleGame {
	
	private JFrame fenetre;
	
	public ViewSimpleGame() {
	fenetre = new JFrame();
	fenetre.setTitle("Game");
	fenetre.setSize(700, 700);
	fenetre.setLocationRelativeTo(null);
	fenetre.setResizable(false);
    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    fenetre.setVisible(true);
	}

}
