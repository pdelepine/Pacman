package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Agent;
import model.AgentFantome;
import model.AgentPacman;
import model.Game;
import model.PacmanGame;
//import model.Maze;
import model.PanelPacmanGame;
import model.PositionAgent;

public class ViewPacmanGame implements Observateur{
	private JFrame fenetre;
	private int turn;
	private JLabel turn_label;
	private PanelPacmanGame game_panel;
	private JPanel general;
	
	public ViewPacmanGame() {
		
		// On paramètre la fenêtre	
		fenetre = new JFrame();
		fenetre.setTitle("Pacman");
		fenetre.setSize(1000, 1000);
		//fenetre.setMinimumSize(new Dimension(400,400));
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
		fenetre.setSize(game_panel.getMaze().getSizeX() * 30, game_panel.getMaze().getSizeY() * 35);
		fenetre.setVisible(true);
		
	}

	public void setTurn(int turn) {
		this.turn = turn;
		turn_label.setText("Current turn: "+ Integer.toString(turn));
	}
	@Override
	public void update(Game game) {
		PacmanGame pgame = (PacmanGame) game;
		this.turn = pgame.getTurn();	
		turn_label.setText("Current turn: "+Integer.toString(pgame.getTurn())); // Change le label sur l'inteface
		
		game_panel.setGhostsScarred(pgame.isCapsuleMange());
		
		// update la position des agents (pacman et fantome)
		ArrayList<PositionAgent> pacmans = new ArrayList<PositionAgent>();
		ArrayList<PositionAgent> fantomes = new ArrayList<PositionAgent>();
		
		System.out.println("Nombre d'agents :"+ Integer.toString(pgame.getAgents().size()));
		for( Agent agt : pgame.getAgents()) {
			if(agt.isPacman()) {
				pacmans.add(agt.getPosition());
			}else {
				fantomes.add(agt.getPosition());
			}
		}
		game_panel.setPacmans_pos(pacmans);
		game_panel.setGhosts_pos(fantomes);
		// On actualise l'interface du jeu
		game_panel.repaint();
		
	}
}
