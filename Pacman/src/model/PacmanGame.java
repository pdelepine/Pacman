package model;

import java.util.ArrayList;

import controleur.InterfaceControleur;

public class PacmanGame extends Game{	
	private Maze maze;
	private ArrayList<Agent> agents;

	public PacmanGame(int maxturn, long time) {
		super(maxturn, time);
		agents = new ArrayList<Agent>();
				
	}
	
	public void chargeMaze(String s) {
		try {
			this.maze = new Maze(s);
		}catch(Exception e) {
			System.out.println("Probleme chargement labyrinth");
		}
	}	

	public Maze getMaze() {
		return maze;
	}
	
	public void addAgent(Agent agt) {
		agents.add(agt);
	}
	
	public void removeAgent(Agent agt) {
		agents.remove(agt);
	}

	@Override
	public void initializeGame() {
		System.out.println("Jeu initialisé");
		chargeMaze("Resources/layouts/testMaze.lay");
	}

	@Override
	public void takeTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean gameContinue() {
		System.out.print("\t Test si jeu terminé");
		System.out.println(" " + Boolean.toString(this.turn<=this.maxturn));
		return this.turn<this.maxturn;
	}

	@Override
	public void gameOver() {
		System.out.println("Le jeu est terminé");
		
	}
	

}
