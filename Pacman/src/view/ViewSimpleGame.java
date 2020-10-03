package view;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.ControleurSimpleGame;



public class ViewSimpleGame {
	
	private JFrame fenetre;
	private int turn;
	private JLabel turn_label;
	
	public ViewSimpleGame() {
		fenetre = new JFrame();
		fenetre.setTitle("Pacman");
		fenetre.setSize(700, 700);
		fenetre.setLocationRelativeTo(null);
		fenetre.setResizable(false);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
		JPanel turn_panel = new JPanel();
		turn_panel.setLayout(new BoxLayout(turn_panel, BoxLayout.Y_AXIS));
		turn_label = new JLabel("Current turn: "+ Integer.toString(turn));
		turn_label.setAlignmentX(Component.CENTER_ALIGNMENT);
    
		turn_panel.add(turn_label);
		fenetre.add(turn_panel);
		fenetre.setVisible(true);
	}

	public void setTurn(int turn) {
		this.turn = turn;
		turn_label.setText("Current turn: "+Integer.toString(turn));
	}

}
