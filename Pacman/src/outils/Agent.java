package outils;

public abstract class Agent {
	protected PositionAgent position;
	protected AgentAction action;
	protected Strategy strategy;
	protected boolean estInteractif;
	protected int numeroJoueur;

	public Agent(int direction, int x, int y, Strategy strat) {
		this.position = new PositionAgent(x, y, direction);
		this.action = new AgentAction(direction);
		this.strategy = strat;
		this.estInteractif = false;
	}
	
	public abstract boolean isPacman();
	
	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	public AgentAction getAction() {
		return action;
	}

	public void setAction(AgentAction action) {
		this.action = action;
	}

	public void setPosition(PositionAgent position) {
		this.position = position;
	}

	public PositionAgent getPosition() {
		return position;
	}	

	public boolean isEstInteractif() {
		return estInteractif;
	}

	public void setEstInteractif(boolean estInteractif) {
		this.estInteractif = estInteractif;
	}
	
	public int getNumeroJoueur() {
		return numeroJoueur;
	}

	public void setNumeroJoueur(int nbJoueur) {
		this.numeroJoueur = nbJoueur;
	}
	
}
