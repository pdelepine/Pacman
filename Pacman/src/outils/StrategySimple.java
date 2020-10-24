package outils;

import java.util.ArrayList;

import model.Game;
import model.PacmanGame;

// Cette strategy suit les pac-gommes(Food) avec enpriorité celle de devant
public class StrategySimple implements Strategy{

	@Override
	public AgentAction getAction(Agent agt, Game game) {
		PacmanGame game1 = (PacmanGame) game;
		ArrayList<PositionAgent> positionFood = new ArrayList<PositionAgent>();
		if(game1.getMaze().isFood(agt.getPosition().getX()+1, agt.getPosition().getY())) {
			positionFood.add(new PositionAgent(agt.getPosition().getX()+1, agt.getPosition().getY(), AgentAction.EAST));
		}
		if(game1.getMaze().isFood(agt.getPosition().getX()-1, agt.getPosition().getY())) {
			positionFood.add(new PositionAgent(agt.getPosition().getX()-1, agt.getPosition().getY(), AgentAction.WEST));
		}
		if(game1.getMaze().isFood(agt.getPosition().getX(), agt.getPosition().getY() + 1)) {
			positionFood.add(new PositionAgent(agt.getPosition().getX(), agt.getPosition().getY() + 1, AgentAction.SOUTH));
		}
		if(game1.getMaze().isFood(agt.getPosition().getX(), agt.getPosition().getY() - 1)) {
			positionFood.add(new PositionAgent(agt.getPosition().getX(), agt.getPosition().getY() - 1, AgentAction.NORTH));
		}
		
		// Si la nourriture est en face on la choisis en priorité
		for(PositionAgent p : positionFood) {
			if(agt.getPosition().getDir() == p.getDir()) {
				return new AgentAction(p.getDir());
			}
		}
		// Si pas de nourriture en face , on choisis aléatoiremet parmis les autres 
		int choixAlteatoire = 4 ;
		if(positionFood.size() != 0) {
			choixAlteatoire = (int) (Math.random() * positionFood.size());
			return new AgentAction(positionFood.get(choixAlteatoire).getDir());
		}
		return null;
	}

	@Override
	public void afficheStrategy() {
		System.out.println("-- Strategy Simple");
		
	}

}
