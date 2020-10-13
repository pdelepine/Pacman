package model;

// Cette strategie renvoie une action aléotoire possible
public class StrategyAleatoire implements Strategy{

	@Override
	public AgentAction getAction(Agent agt, Game game) {
		AgentAction action;
		do {
			action = new AgentAction((int) (Math.random() * 5) );
		}while(!((PacmanGame)game).isLegalMove(agt, action));
		return action;
	}

	@Override
	public void afficheStrategy() {
		System.out.println("-- Strategy Aleatoire");
		
	}

}
