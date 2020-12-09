package outils;

import model.Game;
import model.PacmanGame;

// Cette stratégie a un ordre du priorité sur les actions
// Devant, coté, derrière, RIEN
public class StrategyPriorité implements Strategy{

	@Override
	public AgentAction getAction(Agent agt, Game game) {
		AgentAction action = new AgentAction(agt.getAction().get_direction());
		switch(agt.getAction().get_direction()) {
		case AgentAction.NORTH:
			if(!((PacmanGame)game).isLegalMove(agt, action)) {
				action = new AgentAction(AgentAction.WEST);
			}else {
				System.out.println(" --> Action choisie : "+ action.get_direction()+"\n");
				return action;
			}
			if(!((PacmanGame)game).isLegalMove(agt, action)) {
				action = new AgentAction(AgentAction.EAST);
			}else {
				System.out.println(" --> Action choisie : "+ action.get_direction()+"\n");
				return action;
			}
			if(!((PacmanGame)game).isLegalMove(agt, action)) {
				action = new AgentAction(AgentAction.SOUTH);
			}else {
				System.out.println(" --> Action choisie : "+ action.get_direction()+"\n");
				return action;
			}
			if(!((PacmanGame)game).isLegalMove(agt, action)) {
				System.out.println(" --> Action choisie : "+ AgentAction.STOP+"\n");
				return new AgentAction(AgentAction.STOP);
			}else {
				System.out.println(" --> Action choisie : "+ action.get_direction()+"\n");
				return action;
			}
		case AgentAction.WEST:
			if(!((PacmanGame)game).isLegalMove(agt, action)) {
				action = new AgentAction(AgentAction.NORTH);
			}else {
				System.out.println(" --> Action choisie : "+ action.get_direction()+"\n");
				return action;
			}
			if(!((PacmanGame)game).isLegalMove(agt, action)) {
				action = new AgentAction(AgentAction.SOUTH);
			}else {
				System.out.println(" --> Action choisie : "+ action.get_direction()+"\n");
				return action;
			}			
			if(!((PacmanGame)game).isLegalMove(agt, action)) {
				action = new AgentAction(AgentAction.EAST);
			}else {
				System.out.println(" --> Action choisie : "+ action.get_direction()+"\n");
				return action;
			}
			if(!((PacmanGame)game).isLegalMove(agt, action)) {
				System.out.println(" --> Action choisie : "+ AgentAction.STOP+"\n");
				return new AgentAction(AgentAction.STOP);
			}else {
				System.out.println(" --> Action choisie : "+ action.get_direction()+"\n");
				return action;
			}
		case AgentAction.EAST:
			if(!((PacmanGame)game).isLegalMove(agt, action)) {
				action = new AgentAction(AgentAction.NORTH);
			}else {
				System.out.println(" --> Action choisie : "+ action.get_direction()+"\n");
				return action;
			}
			if(!((PacmanGame)game).isLegalMove(agt, action)) {
				action = new AgentAction(AgentAction.SOUTH);
			}else {
				System.out.println(" --> Action choisie : "+ action.get_direction()+"\n");
				return action;
			}
			if(!((PacmanGame)game).isLegalMove(agt, action)) {
				action = new AgentAction(AgentAction.WEST);
			}else {
				System.out.println(" --> Action choisie : "+ action.get_direction()+"\n");
				return action;
			}
			if(!((PacmanGame)game).isLegalMove(agt, action)) {
				System.out.println(" --> Action choisie : "+ AgentAction.STOP+"\n");
				return new AgentAction(AgentAction.STOP);
			}else {
				System.out.println(" --> Action choisie : "+ action.get_direction()+"\n");
				return action;
			}
		case AgentAction.SOUTH:
			if(!((PacmanGame)game).isLegalMove(agt, action)) {
				System.out.println(" --> Action choisie : "+ action.get_direction()+"\n");
				action = new AgentAction(AgentAction.EAST);
			}else {
				return action;
			}
			if(!((PacmanGame)game).isLegalMove(agt, action)) {
				System.out.println(" --> Action choisie : "+ action.get_direction()+"\n");
				action = new AgentAction(AgentAction.WEST);
			}else {
				return action;
			}
			if(!((PacmanGame)game).isLegalMove(agt, action)) {
				System.out.println(" --> Action choisie : "+ action.get_direction()+"\n");
				action = new AgentAction(AgentAction.NORTH);
			}else {
				return action;
			}
			if(!((PacmanGame)game).isLegalMove(agt, action)) {
				System.out.println(" --> Action choisie : "+ AgentAction.STOP+"\n");
				return new AgentAction(AgentAction.STOP);
			}else {
				System.out.println(" --> Action choisie : "+ action.get_direction()+"\n");
				return action;
			}
		case AgentAction.STOP:
			System.out.println(" --> Action choisie : "+ action.get_direction()+"\n");
			return action;

		}
		return new AgentAction(AgentAction.STOP);
	}

	@Override
	public void afficheStrategy() {
		System.out.println("-- Strategy Priorité");
		
	}

}