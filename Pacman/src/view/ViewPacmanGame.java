package view;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//import model.Maze;
import model.PanelPacmanGame;

public class ViewPacmanGame {
	private JFrame fenetre;
	private int turn;
	private JLabel turn_label;
	private PanelPacmanGame game_panel;
	private JPanel general;
	
	public ViewPacmanGame() {
		
		// On paramètre la fenêtre	
		fenetre = new JFrame();
		fenetre.setTitle("Pacman");
		fenetre.setSize(700, 700);
		fenetre.setLocationRelativeTo(null);
		fenetre.setResizable(false);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// On crée le layout general
		general = new JPanel();
		general.setLayout(new BorderLayout());
		
		// Affichage des tours
		JPanel turn_panel = new JPanel();
		turn_label = new JLabel("Current turn: "+ Integer.toString(turn));
		turn_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		turn_panel.add(turn_label);
		general.add(turn_panel, BorderLayout.NORTH);
		fenetre.add(general);
		fenetre.setVisible(true);
	}
	public PanelPacmanGame getGame_panel() {
		return game_panel;
	}

	public void setGame_panel(PanelPacmanGame game_panel) {
		this.game_panel = game_panel;
		general.add(game_panel, BorderLayout.CENTER);
		fenetre.add(general);
		fenetre.setVisible(true);
		
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}
}
