package model;

import java.util.ArrayList;

public class PacmanGame extends Game{	
	private Maze maze;
	private String layout_chosen;// nom du fichier layout représentant le labyrinthe
	private ArrayList<Agent> agents;

	public PacmanGame(int maxturn, long time) {
		super(maxturn, time);
		agents = new ArrayList<Agent>();
				
	}

	@Override
	public void initializeGame() {
		System.out.println("Jeu initialisé");
		chargeMaze();
		loadAgent();
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
	
	// Change le labyrinthe selon le layout choisis
	public void chargeMaze() {
		try {
			System.out.println(layout_chosen);
			this.maze = new Maze(layout_chosen);
		}catch(Exception e) {
			System.out.println("Probleme chargement labyrinth");
		}
	}
	
	public void setLayout_chosen(String layout_chosen) {
		this.layout_chosen = layout_chosen;
	}	
	//Charge les agents 
	public void loadAgent() {
		ArrayList<PositionAgent> positionsPacmans = maze.getPacman_start();
		ArrayList<PositionAgent> positionsFantomes = maze.getGhosts_start();
		
		for(PositionAgent i : positionsPacmans) {
			AgentPacman ap = new AgentPacman(i.getDir(), i.getX(), i.getY());
			addAgent(ap);
		}
		for(PositionAgent i : positionsFantomes) {
			AgentFantome af = new AgentFantome(i.getDir(), i.getX(), i.getY());
			addAgent(af);
		}
	}
	
	// renvoie true si l'action est possible 
	public boolean isLegalMove(Agent agent,AgentAction action) {
		//Test pour savoir si la prochaine position sera un mur
		if(maze.isWall(agent.getPosition().getX() + action.get_vx(), agent.getPosition().getY() + action.get_vy())) {
			return false;
		}else if(maze.isFood(agent.getPosition().getX() + action.get_vx(), agent.getPosition().getY() + action.get_vy())) {
				
			
		}
		
		return true;
	}
	
	// Deplace un agent vers une position donne
	public void moveAgent(Agent agent, AgentAction action) {
		agent.getPosition().setX(agent.getPosition().getX() + action.get_vx());
		agent.getPosition().setY(agent.getPosition().getY() + action.get_vy());
		agent.getPosition().setDir(action.get_direction());
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
	
	

}
