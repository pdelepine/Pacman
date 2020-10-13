package model;

// Stretegy du comortement d'un Agent
public interface Strategy {
	// Renvoie une action 
	public AgentAction getAction(Agent agt, Game game);
	public void afficheStrategy();
}
