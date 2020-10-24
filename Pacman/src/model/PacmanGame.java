package model;

import java.util.ArrayList;

import outils.Agent;
import outils.AgentAction;
import outils.AgentFantome;
import outils.AgentPacman;
import outils.Maze;
import outils.PositionAgent;
import outils.StrategyAleatoire;
import outils.StrategyFuite;
import outils.StrategyPoursuite;

public class PacmanGame extends Game{	
	private Maze maze;
	private String layout_chosen;// nom du fichier layout représentant le labyrinthe
	private ArrayList<Agent> agents;
	private int nombrePacman;
	private int turnCapsuleMange;
	private boolean capsuleMange;

	public PacmanGame(int maxturn, long time) {
		super(maxturn, time);
		agents = new ArrayList<Agent>();
		nombrePacman = 0;
				
	}

	@Override
	public void initializeGame() {
		System.out.println("Jeu initialisé");
		chargeMaze();
		agents = new ArrayList<Agent>();
		loadAgent();
		capsuleMange = false;
	}

	@Override
	public void takeTurn() {
		System.out.println("\t Nombre d'agents :"+ Integer.toString(agents.size()));
		
		// Les agents choisisse leur action
		for( Agent agt : agents) {
			// Si une capsule est mangée on modifié la strategy des agents
			if(capsuleMange) {
				if(agt.isPacman()) { // Si l'agent est un pacman
					agt.setStrategy(new StrategyPoursuite());
				}else { // Si c'est un fantome
					agt.setStrategy(new StrategyFuite());
				}
			}else {
				if(agt.isPacman()) { // Si l'agent est un pacman
					agt.setStrategy(new StrategyFuite());
				}else { // Si c'est un fantome
					agt.setStrategy(new StrategyPoursuite());
				}
			}
			
			agt.setAction(getAction(agt));
		}
		//Les agents effectue leur action
		for(Agent agt : agents) {
			moveAgent(agt, agt.getAction());

			// On teste si de la nourriture est mangée par un pacman
			if(agt.isPacman() && maze.isFood(agt.getPosition().getX(), agt.getPosition().getY())) {
				maze.setFood(agt.getPosition().getX(), agt.getPosition().getY(), false);
			}
			// On teste si une capsule est mangée par un pacman
			// Si oui on déclenche un timer de 20 périodes aux cours desquelles les pacman peuvent manger les fantômes
			if(agt.isPacman() && maze.isCapsule(agt.getPosition().getX(), agt.getPosition().getY())) {
				maze.setCapsule(agt.getPosition().getX(), agt.getPosition().getY(), false);
				turnCapsuleMange = turn;
				capsuleMange = true;

			}
		}
		
		
		ArrayList<Agent> pacmans = new ArrayList<Agent>();
		ArrayList<Agent> ghosts = new ArrayList<Agent>();
		// On sépare en deux les agents (pacman et fantome)
		for( Agent agt1 : agents) {
			if(agt1.isPacman()) {
				pacmans.add(agt1);
			}else {
				ghosts.add(agt1);
			}			
		}
		// Test si une capsule a été mange
		if(capsuleMange) {
			// PAcman peut désormais mangé les fantome
			for(Agent agt1 : ghosts) {
				for( Agent agt2 : pacmans) {
					System.out.println(agt1.getPosition() +" "+ agt2.getPosition());
					if(agt1.getPosition().getX() == agt2.getPosition().getX() && agt1.getPosition().getY() == agt2.getPosition().getY()) {
						System.out.println("Fantome est tué");
						agents.remove(agt1);
					}							
				}
			}
		}else {
			// Pour chaque pacman , on teste s'il est sur la même position qu'un fantome
			// Si c'est le cas il meurt
			for(Agent agt1 : pacmans) {
				for( Agent agt2 : ghosts) {
					if(agt1.getPosition().getX() == agt2.getPosition().getX() && agt1.getPosition().getY() == agt2.getPosition().getY()) {
						System.out.println("Pacman : "+agt1.getPosition() +" Fantôme : "+ agt2.getPosition());
						System.out.println("Pacman est tué");
						agents.remove(agt1);
						nombrePacman --;
					}							
				}
			}
		}
		if(turn > turnCapsuleMange +20) capsuleMange = false ;
	}

	@Override
	// Condition d'arrêt du jeu
	public boolean gameContinue() {
			
		
		boolean hasFood = false ;// SI HasFood = true c'est qu'il reste des pac-gommes dans le labyrinthe
		for(int x = 0; x < maze.getSizeX() ; x++) {
			for(int y = 0 ; y<maze.getSizeY() ; y++) {
				if(maze.isFood(x, y) || maze.isCapsule(x, y)) hasFood = true ;// Si il y a une pacgomme hasFood = true
				
			}
		}
		return this.turn<this.maxturn && nombrePacman != 0 && hasFood == true ;
	}

	@Override
	public void gameOver() {
		System.out.println("Le jeu est terminé ! (╯✧▽✧)╯");
		
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
			AgentPacman ap = new AgentPacman(i.getDir(), i.getX(), i.getY(), new StrategyFuite());
			addAgent(ap);
		}
		for(PositionAgent i : positionsFantomes) {
			AgentFantome af = new AgentFantome(i.getDir(), i.getX(), i.getY(), new StrategyAleatoire());
			addAgent(af);
		}
		nombrePacman = maze.getInitNumberOfPacmans();
	}
	
	// renvoie true si l'action est possible 
	public boolean isLegalMove(Agent agent,AgentAction action) {
		//Test pour savoir si la prochaine position sera un mur
		return (!maze.isWall(agent.getPosition().getX() + action.get_vx(), agent.getPosition().getY() + action.get_vy())); 
	}
	
	// Deplace un agent vers une position donne
	public void moveAgent(Agent agent, AgentAction action) {
		agent.getPosition().setX(agent.getPosition().getX() + action.get_vx());
		agent.getPosition().setY(agent.getPosition().getY() + action.get_vy());
		agent.getPosition().setDir(action.get_direction());
	}
	
	// Renvoie une action possible dans le labyrinthe pour un agent donné
	public AgentAction getAction(Agent agt) {
		if(agt.isPacman()) {
			System.out.println("Un pacman joue");
		}else {
			System.out.println("Un fantôme joue");
		}
		agt.getStrategy().afficheStrategy();
		AgentAction action = agt.getStrategy().getAction(agt, this);
		
		if(action == null) {
			do {
				action = new AgentAction((int) (Math.random() * 5) );
			}while(!isLegalMove(agt, action));
		}
		return action;		
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
	
	public ArrayList<Agent> getAgents() {
		return agents;
	}

	public boolean isCapsuleMange() {
		return capsuleMange;
	}
	
	

}
