package outils;

import model.Game;

// Stretegy du comportement d'un Agent
public interface Strategy {
	// Renvoie une action 
	public AgentAction getAction(Agent agt, Game game);
	public void afficheStrategy();
}
