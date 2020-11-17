package outils;

import model.Game;
import model.PacmanGame;

// Cette strategie renvoie une action aléotoire possible
public class StrategyAleatoire implements Strategy{

	@Override
	public AgentAction getAction(Agent agt, Game game) {
		AgentAction action;
		do {
			action = new AgentAction((int) (Math.random() * 5) );
		}while(!((PacmanGame)game).isLegalMove(agt, action));
		System.out.println(" --> Action choisie : "+ action.get_direction()+"\n");
		return action;
	}

	@Override
	public void afficheStrategy() {
		System.out.println("-- Strategy Aleatoire");
		
	}

}
